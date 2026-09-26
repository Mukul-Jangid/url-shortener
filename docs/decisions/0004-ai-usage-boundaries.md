# 0004: AI usage boundaries for this project

- **Date**: 2026-09-26
- **Status**: Accepted
- **Task context**: Project setup (`SCAFFOLD-000`); binding on every subsequent task.

## Context

The exercise requires "secure AI usage" and "explicit engineer ownership." That needs a concrete,
checkable rule set — not just a stated intention — or it's not actually verifiable by a reviewer.

## Decision

1. No real credentials, secrets, or production data are ever placed in a prompt (none exist in
   this project, but the rule is recorded regardless, for when it matters).
2. AI output touching security-sensitive logic (auth, redirect target validation, ID generation,
   rate limiting) requires an explicit human `Reviewer Decision: APPROVED` entry in the relevant
   `docs/ai-work-log/entries/` file before merge — no exceptions.
3. All AI-authored or AI-assisted code is attributed as such in the relevant work-log entry.
   There's no requirement to tag it in commit messages beyond a pointer to the log entry — the
   log is the single source of truth for provenance.
4. The engineer, not the AI, decides when a task is `Done`.
5. New third-party dependencies suggested by AI require a one-line justification in the work-log
   entry that introduced them.

## Alternatives considered

- **Lighter-touch logging** (only log "significant" changes) — rejected because partial
  traceability undermines the exact thing this exercise is evaluating.

## Consequences

More process overhead per task than "just accept the AI's output." That overhead is the point —
it's what turns AI assistance into something a reviewer can actually audit and trust.

## Status

Accepted.
