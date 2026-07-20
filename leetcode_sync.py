#!/usr/bin/env python3
"""
LeetCode to GitHub Synchronizer
--------------------------------
- Fetches all submissions from your LeetCode account.
- Supports historical backfill with original submission timestamps in Git.
- Preserves MULTIPLE submissions per problem as distinct files (solution_1.py, solution_2.py, solution_3.cpp, etc.).
- Generates informative README.md documentation for each problem.
- Incremental sync: only processes new submissions on subsequent runs.
"""

import os
import sys
import json
import time
import subprocess
from datetime import datetime, timezone
from pathlib import Path
from typing import Dict, List, Any, Optional

# Ensure UTF-8 output on Windows consoles
if sys.platform == "win32":
    try:
        sys.stdout.reconfigure(encoding="utf-8")
        sys.stderr.reconfigure(encoding="utf-8")
    except Exception:
        pass

try:
    import requests
except ImportError:
    print("Error: 'requests' package not found. Please run: pip install requests")
    sys.exit(1)

LEETCODE_URL = "https://leetcode.com"
GRAPHQL_URL = f"{LEETCODE_URL}/graphql"
ALL_PROBLEMS_URL = f"{LEETCODE_URL}/api/problems/all/"

LANG_EXTENSIONS: Dict[str, str] = {
    "python": ".py",
    "python3": ".py",
    "cpp": ".cpp",
    "c": ".c",
    "csharp": ".cs",
    "java": ".java",
    "javascript": ".js",
    "typescript": ".ts",
    "golang": ".go",
    "rust": ".rs",
    "ruby": ".rb",
    "swift": ".swift",
    "kotlin": ".kt",
    "scala": ".scala",
    "php": ".php",
    "racket": ".rkt",
    "erlang": ".erl",
    "elixir": ".ex",
    "dart": ".dart",
    "mysql": ".sql",
    "mssql": ".sql",
    "oraclesql": ".sql",
    "postgresql": ".sql",
    "pythondata": ".py",
    "bash": ".sh",
}

DIFFICULTY_MAP = {1: "Easy", 2: "Medium", 3: "Hard"}
DIFFICULTY_BADGES = {
    "Easy": "🟢 **Easy**",
    "Medium": "🟡 **Medium**",
    "Hard": "🔴 **Hard**",
}


def load_config() -> Dict[str, Any]:
    """Load configuration from config.json or environment variables."""
    config = {
        "leetcode_session": os.environ.get("LEETCODE_SESSION", ""),
        "csrf_token": os.environ.get("LEETCODE_CSRF_TOKEN", ""),
        "output_dir": os.environ.get("OUTPUT_DIR", "problems"),
        "git_commit": os.environ.get("GIT_COMMIT", "true").lower() in ("true", "1", "yes"),
        "accepted_only": os.environ.get("ACCEPTED_ONLY", "true").lower() in ("true", "1", "yes"),
        "delay_seconds": float(os.environ.get("DELAY_SECONDS", "0.5")),
    }

    config_file = Path("config.json")
    if config_file.exists():
        try:
            with open(config_file, "r", encoding="utf-8-sig") as f:
                user_config = json.load(f)
                config.update({k: v for k, v in user_config.items() if v})
        except Exception as e:
            print(f"Warning: Could not parse config.json: {e}")

    return config


class LeetCodeClient:
    def __init__(self, session_cookie: str, csrf_token: str, delay: float = 0.5):
        self.session_cookie = session_cookie.strip()
        self.csrf_token = csrf_token.strip()
        self.delay = delay
        self.session = requests.Session()
        self.session.headers.update({
            "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.0.0 Safari/537.36",
            "Referer": "https://leetcode.com/",
            "Origin": "https://leetcode.com",
            "Content-Type": "application/json",
            "Cookie": f"LEETCODE_SESSION={self.session_cookie}; csrftoken={self.csrf_token};",
            "x-csrftoken": self.csrf_token,
        })

    def request_with_retry(self, method: str, url: str, **kwargs) -> Optional[requests.Response]:
        """Perform HTTP request with retries and exponential backoff."""
        max_retries = 3
        backoff = 2
        for attempt in range(1, max_retries + 1):
            try:
                time.sleep(self.delay)
                resp = self.session.request(method, url, **kwargs)
                if resp.status_code == 200:
                    return resp
                elif resp.status_code in (429, 403):
                    wait = backoff ** attempt
                    print(f"Rate limited or forbidden ({resp.status_code}). Waiting {wait}s before retry...")
                    time.sleep(wait)
                else:
                    print(f"HTTP {resp.status_code} for {url}. Attempt {attempt}/{max_retries}")
            except Exception as e:
                print(f"Request error: {e}. Attempt {attempt}/{max_retries}")
                time.sleep(backoff ** attempt)
        return None

    def verify_auth(self) -> Optional[str]:
        """Check if credentials are valid and get username."""
        query = """
        query globalData {
            userStatus {
                isSignedIn
                username
            }
        }
        """
        payload = {"query": query, "variables": {}}
        resp = self.request_with_retry("POST", GRAPHQL_URL, json=payload)
        if not resp:
            return None
        try:
            data = resp.json()
            user_status = data.get("data", {}).get("userStatus", {})
            if user_status.get("isSignedIn"):
                return user_status.get("username")
        except Exception:
            pass
        return None

    def get_solved_problems(self) -> List[Dict[str, Any]]:
        """Fetch all solved problems using the all problems API."""
        print("Fetching list of all solved problems...")
        resp = self.request_with_retry("GET", ALL_PROBLEMS_URL)
        if not resp:
            print("Failed to fetch problem list.")
            return []

        data = resp.json()
        stat_pairs = data.get("stat_status_pairs", [])
        solved = []

        for pair in stat_pairs:
            status = pair.get("status")
            if status == "ac":
                stat = pair.get("stat", {})
                difficulty_level = pair.get("difficulty", {}).get("level", 1)
                solved.append({
                    "id": stat.get("frontend_question_id") or stat.get("question_id"),
                    "title": stat.get("question__title"),
                    "slug": stat.get("question__title_slug"),
                    "difficulty": DIFFICULTY_MAP.get(difficulty_level, "Easy"),
                })

        try:
            solved.sort(key=lambda x: int(x["id"]))
        except (ValueError, TypeError):
            solved.sort(key=lambda x: str(x["id"]))

        print(f"Found {len(solved)} solved problems.")
        return solved

    def get_problem_submissions(self, question_slug: str) -> List[Dict[str, Any]]:
        """Fetch all submissions for a specific problem."""
        query = """
        query submissionList($questionSlug: String!, $offset: Int, $limit: Int) {
            submissionList(questionSlug: $questionSlug, offset: $offset, limit: $limit) {
                submissions {
                    id
                    statusDisplay
                    lang
                    runtime
                    timestamp
                    url
                }
                hasNext
            }
        }
        """
        submissions = []
        offset = 0
        limit = 20
        has_next = True

        while has_next:
            variables = {"questionSlug": question_slug, "offset": offset, "limit": limit}
            resp = self.request_with_retry("POST", GRAPHQL_URL, json={"query": query, "variables": variables})
            if not resp:
                break
            try:
                data = resp.json()
                sub_list = data.get("data", {}).get("submissionList", {})
                subs = sub_list.get("submissions", [])
                submissions.extend(subs)
                has_next = sub_list.get("hasNext", False) and len(subs) == limit
                offset += limit
            except Exception as e:
                print(f"Error parsing submissions for {question_slug}: {e}")
                break

        return submissions

    def get_submission_details(self, submission_id: int) -> Optional[Dict[str, Any]]:
        """Fetch submission source code and metadata."""
        query = """
        query submissionDetails($submissionId: Int!) {
            submissionDetails(submissionId: $submissionId) {
                code
                timestamp
                statusDisplay
                lang {
                    name
                    verboseName
                }
                runtime
                memory
            }
        }
        """
        payload = {"query": query, "variables": {"submissionId": int(submission_id)}}
        resp = self.request_with_retry("POST", GRAPHQL_URL, json=payload)
        if not resp:
            return None
        try:
            data = resp.json()
            return data.get("data", {}).get("submissionDetails")
        except Exception as e:
            print(f"Error parsing submission details {submission_id}: {e}")
            return None


class SyncManager:
    def __init__(self, client: LeetCodeClient, config: Dict[str, Any]):
        self.client = client
        self.config = config
        self.output_dir = Path(config.get("output_dir", "problems"))
        self.output_dir.mkdir(parents=True, exist_ok=True)
        self.state_file = Path(".synced_submissions.json")
        self.synced_ids = self.load_synced_ids()

    def load_synced_ids(self) -> set:
        """Load already synced submission IDs."""
        if self.state_file.exists():
            try:
                with open(self.state_file, "r", encoding="utf-8") as f:
                    return set(json.load(f))
            except Exception:
                return set()
        return set()

    def save_synced_ids(self):
        """Save synced submission IDs."""
        with open(self.state_file, "w", encoding="utf-8") as f:
            json.dump(list(self.synced_ids), f, indent=2)

    def format_code_header(self, problem: Dict[str, Any], sub: Dict[str, Any], solution_num: int, ext: str) -> str:
        """Add metadata comment block at the top of the code file."""
        ts = int(sub.get("timestamp", 0))
        dt_str = datetime.fromtimestamp(ts, tz=timezone.utc).strftime("%Y-%m-%d %H:%M:%S UTC")
        lang_name = sub.get("lang", {}).get("verboseName") or sub.get("lang", {}).get("name") or "Unknown"
        runtime = sub.get("runtime", "N/A")
        memory = sub.get("memory", "N/A")

        header_lines = [
            f"LeetCode Problem #{problem['id']}: {problem['title']}",
            f"URL: https://leetcode.com/problems/{problem['slug']}/",
            f"Solution #{solution_num} ({lang_name})",
            f"Status: {sub.get('statusDisplay', 'Accepted')}",
            f"Runtime: {runtime}",
            f"Memory: {memory}",
            f"Submission Date: {dt_str}",
            f"Submission ID: {sub.get('id', 'N/A')}",
        ]

        if ext in (".py", ".rb", ".sh", ".rkt", ".ex"):
            cmt = '"""\n' + "\n".join(header_lines) + '\n"""\n\n'
        elif ext in (".sql",):
            cmt = "\n".join([f"-- {line}" for line in header_lines]) + "\n\n"
        elif ext in (".erl",):
            cmt = "\n".join([f"% {line}" for line in header_lines]) + "\n\n"
        else:
            cmt = "/*\n * " + "\n * ".join(header_lines) + "\n */\n\n"

        return cmt

    def update_problem_readme(self, problem_dir: Path, problem: Dict[str, Any], solutions: List[Dict[str, Any]]):
        """Generate or update the problem directory README.md."""
        readme_path = problem_dir / "README.md"
        difficulty = problem.get("difficulty", "Easy")
        badge = DIFFICULTY_BADGES.get(difficulty, difficulty)

        content = [
            f"# {problem['id']}. {problem['title']}",
            "",
            f"**Difficulty:** {badge}  ",
            f"**Problem Link:** [{problem['title']}](https://leetcode.com/problems/{problem['slug']}/)",
            "",
            "## Solutions",
            "",
            "| # | File | Language | Runtime | Memory | Submitted At |",
            "| :--- | :--- | :--- | :--- | :--- | :--- |",
        ]

        for s in solutions:
            ts = int(s.get("timestamp", 0))
            dt_str = datetime.fromtimestamp(ts, tz=timezone.utc).strftime("%Y-%m-%d %H:%M")
            lang = s.get("lang_name", "Unknown")
            runtime = s.get("runtime", "N/A")
            memory = s.get("memory", "N/A")
            filename = s.get("filename", "")
            num = s.get("solution_num", 1)
            content.append(f"| {num} | [`{filename}`](./{filename}) | {lang} | {runtime} | {memory} | {dt_str} |")

        content.append("")
        with open(readme_path, "w", encoding="utf-8") as f:
            f.write("\n".join(content))

    def git_commit_submission(self, problem: Dict[str, Any], sub_meta: Dict[str, Any]):
        """Commit files with original submission timestamp in Git."""
        if not self.config.get("git_commit", True):
            return

        ts = int(sub_meta.get("timestamp", time.time()))
        dt_iso = datetime.fromtimestamp(ts, tz=timezone.utc).strftime("%Y-%m-%dT%H:%M:%SZ")

        env = os.environ.copy()
        env["GIT_AUTHOR_DATE"] = dt_iso
        env["GIT_COMMITTER_DATE"] = dt_iso

        msg = f"[LeetCode #{problem['id']}] {problem['title']} - Solution {sub_meta['solution_num']} ({sub_meta['lang_name']})"

        try:
            subprocess.run(["git", "add", "."], check=True, capture_output=True, env=env)
            subprocess.run(["git", "commit", "-m", msg], check=True, capture_output=True, env=env)
        except subprocess.CalledProcessError:
            pass

    def sync(self):
        """Execute synchronization process."""
        user = self.client.verify_auth()
        if not user:
            print("Authentication failed! Please check your LEETCODE_SESSION and csrftoken.")
            return

        print(f"Logged in as LeetCode user: '{user}'")

        solved_problems = self.client.get_solved_problems()
        if not solved_problems:
            print("No solved problems found or API error.")
            return

        all_submissions_to_process = []

        print(f"Fetching submissions for {len(solved_problems)} problems...")
        for idx, problem in enumerate(solved_problems, 1):
            print(f"[{idx}/{len(solved_problems)}] Checking #{problem['id']} - {problem['title']}...")
            raw_subs = self.client.get_problem_submissions(problem["slug"])

            if self.config.get("accepted_only", True):
                raw_subs = [s for s in raw_subs if s.get("statusDisplay") == "Accepted"]

            try:
                raw_subs.sort(key=lambda s: int(s.get("timestamp", 0)))
            except (ValueError, TypeError):
                pass

            for sub_index, sub in enumerate(raw_subs, 1):
                all_submissions_to_process.append({
                    "problem": problem,
                    "sub_summary": sub,
                    "solution_num": sub_index,
                    "timestamp": int(sub.get("timestamp", 0)),
                })

        all_submissions_to_process.sort(key=lambda x: x["timestamp"])

        print(f"\nTotal submissions found across all solved problems: {len(all_submissions_to_process)}")
        new_count = sum(1 for item in all_submissions_to_process if str(item["sub_summary"]["id"]) not in self.synced_ids)
        print(f"New submissions to download and commit: {new_count}\n")

        problem_solutions_map: Dict[str, List[Dict[str, Any]]] = {}

        for item in all_submissions_to_process:
            problem = item["problem"]
            sub_sum = item["sub_summary"]
            sub_id = str(sub_sum["id"])
            solution_num = item["solution_num"]
            slug = problem["slug"]
            prob_id_padded = str(problem["id"]).zfill(4)
            folder_name = f"{prob_id_padded}-{slug}"
            problem_dir = self.output_dir / folder_name
            problem_dir.mkdir(parents=True, exist_ok=True)

            lang_key = sub_sum.get("lang", "").lower()
            ext = LANG_EXTENSIONS.get(lang_key, ".txt")
            filename = f"solution_{solution_num}{ext}"

            if sub_id in self.synced_ids:
                if slug not in problem_solutions_map:
                    problem_solutions_map[slug] = []
                problem_solutions_map[slug].append({
                    "solution_num": solution_num,
                    "filename": filename,
                    "lang_name": sub_sum.get("lang", "Unknown"),
                    "runtime": sub_sum.get("runtime", "N/A"),
                    "memory": sub_sum.get("memory", "N/A"),
                    "timestamp": item["timestamp"],
                })
                continue

            print(f"Downloading #{problem['id']} {problem['title']} (Solution #{solution_num}, ID: {sub_id})...")
            details = self.client.get_submission_details(int(sub_id))
            if not details:
                print(f"  Failed to fetch code for submission ID {sub_id}")
                continue

            details["id"] = sub_id
            code = details.get("code", "")
            lang_obj = details.get("lang", {})
            lang_name = lang_obj.get("verboseName") or lang_obj.get("name") or sub_sum.get("lang", "unknown")
            lang_identifier = lang_obj.get("name") or sub_sum.get("lang", "")
            ext = LANG_EXTENSIONS.get(lang_identifier.lower(), ".txt")

            filename = f"solution_{solution_num}{ext}"
            file_path = problem_dir / filename

            header = self.format_code_header(problem, details, solution_num, ext)
            with open(file_path, "w", encoding="utf-8") as f:
                f.write(header + code)

            if slug not in problem_solutions_map:
                problem_solutions_map[slug] = []
            sol_meta = {
                "solution_num": solution_num,
                "filename": filename,
                "lang_name": lang_name,
                "runtime": details.get("runtime", "N/A"),
                "memory": details.get("memory", "N/A"),
                "timestamp": item["timestamp"],
            }
            problem_solutions_map[slug].append(sol_meta)

            self.update_problem_readme(problem_dir, problem, problem_solutions_map[slug])
            self.git_commit_submission(problem, sol_meta)

            self.synced_ids.add(sub_id)
            self.save_synced_ids()

        print("\n==========================================")
        print("✅ Synchronization completed successfully!")
        print(f"📁 Problem folders saved in: '{self.output_dir.resolve()}'")
        print("==========================================")


def main():
    print("=== LeetCode to GitHub Sync Tool ===")
    config = load_config()

    if not config.get("leetcode_session") or not config.get("csrf_token"):
        print("\nMissing LeetCode credentials.")
        print("Please provide your LEETCODE_SESSION and csrftoken in 'config.json' or as environment variables.\n")
        session_input = input("Enter LEETCODE_SESSION cookie: ").strip()
        csrf_input = input("Enter csrftoken cookie: ").strip()
        if not session_input or not csrf_input:
            print("Credentials required. Exiting.")
            sys.exit(1)
        config["leetcode_session"] = session_input
        config["csrf_token"] = csrf_input

    client = LeetCodeClient(
        session_cookie=config["leetcode_session"],
        csrf_token=config["csrf_token"],
        delay=config.get("delay_seconds", 0.5),
    )
    manager = SyncManager(client, config)
    manager.sync()


if __name__ == "__main__":
    main()
