# AI Work Log — Index

**Purpose**: turns "I used Claude/Copilot" into demonstrable, reviewable AI-assisted engineering.
Every AI interaction that produces or influences anything in `src/` (or a task's scope) gets its
own entry file under `entries/`, named by task ID. This folder is the audit trail.

## Why one file per task instead of one growing log

A single shared log becomes hard to search once there are entries across many unrelated tasks. A
future contributor (or agent) picking up, say, `BF-202` should be able to open
`entries/BF-202.md` directly and get the full provenance for that piece of code, without reading
unrelated history.

## Entry lifecycle

1. Copy `template.md` to `entries/<TASK-ID>.md` when a task moves to `In Progress`.
2. Fill in Intent / Constraints / Acceptance Criteria / Technical Context *before* prompting.
3. After each significant AI interaction for that task, append a dated sub-entry (a task may
   involve several rounds of prompting — log each round, don't collapse them into one).
4. Close the entry with a final `Engineer Decision` and, for high-impact changes, an explicit
   `Reviewer Decision: APPROVED` line.

## High-impact change list (requires explicit Reviewer Decision)

See `docs/conventions/ai-usage-rules.md` — reproduced here for convenience:
- Authentication/authorization
- Redirect target validation logic (open-redirect surface)
- Short-code / ID generation strategy
- Database schema changes
- Public API request/response contract changes
- Rate-limiting/abuse-control logic

## Entries

| Task ID | File | Status |
|---|---|---|
| SCAFFOLD-000 | [entries/SCAFFOLD-000.md](entries/SCAFFOLD-000.md) | Closed |
| SCAFFOLD-001 | [entries/SCAFFOLD-001.md](entries/SCAFFOLD-001.md) | Closed (docs restructuring) |
| SCAFFOLD-002 | [entries/SCAFFOLD-002.md](entries/SCAFFOLD-002.md) | Open (guidelines in progress) |
| SCAFFOLD-004 | [entries/SCAFFOLD-004.md](entries/SCAFFOLD-004.md) | Open (setup verified; engineer review pending) |

*(new rows added here as tasks start — keep this table in sync with `entries/`)*
