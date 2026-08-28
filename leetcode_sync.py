#!/usr/bin/env python3
"""
LeetCode to GitHub Synchronizer
--------------------------------
- Fetches all submissions from your LeetCode account.
- Supports historical backfill with original submission timestamps in Git.
- Preserves MULTIPLE submissions per problem as distinct files (solution_1.py, solution_2.py, solution_3.cpp, etc.).
- Generates informative README.md documentation for each problem AND the main root portfolio README.md index with numeric auto-sorting!
- Incremental sync: only processes new submissions on subsequent runs.
"""

import os
import sys
import json
import time
import re
import subprocess
from datetime import datetime, timezone
from pathlib import Path
from typing import Dict, List, Any, Optional

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
                    time.sleep(wait)
            except Exception:
                time.sleep(backoff ** attempt)
        return None

    def verify_auth(self) -> Optional[str]:
        query = """
        query globalData {
            userStatus {
                isSignedIn
                username
            }
        }
        """
        resp = self.request_with_retry("POST", GRAPHQL_URL, json={"query": query, "variables": {}})
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
        resp = self.request_with_retry("GET", ALL_PROBLEMS_URL)
        if not resp:
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

        return solved

    def get_problem_submissions(self, question_slug: str) -> List[Dict[str, Any]]:
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
            except Exception:
                break

        return submissions

    def get_submission_details(self, submission_id: int) -> Optional[Dict[str, Any]]:
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
        resp = self.request_with_retry("POST", GRAPHQL_URL, json={"query": query, "variables": {"submissionId": int(submissionId)}})
        if not resp:
            return None
        try:
            data = resp.json()
            return data.get("data", {}).get("submissionDetails")
        except Exception:
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
        if self.state_file.exists():
            try:
                with open(self.state_file, "r", encoding="utf-8") as f:
                    return set(json.load(f))
            except Exception:
                return set()
        return set()

    def save_synced_ids(self):
        with open(self.state_file, "w", encoding="utf-8") as f:
            json.dump(list(self.synced_ids), f, indent=2)

    def format_code_header(self, problem: Dict[str, Any], sub: Dict[str, Any], solution_num: int, ext: str) -> str:
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

    def update_root_readme(self):
        """Regenerate root portfolio README.md with strict numeric sorting."""
        folders = sorted(
            [f for f in self.output_dir.iterdir() if f.is_dir()],
            key=lambda x: int(x.name.split("-")[0]) if x.name.split("-")[0].isdigit() else 999999,
        )

        rows = []
        diff_counts = {"Easy": 0, "Medium": 0, "Hard": 0}
        total_solutions = 0

        for folder in folders:
            readme = folder / "README.md"
            title = folder.name
            difficulty = "Easy"
            prob_id = folder.name.split("-")[0].lstrip("0") or "0"

            if readme.exists():
                text = readme.read_text(encoding="utf-8", errors="ignore")
                m_title = re.search(r"#\s*(\d+)\.\s*(.+)", text)
                if m_title:
                    prob_id = m_title.group(1)
                    title = m_title.group(2).strip()
                if "Hard" in text:
                    difficulty = "Hard"
                elif "Medium" in text:
                    difficulty = "Medium"
                elif "Easy" in text:
                    difficulty = "Easy"

            diff_counts[difficulty] = diff_counts.get(difficulty, 0) + 1

            # Natural numeric sort for solution files: solution_1, solution_2, solution_3...
            sol_files = [f.name for f in folder.iterdir() if f.is_file() and f.name.startswith("solution_")]
            def sol_sort_key(s):
                m = re.search(r"solution_(\d+)", s)
                return int(m.group(1)) if m else 0
            sol_files.sort(key=sol_sort_key)

            total_solutions += len(sol_files)

            sol_links = ", ".join([f"[`{s}`](./problems/{folder.name}/{s})" for s in sol_files])

            diff_badge = {
                "Easy": "🟢 Easy",
                "Medium": "🟡 Medium",
                "Hard": "🔴 Hard",
            }.get(difficulty, difficulty)

            rows.append({
                "id": int(prob_id) if prob_id.isdigit() else 999999,
                "id_str": prob_id,
                "title": title,
                "folder": folder.name,
                "difficulty": diff_badge,
                "solutions": sol_links,
            })

        # Strict ascending numeric sort
        rows.sort(key=lambda x: x["id"])
        total_solved = len(rows)
        easy_count = diff_counts.get("Easy", 0)
        medium_count = diff_counts.get("Medium", 0)
        hard_count = diff_counts.get("Hard", 0)

        header = f"""# 🎯 LeetCode Solutions

<div align="center">

[![LeetCode Profile](https://img.shields.io/badge/LeetCode-Profile-FFA116?style=for-the-badge&logo=leetcode&logoColor=black)](https://leetcode.com/u/FjYI1cEg6C/)
[![Total Solved](https://img.shields.io/badge/Problems%20Solved-{total_solved}-blue?style=for-the-badge&logo=codeforces)](./problems)
[![Total Submissions](https://img.shields.io/badge/Total%20Solutions-{total_solutions}-brightgreen?style=for-the-badge)](./problems)

[![Easy](https://img.shields.io/badge/Easy-{easy_count}-28a745?style=flat-square)]()
[![Medium](https://img.shields.io/badge/Medium-{medium_count}-ffc107?style=flat-square)]()
[![Hard](https://img.shields.io/badge/Hard-{hard_count}-dc3545?style=flat-square)]()
[![Automated Sync](https://img.shields.io/badge/Instant%20Sync-Active-success?style=flat-square&logo=githubactions&logoColor=white)]()

An automated repository synchronizing all my LeetCode submissions with real-time browser push, multi-solution versioning, and authentic historical timestamps.

</div>

---

## 🌟 Highlights

- **⚡ Instant Direct Push**: Submissions are pushed directly to GitHub the millisecond they are marked **Accepted** on LeetCode via the browser extension.
- **📁 Multi-Solution Tracking**: When a problem is solved with multiple approaches (e.g. brute force, two-pointers, hash map, or different languages), all attempts are preserved as individual files (`solution_1.java`, `solution_2.java`, `solution_3.cpp`) without overwriting!
- **📅 Historical Timeline**: Preserves authentic LeetCode submission timestamps across GitHub commits.
- **🤖 Cloud Backup**: Automated GitHub Action workflow running every 6 hours to ensure continuous 100% sync reliability.

---

## 📊 Solved Problems Index

| # | Problem Title | Difficulty | Solutions |
| :---: | :--- | :---: | :--- |
"""
        table_rows = []
        for r in rows:
            table_rows.append(f"| {r['id_str']} | [{r['title']}](./problems/{r['folder']}) | {r['difficulty']} | {r['solutions']} |")

        footer = """

---

<div align="center">
<i>Automatically synchronized & maintained with ❤️ using LeetCode Instant Sync</i>
</div>
"""
        full_readme = header + "\n".join(table_rows) + footer
        with open("README.md", "w", encoding="utf-8") as f:
            f.write(full_readme.strip() + "\n")

    def git_commit_submission(self, problem: Dict[str, Any], sub_meta: Dict[str, Any]):
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
        user = self.client.verify_auth()
        if not user:
            print("Authentication failed! Please check your credentials.")
            return

        print(f"Logged in as LeetCode user: '{user}'")
        solved_problems = self.client.get_solved_problems()
        if not solved_problems:
            print("No solved problems found.")
            return

        all_submissions_to_process = []
        for problem in solved_problems:
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

            details = self.client.get_submission_details(int(sub_id))
            if not details:
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

        self.update_root_readme()
        print("Synchronization completed successfully!")


def main():
    config = load_config()
    client = LeetCodeClient(
        session_cookie=config["leetcode_session"],
        csrf_token=config["csrf_token"],
        delay=config.get("delay_seconds", 0.5),
    )
    manager = SyncManager(client, config)
    manager.sync()


if __name__ == "__main__":
    main()
