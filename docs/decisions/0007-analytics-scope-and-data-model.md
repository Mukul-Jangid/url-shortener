# 0007. Analytics Scope and Data Model Choice

- **Status**: Accepted
- **Date**: 2026-09-26
- **Deciders**: Engineering Lead (Developer), AI Assistant
- **Task Context**: [AMB-301](../../tasks/analytics/AMB-301-requirement-disambiguation.md), [AMB-302](../../tasks/analytics/AMB-302-analytics-data-model.md)

## Context and Problem Statement

The initial project requirement specified adding "analytics" to the URL shortener service without defining the exact metric depth, granularity, privacy boundaries, or storage architecture. We need to disambiguate this requirement and commit to a data model and execution model before writing code.

## Candidate Options Evaluated

1. **Option 1: Simple Atomic Counter (`clickCount` + `lastAccessedAt`)**
   - Track total click count (`long`) and timestamp of most recent successful redirect (`Instant`).
   - Store as direct columns on the primary `short_urls` table.
   - Increment count using atomic SQL query: `UPDATE short_urls SET click_count = click_count + 1, last_accessed_at = :now WHERE code = :code`.

2. **Option 2: Time-Series / Hourly Breakdown Table**
   - Create a separate `short_url_analytics` table with hourly or daily aggregated buckets (`hour_timestamp`, `click_count`).

3. **Option 3: Full Per-Click Log (IP, User-Agent, Referrer, Geo)**
   - Record an individual event row per redirect containing client IP address, User-Agent header, referrer URL, and geolocation.

## Decision Outcome

**Chosen Option**: **Option 1 (Simple Atomic Counter)**.

### Rationale

- **Performance ($O(1)$ Atomic Write)**: Directly updating the `click_count` column on the indexed `short_urls` table requires no table joins and executes in sub-millisecond time.
- **Privacy & Security (R-012 Compliance)**: Storing client IPs or User-Agents introduces GDPR/PII compliance burdens and security risks (R-012). Option 1 collects aggregate access counts with zero privacy liability.
- **Concurrency Safety (R-006 Mitigation)**: An atomic SQL query (`UPDATE ... SET count = count + 1`) delegates lock isolation to the database engine page buffer, preventing application-level lost updates under concurrent redirect traffic.
- **Fault-Tolerant Redirect Path**: Click counter updates are best-effort. If a transient database write delay occurs, the error is logged, but the primary `302 Found` redirect response succeeds without interruption.

## Consequences

### Positive
- Zero risk of lost updates during high concurrency.
- Zero PII storage or compliance risks.
- `GET /api/v1/urls/{code}/analytics` endpoint responds quickly with `ShortUrlAnalyticsResponse` DTO.

### Negative / Trade-offs
- Granular hourly/daily trend graphs are not supported in Phase 1 (deferred to future time-series tasks if requested).
