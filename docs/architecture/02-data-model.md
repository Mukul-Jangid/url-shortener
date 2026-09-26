# Data Model

Status: **planned, not yet implemented.** This is the target shape for `GF-101`; update this
file the moment the entity is actually created, including any deviation from what's below and
why (link the deviation to the task's dev notes rather than re-explaining it here).

## `ShortUrl` (implemented)

| Field | Type | Notes |
|---|---|---|
| `id` | `Long` (PK) | Surrogate key |
| `code` | `String`, unique, indexed | 7-character Base62 short code |
| `originalUrl` | `String` | Target web address |
| `createdAt` | `Instant` | Creation timestamp |
| `active` | `boolean` | Soft-deactivation status flag |
| `clickCount` | `long` | Total atomic redirect count (AMB-302) |
| `lastAccessedAt` | `Instant` | Timestamp of most recent redirect access (AMB-302) |

## Schema evolution log

### 2026-09-26 — AMB-302 — Added click count and last accessed timestamp fields
- Change: Added `click_count` (`long`, default 0) and `last_accessed_at` (`Instant`, nullable) columns to `short_urls` table.
- Reason: Track total redirect clicks and access recency per ADR 0007 / AMB-301.
- Migration approach: Hibernate `ddl-auto=update` for in-memory H2 database.
- Concurrency: Atomic SQL update `UPDATE short_urls SET click_count = click_count + 1, last_accessed_at = :now WHERE code = :code`.

### 2026-09-26 — GF-101 — Initial ShortUrl entity
- Change: Created `short_urls` table (`id`, `code`, `original_url`, `created_at`, `active`).
- Reason: Base persistence for URL shortener service.
