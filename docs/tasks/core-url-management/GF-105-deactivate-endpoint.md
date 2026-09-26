# [GF-105] `DELETE /api/v1/urls/{code}` — deactivate

| Field | Value |
|---|---|
| **Type** | Story |
| **Module** | Core URL Management |
| **Epic / Phase** | Phase 1 — Greenfield Core |
| **Status** | Not Started |
| **Priority** | Medium |
| **Depends on** | GF-104 |
| **Blocks** | GF-107 |
| **AI Work Log** | docs/ai-work-log/entries/GF-105.md |

## Summary

Soft-delete a short URL (sets `active = false`) rather than hard-deleting, so historical
analytics data and the row itself aren't destroyed.

## Acceptance Criteria

- [ ] `DELETE /api/v1/urls/{code}` sets `active = false` and returns `204`
- [ ] Idempotent: deactivating an already-inactive code still returns `204`, not an error
- [ ] Returns `404` for a code that has never existed
- [ ] After deactivation, `GF-103`'s redirect endpoint returns `404` for that code (cross-task
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

- [ ] Code implemented per acceptance criteria
- [ ] Tests written and passing (including the cross-task redirect check)
- [ ] Quality gates passed
- [ ] AI Work Log entry closed

## Dev Notes

*(fill in once complete)*

## Related

- —
