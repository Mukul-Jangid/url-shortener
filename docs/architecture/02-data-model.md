# Data Model

Status: **planned, not yet implemented.** This is the target shape for `GF-101`; update this
file the moment the entity is actually created, including any deviation from what's below and
why (link the deviation to the task's dev notes rather than re-explaining it here).

## `ShortUrl` (planned)

| Field | Type | Notes |
|---|---|---|
| `id` | `Long` (PK) | Surrogate key |
| `code` | `String`, unique, indexed | The short code (see `docs/decisions/0003-id-generation-strategy.md`) |
| `originalUrl` | `String` | Basic syntax and HTTP/HTTPS validation in GF-102/GF-106; later policy review in VAL-403 |
| `createdAt` | `Instant` | |
| `active` | `boolean` | Soft-delete flag for `DELETE /api/v1/urls/{code}` |

Analytics fields are excluded from GF-101. AMB-301 decides their scope and AMB-302 adds the
chosen model later; do not add a placeholder counter now.

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
