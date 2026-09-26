# [GF-105] `DELETE /api/v1/urls/{code}` — deactivate

| Field | Value |
|---|---|
| **Type** | Story |
| **Module** | Core URL Management |
| **Epic / Phase** | Phase 1 — Greenfield Core |
| **Status** | In Review |
| **Priority** | Medium |
| **Depends on** | GF-104 |
| **Blocks** | GF-107 |
| **AI Work Log** | docs/ai-work-log/entries/GF-105.md |

## Summary

Soft-delete a short URL (sets `active = false`) rather than hard-deleting, so historical
analytics data and the row itself aren't destroyed.

## Acceptance Criteria

- [x] `DELETE /api/v1/urls/{code}` sets `active = false` and returns `204`
- [x] Idempotent: deactivating an already-inactive code still returns `204`, not an error
- [x] Returns `404` for a code that has never existed
- [x] After deactivation, `GF-103`'s redirect endpoint returns `404` for that code (cross-task
      acceptance check — write an integration test spanning both)

## Technical Notes / Constraints

- Hard delete is explicitly out of scope — do not implement it without a new decision recorded
  in `docs/decisions/`.

## AI Collaboration Plan

- **Intent**: Implement soft-delete deactivation, idempotent.
- **Constraints**: No hard delete; must be idempotent.
- **Acceptance criteria**: as listed above.
- **Technical context**: `GF-101`, `GF-103` (for the cross-task test).

## Definition of Done

- [x] Code implemented per acceptance criteria
- [x] Tests written and passing (including the cross-task redirect check)
- [x] Quality gates passed
- [x] AI Work Log entry closed

## Dev Notes

*(fill in once complete)*

## Related

- —
