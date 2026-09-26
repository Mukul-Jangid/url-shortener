# [BF-204] Regression tests: Phase 1 contract unchanged

| Field | Value |
|---|---|
| **Type** | Task |
| **Module** | ID Generation Hardening (Brownfield) |
| **Epic / Phase** | Phase 2 — Brownfield |
| **Status** | Not Started |
| **Priority** | High |
| **Depends on** | BF-202, BF-203 |
| **Blocks** | BF-205, Phase 2 completion |
| **AI Work Log** | docs/ai-work-log/entries/BF-204.md |

## Summary

Prove, with tests, that Phase 1's external API contract is unchanged despite the internal
refactors in BF-202/BF-203. This is the task that gives the brownfield scenario a concrete,
checkable "did we actually not break anything" answer, per
`docs/conventions/quality-gates.md`'s "Regression check" gate.

## Acceptance Criteria

- [ ] Every GF-102/GF-103 test from Phase 1 still passes with assertions unchanged (only
      setup/mocking may differ due to internal refactor)
- [ ] A new end-to-end test exercises: create → redirect (cached) → deactivate → redirect
      (expect 404) as one continuous flow
- [ ] No response DTO field added/removed/renamed as a side effect of BF-202/BF-203

## AI Collaboration Plan

- **Intent**: Verify, don't assume — confirm contract stability with tests, not review alone.
- **Constraints**: Do not modify BF-202/BF-203 implementation as part of this task — if a
  regression is found, it's logged as a defect and routed back to the originating task, not
  silently patched here.
- **Acceptance criteria**: as listed above.
- **Technical context**: BF-202, BF-203, all Phase 1 test files.

## Definition of Done

- [ ] All regression tests passing
- [ ] Any regressions found are documented and routed back to BF-202/BF-203 (not silently fixed
      in this task)
- [ ] Quality gates passed
- [ ] AI Work Log entry closed

## Dev Notes

*(fill in once complete)*

## Related

- —
