# API Design

## Conventions

- Base path: `/api/v1` for management endpoints. The redirect endpoint itself is intentionally
  unversioned (`GET /{code}`) since it's the public-facing short link and must stay stable.
- JSON request/response bodies, `Content-Type: application/json`.
- Errors: RFC 7807 `ProblemDetail` — see `docs/conventions/error-handling.md`.
- No entity is ever returned directly; every response goes through a `dto/` class.

## Endpoint inventory

| Endpoint | Method | Purpose | Status | Task |
|---|---|---|---|---|
| `/api/v1/urls` | `POST` | Create a short URL from a long URL | Not started | `GF-102` |
| `/{code}` | `GET` | Redirect to the original long URL | Not started | `GF-103` |
| `/api/v1/urls/{code}` | `GET` | Fetch metadata (no redirect) | Not started | `GF-104` |
| `/api/v1/urls/{code}` | `DELETE` | Deactivate a short URL | Not started | `GF-105` |
| `/api/v1/urls/{code}/analytics` | `GET` | Click analytics | Not started | `AMB-303` |

## Contract stability rule

Once an endpoint's request/response shape is implemented and tested (Phase 1), later phases
(brownfield refactors in particular) must not silently change it. `BF-204` exists specifically
to prove this with regression tests. If a contract change is genuinely required, it goes through
`docs/decisions/` as a new decision, not as an incidental side effect of a refactor.

## Schema definitions

Once implemented, request/response schemas will be captured as OpenAPI annotations directly in
the controllers (springdoc-openapi), with the generated spec linked here rather than
hand-duplicated in Markdown (to avoid the two drifting apart).
