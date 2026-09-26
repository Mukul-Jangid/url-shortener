# Data Model

Status: **planned, not yet implemented.** This is the target shape for `GF-101`; update this
file the moment the entity is actually created, including any deviation from what's below and
why (link the deviation to the task's dev notes rather than re-explaining it here).

## `ShortUrl` (planned)

| Field | Type | Notes |
|---|---|---|
| `id` | `Long` (PK) | Surrogate key |
| `code` | `String`, unique, indexed | The short code (see `docs/decisions/0003-id-generation-strategy.md`) |
| `originalUrl` | `String` | Validated on write (see `tasks/validation-hardening/VAL-403-*.md`) |
| `createdAt` | `Instant` | |
| `active` | `boolean` | Soft-delete flag for `DELETE /api/v1/urls/{code}` |
| `clickCount` | `long` | Only if analytics scope (AMB-301) settles on simple counting; may move to a separate table if a richer breakdown is chosen |

## Schema evolution log

Record every schema change here, in order, once implementation starts:

```
[none yet — first entry lands with GF-101]
```

Format for each entry:
```
### YYYY-MM-DD — <task ID> — <one-line summary>
- Change: (e.g., "added `active` boolean column")
- Reason:
- Migration approach: (e.g., Hibernate ddl-auto=update for this exercise; note if that's a
  known limitation vs. a real migration tool like Flyway/Liquibase)
```

## Open question flagged for `AMB-302`

If the analytics scope chosen in `AMB-301` requires per-click detail (referrer, timestamp,
device) rather than a simple counter, `clickCount` on `ShortUrl` becomes insufficient and a
separate `ClickEvent` entity will be needed. Don't pre-build it before AMB-301 concludes —
that would be scope creep ahead of the disambiguation this project is supposed to demonstrate.
