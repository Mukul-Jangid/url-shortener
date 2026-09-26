# Tasks — Index (Board)

Every task is its own spec file under a module folder, using `_template/TASK-TEMPLATE.md`.
This index is the board view: status at a glance, with links into the detail files. Update this
table whenever a task's status changes — the detail file is the source of truth for content, this
file is the source of truth for "what's the current state of everything."

## Modules (folders)

| Module folder | Maps to phase | Purpose |
|---|---|---|
| `core-url-management/` | Phase 1 — Greenfield | Core create/redirect/lookup/deactivate APIs |
| `id-generation-hardening/` | Phase 2 — Brownfield | Refactor ID generation + redirect caching |
| `analytics/` | Phase 3 — Ambiguous | "Add analytics" — disambiguation through implementation |
| `validation-hardening/` | Phase 4 — Validation | Cross-cutting risk mitigation (rate limiting, redirect validation) |
| `documentation/` | Phase 5 — Docs | README, final summary |

## Board

| ID | Title | Module | Status | Depends on |
|---|---|---|---|---|
| SCAFFOLD-000 | Project + guardrails scaffold | — | Done | — |
| SCAFFOLD-002 | Establish incremental development guidelines | setup | In Progress | Existing scaffold |
| SCAFFOLD-003 | Verify scaffold build and health check | setup | Not Started | SCAFFOLD-002 |
| SCAFFOLD-004 | Set up local and GitHub repository | setup | In Progress | GitHub authentication |
| GF-101 | `ShortUrl` domain entity + repository | core-url-management | Not Started | SCAFFOLD-000 |
| GF-102 | `POST /api/v1/urls` — create short URL | core-url-management | Not Started | GF-101 |
| GF-103 | `GET /{code}` — redirect endpoint | core-url-management | Not Started | GF-101 |
| GF-104 | `GET /api/v1/urls/{code}` — metadata lookup | core-url-management | Not Started | GF-101 |
| GF-105 | `DELETE /api/v1/urls/{code}` — deactivate | core-url-management | Not Started | GF-101 |
| GF-106 | Input validation + centralized error handling | core-url-management | Not Started | GF-102, GF-103 |
| GF-107 | Unit + integration tests, Phase 1 | core-url-management | Not Started | GF-102..GF-106 |
| GF-108 | OpenAPI/schema definitions, Phase 1 | core-url-management | Not Started | GF-102..GF-106 |
| BF-201 | Impact analysis: ID generation + redirect path | id-generation-hardening | Not Started | Phase 1 complete |
| BF-202 | Refactor: counter-based Base62 ID generation | id-generation-hardening | Not Started | BF-201 |
| BF-203 | Caching layer in front of redirect lookup | id-generation-hardening | Not Started | BF-201 |
| BF-204 | Regression tests: Phase 1 contract unchanged | id-generation-hardening | Not Started | BF-202, BF-203 |
| BF-205 | Update architecture/decisions docs post-refactor | id-generation-hardening | Not Started | BF-202 |
| AMB-301 | Requirement disambiguation: "add analytics" | analytics | Not Started | Phase 1 complete |
| AMB-302 | Data model for chosen analytics scope | analytics | Not Started | AMB-301 |
| AMB-303 | `GET /api/v1/urls/{code}/analytics` endpoint | analytics | Not Started | AMB-302 |
| AMB-304 | Concurrency-safe click recording | analytics | Not Started | AMB-302, BF-203 |
| AMB-305 | Concurrency correctness tests | analytics | Not Started | AMB-304 |
| VAL-401 | Threat/risk pass (formalize risk register) | validation-hardening | Not Started | Phase 1 |
| VAL-402 | Rate limiting on create endpoint | validation-hardening | Not Started | VAL-401 |
| VAL-403 | Redirect target validation (anti open-redirect) | validation-hardening | Not Started | VAL-401 |
| VAL-404 | Load/latency sanity check, redirect path | validation-hardening | Not Started | BF-203 |
| DOC-501 | README setup/run instructions | documentation | Not Started | Phase 1 |
| DOC-502 | FINAL_SUMMARY.md | documentation | Not Started | All prior phases |

## Sequencing rationale

Phase 1 must exist before Phase 2 — can't reason about a brownfield change without a real
existing codebase to reason about. Phase 3 (analytics) is sequenced after Phase 1/2 core
stabilizes because click recording depends on the redirect path (`BF-203`/`BF-204`) being
settled; recording clicks against a code path still being refactored would conflate two kinds of
change in one task, which undermines clean traceability in the AI work log.

## How to use this during development

1. Pick the next unblocked task (dependencies satisfied) from the board.
2. Open its detail file, fill in "AI Collaboration Plan" if not already precise enough.
3. Update Status here to `In Progress` at the same time you open the work-log entry.
4. When Done, fill in the task's "Dev Notes" in its detail file — that's what makes it useful
   later — and flip Status here to `Done`.
