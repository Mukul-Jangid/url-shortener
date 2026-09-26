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

1. **Branch off `main`**: Always create your feature or documentation branch from the latest `main` branch.
2. **Develop & Verify locally**: Implement code, write unit/integration tests, and run quality gates (`./mvnw clean test spotless:check`).
3. **Move Task to `In Review`**: Update the task file and task board status.
4. **Merge to `main`**: Once accepted by the engineer, merge the branch into `main`.

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

* **One Task per Feature Branch**: Never mix multiple unrelated tasks into a single branch.
* **No Unrelated Code on Docs Branches**: Do not write application feature code directly on `docs/` branches. Keep documentation and setup branches strictly focused on docs.
