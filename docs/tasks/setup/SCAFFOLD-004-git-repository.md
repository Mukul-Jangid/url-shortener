# [SCAFFOLD-004] Set up the Git repository

| Field | Value |
|---|---|
| **Type** | Task |
| **Module** | Setup |
| **Status** | In Progress |
| **Depends on** | GitHub account authentication |
| **AI Work Log** | [SCAFFOLD-004](../../ai-work-log/entries/SCAFFOLD-004.md) |

## Summary

Initialize this project locally and create a repository on the user's GitHub account, as
requested on 2026-09-26. Use `url-shortener` and private visibility unless directed otherwise.

## Acceptance Criteria

- [x] Local repository initialized with `main` as its branch.
- [ ] GitHub account authenticated and repository created.
- [ ] Project snapshot committed, pushed, and linked to `origin/main`.
- [ ] Remote URL, visibility, and branch synchronization verified.

## Scope and verification

Preserve all existing project files, including the guidelines work in progress. Exclude build
output using the existing `.gitignore`. No feature or build changes. Inspect the GitHub account,
repository metadata, local status, and remote branch hash to verify setup.

## Dev Notes

Git was available with the user's name and email configured. GitHub CLI was absent; installed
the official Linux release to `~/.local/bin/gh` and verified its release checksum. Browser-based
device authentication is required to connect the user's account. Guidelines work in
SCAFFOLD-002 is still in progress and will resume after repository setup.

## Definition of Done

All acceptance criteria verified and engineer acceptance recorded under the existing policy.
