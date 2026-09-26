# Tasks — board

Start with [development guidelines](../conventions/development-workflow.md). The board owns
status and sequence; each linked task owns its scope and acceptance criteria. Update both
when status or dependencies change. Historical phase numbers preserve the original exercise
categories; the iterations below determine execution order.

## Current sequence

1. **Iteration 0 — Guidelines and setup:** SCAFFOLD-002 establishes the rules. SCAFFOLD-004
   sets up GitHub at the user's request. SCAFFOLD-003 then proves the existing scaffold builds
   and starts; no feature implementation before this check.
2. **Iteration 1 — Create and redirect:** GF-101 → GF-106 → GF-102 → GF-103. Demonstrate creating
   a link, following it, and receiving useful invalid-input/unknown-code errors. Tests, basic
   validation, and README examples belong to these tasks.
3. **Iteration 2 — Manage links:** GF-104 → GF-105 → GF-107. Demonstrate metadata lookup,
   deactivation, and a subsequent 404 redirect. GF-107 checks coverage already written.
4. **Later iterations:** Select and refine a small useful outcome after the core works.
   GF-108, analytics, brownfield changes, and deployment hardening remain candidates. Keeping
   their IDs preserves the exercise plan; it does not require all their proposed designs.

Analytics depends on its scope decision and the working redirect, not on a cache. BF-201 must
justify a refactor before BF-202/BF-203 starts. VAL-404 can measure the uncached baseline.
Review abuse/deployment requirements in VAL-401 before proposing public deployment.
DOC-501 and BF-205 audit ongoing documentation; DOC-502 summarizes selected delivered work
and explicitly records deferrals.

## Historical scaffold status

SCAFFOLD-000 files exist, but build verification and engineer acceptance were never recorded;
its earlier Done label was unsupported. Verification is now SCAFFOLD-003. SCAFFOLD-001's docs
exist but its engineer review is pending. Their original work logs remain unchanged as history.

## Board

| ID | Title | Iteration | Status | Depends on |
|---|---|---|---|---|
| [SCAFFOLD-002](setup/SCAFFOLD-002-development-guidelines.md) | Establish incremental development guidelines | 0 — Setup | In Review | Existing scaffold and documentation |
| [SCAFFOLD-004](setup/SCAFFOLD-004-git-repository.md) | Set up the Git repository | 0 — Setup | In Review | GitHub account authentication |
| [SCAFFOLD-003](setup/SCAFFOLD-003-verify-build.md) | Verify the scaffold before feature work | 0 — Setup | In Review | SCAFFOLD-002 |
| [GF-101](core-url-management/GF-101-short-url-entity-and-repository.md) | `ShortUrl` domain entity + repository | 1 — Create and redirect | In Review | SCAFFOLD-003 |
| [GF-106](core-url-management/GF-106-validation-and-error-handling.md) | Input validation + centralized error handling | 1 — Create and redirect | In Review | GF-101 |
| [GF-102](core-url-management/GF-102-create-short-url-api.md) | `POST /api/v1/urls` — create short URL | 1 — Create and redirect | In Review | GF-101, GF-106 |
| [GF-103](core-url-management/GF-103-redirect-endpoint.md) | `GET /{code}` — redirect endpoint | 1 — Create and redirect | In Review | GF-102 |
| [GF-104](core-url-management/GF-104-metadata-lookup.md) | `GET /api/v1/urls/{code}` — metadata lookup | 2 — Manage links | In Review | GF-103 |
| [GF-105](core-url-management/GF-105-deactivate-endpoint.md) | `DELETE /api/v1/urls/{code}` — deactivate | 2 — Manage links | In Review | GF-104 |
| [GF-107](core-url-management/GF-107-phase1-tests.md) | Unit + integration tests, Phase 1 | 2 — Manage links | In Review | GF-102, GF-103, GF-104, GF-105, GF-106 |
| [GF-108](core-url-management/GF-108-openapi-schema.md) | OpenAPI/schema definitions, Phase 1 | 1 — Create and redirect | In Review | GF-102 |
| [BF-201](short-code-refactoring/BF-201-impact-analysis.md) | Impact analysis: short code generation + redirect path | Later candidate | Not Started | Phase 1 complete (GF-107) |
| [BF-202](short-code-refactoring/BF-202-refactor-id-generation.md) | Refine short code generation when justified | Later candidate | Not Started | BF-201 |
| [BF-203](short-code-refactoring/BF-203-caching-layer.md) | Caching layer in front of redirect lookup | Later candidate | Not Started | BF-201 |
| [BF-204](short-code-refactoring/BF-204-regression-tests.md) | Regression tests: Phase 1 contract unchanged | Later candidate | Not Started | BF-202, BF-203 |
| [BF-205](short-code-refactoring/BF-205-update-docs.md) | Update architecture/decisions docs post-refactor | Later candidate | Not Started | BF-202 |
| [AMB-301](analytics/AMB-301-requirement-disambiguation.md) | Requirement disambiguation: "add analytics" | Later candidate | Not Started | Phase 1 complete |
| [AMB-302](analytics/AMB-302-analytics-data-model.md) | Data model for chosen analytics scope | Later candidate | Not Started (blocked pending AMB-301 scope decision) | AMB-301 |
| [AMB-303](analytics/AMB-303-analytics-endpoint.md) | `GET /api/v1/urls/{code}/analytics` endpoint | Later candidate | Not Started (blocked pending AMB-301/AMB-302) | AMB-302 |
| [AMB-304](analytics/AMB-304-concurrency-safe-click-recording.md) | Concurrency-safe click recording | Later candidate | Not Started | AMB-302, GF-103 |
| [AMB-305](analytics/AMB-305-concurrency-tests.md) | Concurrency correctness tests | Later candidate | Not Started | AMB-304 |
| [VAL-401](validation-hardening/VAL-401-threat-risk-pass.md) | Threat/risk pass (formalize risk register) | Later candidate | Not Started | Phase 1 |
| [VAL-402](validation-hardening/VAL-402-rate-limiting.md) | Rate limiting on create endpoint | Later candidate | Not Started | VAL-401 |
| [VAL-403](validation-hardening/VAL-403-redirect-target-validation.md) | Review additional destination policy | Later candidate | Not Started | VAL-401 |
| [VAL-404](validation-hardening/VAL-404-load-sanity-check.md) | Load/latency sanity check, redirect path | Later candidate | Not Started | GF-107 |
| [DOC-501](documentation/DOC-501-readme.md) | README setup/run instructions | Later candidate | Not Started (initial version already exists from SCAFFOLD-000; this task finalizes it) | Phase 1 |
| [DOC-502](documentation/DOC-502-final-summary.md) | FINAL_SUMMARY.md | Later candidate | Not Started | All selected delivery tasks |

## Before selecting a later candidate

Its older technical notes are starting proposals, not settled implementation requirements.
Confirm the need, refine scope, update dependencies and acceptance criteria, and record the
choice before coding. In particular, BF-204/BF-205 must depend only on the brownfield changes
actually selected; AMB-302 through AMB-305 must match AMB-301's decision.

Use In Review only after applicable checks pass. The engineer owns Done; do not treat repository
creation, a commit, or a push as automatic acceptance of the source or the guidelines.
