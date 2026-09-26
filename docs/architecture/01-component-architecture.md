# Component Architecture

## Layering

```
                          ┌─────────────────────┐
                          │      Client          │
                          └──────────┬───────────┘
                                     │ HTTP
                          ┌──────────▼───────────┐
                          │   controller/         │  REST endpoints, request validation,
                          │   (Web layer)         │  DTO <-> domain mapping
                          └──────────┬───────────┘
                                     │
                          ┌──────────▼───────────┐
                          │   service/            │  Business rules: code generation,
                          │   (Domain logic)      │  expiry checks, analytics recording
                          └──────────┬───────────┘
                                     │
                          ┌──────────▼───────────┐
                          │   repository/         │  Spring Data JPA repositories
                          └──────────┬───────────┘
                                     │
                          ┌──────────▼───────────┐
                          │   H2 (dev) / Postgres │  see docs/decisions/0002-persistence-choice.md
                          │   (prod, proposed)    │
                          └───────────────────────┘
```

Dependency rule: a class only depends "downward." Services never depend on controllers;
repositories never depend on services. Enforced by convention + code review, not tooling, for
this project's size — see `docs/conventions/package-structure.md`.

## Package responsibilities

| Package | Responsibility | Must NOT contain |
|---|---|---|
| `controller/` | HTTP concerns, request validation, DTO mapping | Business rules, direct repository access |
| `service/` | Business rules (code generation, expiry, analytics logic) | HTTP-specific types, JPA annotations |
| `repository/` | Data access | Business logic |
| `domain/` | JPA entities / core model | DTOs, HTTP concerns |
| `dto/` | API request/response payloads | Persistence annotations |
| `exception/` | Domain exceptions + centralized `@ControllerAdvice` mapping | Business logic |
| `config/` | Cross-cutting Spring config (caching, rate limiting) | Feature-specific business logic |

## Status of each component (updated as tasks land)

| Component | Status | Landed in task |
|---|---|---|
| `domain.ShortUrl` entity | Not started | `tasks/core-url-management/GF-101-*.md` |
| Create endpoint | Not started | `tasks/core-url-management/GF-102-*.md` |
| Redirect endpoint | Not started | `tasks/core-url-management/GF-103-*.md` |
| Caching layer | Not started | `tasks/id-generation-hardening/BF-203-*.md` |
| Analytics module | Not started | `tasks/analytics/` |
| Rate limiting | Not started | `tasks/validation-hardening/VAL-402-*.md` |

This table is the fastest way for a future contributor (or agent) to answer "does X exist yet,
and which task built it."
