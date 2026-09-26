# [BF-202] Refine ID generation when justified

| Field | Value |
|---|---|
| **Type** | Task |
| **Module** | ID Generation Hardening (Brownfield) |
| **Epic / Phase** | Phase 2 — Brownfield |
| **Status** | Not Started |
| **Priority** | High |
| **Depends on** | BF-201 |
| **Blocks** | BF-204, BF-205 |
| **AI Work Log** | docs/ai-work-log/entries/BF-202.md |

## Summary

Candidate generator improvement, selected only after BF-201 justifies a change. The initial
generator already has bounded retries. A counter is one option, not an automatic security
improvement. **High-impact task — requires Reviewer Decision: APPROVED.**

## Description

Before implementation, choose the strategy from BF-201 evidence and record it in a new ADR.
Compare keeping bounded random generation with alternatives. Base62 encoding or a fixed offset
does not establish unpredictability. Account for codes already stored before any migration.

## Impact Analysis

*(carried over from BF-201's findings — do not re-derive from scratch; reference it)*

- Modules/files affected: *(fill from BF-201 output)*
- Existing behavior that must not change: `POST /api/v1/urls` response shape; `GET /{code}`
  contract per BF-201's findings
- Tests that must still pass unmodified (assertions unchanged, setup may change): all GF-102 and
  GF-103 integration tests

## Acceptance Criteria

- [ ] Finalize measurable acceptance criteria for the selected improvement before coding
- [ ] Existing mappings remain usable; uniqueness and predictability trade-offs are documented
      and tested as applicable; do not assume migration cannot collide with existing codes
- [ ] `POST /api/v1/urls` and `GET /{code}` external contracts unchanged (verified by BF-204)
- [ ] `docs/decisions/0003-id-generation-strategy.md` marked superseded; new decision file added

## AI Collaboration Plan

- **Intent**: Implement the generator improvement chosen after BF-201; preserve the public contract.
- **Constraints**: No change to `POST`/`GET` request/response shapes; preserve uniqueness and bounded completion; evaluate R-003 (enumeration).
- **Acceptance criteria**: as listed above.
- **Technical context**: BF-201's impact map, `GF-101`/`GF-102`.

## Definition of Done

- [ ] Code implemented per acceptance criteria
- [ ] Tests updated (internals) — BF-204 covers external contract regression separately
- [ ] Quality gates passed, including the manual security review checklist
- [ ] AI Work Log entry closed **with Reviewer Decision: APPROVED**
- [ ] New decision file added, 0003 marked superseded

## Dev Notes

*(fill in once complete)*

## Related

- Decision: 0003-id-generation-strategy.md (to be superseded)
- Risk: R-001, R-002, R-003
