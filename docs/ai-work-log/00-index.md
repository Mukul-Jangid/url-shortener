# AI Work Log — Index

**Purpose**: turns "I used Claude/Copilot" into demonstrable, reviewable AI-assisted engineering.
Each task that changes `src/`, documentation, or task scope gets one entry file under
`entries/`, named by task ID. Meaningful interactions are rounds within that file. This folder is the audit trail.

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
| SCAFFOLD-000 | [entries/SCAFFOLD-000.md](entries/SCAFFOLD-000.md) | Open (historical review pending; build verification in SCAFFOLD-003) |
| SCAFFOLD-001 | [entries/SCAFFOLD-001.md](entries/SCAFFOLD-001.md) | Open (historical docs review pending) |
| SCAFFOLD-002 | [entries/SCAFFOLD-002.md](entries/SCAFFOLD-002.md) | Open (guidelines verified; engineer review pending) |
| SCAFFOLD-003 | [entries/SCAFFOLD-003.md](entries/SCAFFOLD-003.md) | Open (Java 21 build setup) |
| SCAFFOLD-004 | [entries/SCAFFOLD-004.md](entries/SCAFFOLD-004.md) | Open (setup verified; engineer review pending) |
| GF-101 | [entries/GF-101.md](entries/GF-101.md) | Closed (`ShortUrl` domain entity & repository) |
| GF-106 | [entries/GF-106.md](entries/GF-106.md) | Open (Input validation & centralized error handling) |
| GF-102 | [entries/GF-102.md](entries/GF-102.md) | Open (`POST /api/v1/urls` Create Short URL API) |
| GF-108 | [entries/GF-108.md](entries/GF-108.md) | Open (OpenAPI & Swagger UI integration) |
| GF-103 | [entries/GF-103.md](entries/GF-103.md) | Open (`GET /{code}` Redirect Endpoint) |
| GF-104 | [entries/GF-104.md](entries/GF-104.md) | Open (`GET /api/v1/urls/{code}` Metadata Lookup Endpoint) |

*(new rows added here as tasks start — keep this table in sync with `entries/`)*
