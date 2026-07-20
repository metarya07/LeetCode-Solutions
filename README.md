# 🚀 LeetCode to GitHub Auto-Sync (Multi-Solution & Historical Backfill)

Automatically synchronizes your LeetCode submissions to GitHub:
- **Preserves Multiple Submissions**: If you submit 3 solutions for the same problem (e.g. brute force, optimized, or different languages), all of them are saved as individual files (`solution_1.py`, `solution_2.py`, `solution_3.cpp`) without overwriting!
- **Backfills Historical Submissions**: Imports all your past solved questions with their genuine historical submission dates in Git commits (so your GitHub contribution graph accurately reflects when you solved them).
- **Auto-Sync via GitHub Actions**: Once pushed to GitHub, an automated workflow syncs newly solved problems every 6 hours without needing to run anything locally.

---

## 📁 Repository Structure

```
leetcode-to-github/
├── problems/
│   ├── 0001-two-sum/
│   │   ├── README.md             # Difficulty, problem link, and summary table of solutions
│   │   ├── solution_1.py         # 1st Accepted Submission
│   │   ├── solution_2.py         # 2nd Accepted Submission (e.g. optimized hash map)
│   │   └── solution_3.cpp        # 3rd Accepted Submission (e.g. C++ version)
│   └── 0002-add-two-numbers/
│       ├── README.md
│       └── solution_1.py
├── .github/
│   └── workflows/
│       └── leetcode_sync.yml     # Automated cloud sync
├── config.example.json           # Template configuration
├── leetcode_sync.py              # Main synchronization script
└── requirements.txt
```

---

## 🔑 Step 1: Get Your LeetCode Cookies (30 Seconds)

1. Open your browser and log into [LeetCode](https://leetcode.com).
2. Press `F12` (or Right-Click -> **Inspect**) to open Developer Tools.
3. Go to the **Network** tab.
4. Refresh the page or click on any problem.
5. Click on any network request going to `leetcode.com` (e.g. `graphql` or `all/`).
6. In the **Headers** -> **Request Headers** section:
   - Find `Cookie:` and copy the value of `LEETCODE_SESSION` (e.g. `eyJ0eXAi...`).
   - Find `csrftoken` (e.g. `AbCdEf123...`).

---

## 💻 Step 2: Initial Historical Backfill (Run Locally)

1. **Install requirements**:
   ```bash
   pip install -r requirements.txt
   ```

2. **Configure your credentials**:
   Copy `config.example.json` to `config.json`:
   ```bash
   cp config.example.json config.json
   ```
   Paste your `LEETCODE_SESSION` and `csrftoken` into `config.json`.

3. **Initialize Git Repository (if not already done)**:
   ```bash
   git init
   ```

4. **Run the Sync**:
   ```bash
   python leetcode_sync.py
   ```
   *The script will fetch all your solved questions, create the folder structure, save every submission version as a distinct file, and make timestamped Git commits matching the original submission times!*

5. **Push to your GitHub repository**:
   ```bash
   git remote add origin https://github.com/<your-username>/<your-repo-name>.git
   git branch -M main
   git push -u origin main
   ```

---

## 🤖 Step 3: Set Up Automated Cloud Sync (GitHub Actions)

To keep syncing automatically whenever you solve problems in the future:

1. Go to your repository on GitHub.
2. Click **Settings** -> **Secrets and variables** -> **Actions**.
3. Click **New repository secret** and add:
   - Name: `LEETCODE_SESSION` | Value: *(Your LeetCode session cookie)*
   - Name: `LEETCODE_CSRF_TOKEN` | Value: *(Your csrftoken value)*
4. Go to **Settings** -> **Actions** -> **General** -> **Workflow permissions**, and select **"Read and write permissions"** (so the action can push commits).
5. That's it! GitHub Actions will now automatically check for and commit new submissions every 6 hours. You can also trigger it manually from the **Actions** tab anytime by clicking **Run workflow**.
