# Git Workflow and Branching Conventions

This document defines how git branches and commits are managed for this project.

## 1. Branch Naming Strategy

Every branch must have a prefix indicating its purpose, followed by the Task ID and a brief description:

* **Feature Branches** (`feature/<TASK-ID>-<short-description>`):
  Use for all application feature development (e.g., `feature/GF-101-short-url-entity`, `feature/GF-102-create-url-api`).
* **Fix Branches** (`fix/<TASK-ID>-<short-description>`):
  Use for bug fixes or defect corrections (e.g., `fix/GF-106-validation-fix`).
* **Documentation & Setup Branches** (`docs/<topic-description>`):
  Use for pure documentation, setup guidelines, or repository infrastructure updates (e.g., `docs/incremental-guidelines`, `docs/setup-verification`).

## 2. Branching & Merging Lifecycle

1. **Pre-Development Plan Discussion**: Before writing any code, discuss and align on the implementation plan (scope, files to create/modify, acceptance criteria, and constraints) with the developer.
2. **Branch off `main`**: Create a dedicated branch from `main` matching the naming convention (e.g., `feature/<TASK-ID>-<short-description>`).
3. **Develop & Verify Locally**: Implement code, write unit/integration tests, and run quality gates (`./mvnw clean test spotless:check`).
4. **Developer Approval Before Commit/Push**: Present the local verification results, summary of changes, and diff to the developer. **Do not commit or push to git without explicit developer approval.**
5. **Move Task to `In Review` & PR/Merge**: Upon approval, commit with a standard message structure, push the branch, and move task to `In Review` for merge to `main`.

## 3. Commit Message Structure

Commit messages must be concise and use plain, simple language:

```text
<TASK-ID>: <plain, simple summary of what was changed>

[Optional Body]
AI-assisted: see docs/ai-work-log/entries/<TASK-ID>.md
```

### Examples:
* `GF-101: create ShortUrl entity and repository with integration tests`
* `SCAFFOLD-003: verify Java 21 build setup and add Maven wrapper`
* `DOC-501: update README with local startup instructions`

## 4. Key Rules

* **Pre-Development Plan Required**: Never begin coding without first presenting and discussing the implementation plan with the developer.
* **STRICT RULE — NO AUTOMATIC COMMITS OR PUSHES**: The AI agent MUST NOT execute `git commit` or `git push` under any circumstances without first presenting the local changes/diff to the developer and receiving explicit approval.
* **One Task per Feature Branch**: Never mix multiple unrelated tasks into a single branch.
* **No Feature Code on Docs Branches**: Do not write application feature code directly on `docs/` branches. Keep documentation and setup branches strictly focused on docs.

## 5. Branching Strategy for Dependent vs. Independent Tasks

* **Independent Tasks**: Branch directly off `main` (e.g., `git checkout main && git checkout -b feature/<TASK-ID>-<description>`).
* **Dependent Tasks (Chained Feature Branches)**:
  When a task depends on an earlier unmerged feature (e.g., `GF-106` depending on `GF-101`), chain the next feature branch directly off the parent feature branch:
  ```bash
  git checkout feature/GF-101-short-url-entity
  git checkout -b feature/GF-106-validation-and-error-handling
  ```
  * **Rebase Policy**: Once the parent feature branch (`feature/GF-101-short-url-entity`) is merged into `main`, rebase the chained child feature branch onto `main`:
    ```bash
    git checkout feature/GF-106-validation-and-error-handling
    git rebase main
    ```

