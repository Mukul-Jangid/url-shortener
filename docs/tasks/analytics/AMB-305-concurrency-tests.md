# [AMB-305] Concurrency correctness tests

| Field | Value |
|---|---|
| **Type** | Task |
| **Module** | Analytics (Ambiguous Scenario) |
| **Epic / Phase** | Phase 3 — Ambiguous Requirement |
| **Status** | Not Started |
| **Priority** | High |
| **Depends on** | AMB-304 |
| **Blocks** | Phase 3 completion |
| **AI Work Log** | docs/ai-work-log/entries/AMB-305.md |

## Summary

Prove AMB-304's atomicity claim with a real concurrent-access test — not just a single-threaded
happy path, per `docs/conventions/testing.md`.

## Acceptance Criteria

- [ ] With healthy storage and a known initial count, N concurrent successful redirects
      increase the count by exactly N; test injected recording failures separately under
      AMB-301's chosen failure policy
- [ ] Test confirms redirect responses all succeed even under concurrent load

## AI Collaboration Plan

- **Intent**: Write a genuine concurrency test, not a mocked-out simulation of one.
- **Constraints**: Must use real concurrent threads/requests against the actual increment logic,
  not a unit test that mocks away the concurrency question entirely.
- **Acceptance criteria**: as listed above.
- **Technical context**: AMB-304.

## Definition of Done

- [ ] Test written, passing, and demonstrably would fail against a naive read-modify-write
      implementation (sanity-checked by temporarily reverting AMB-304's atomicity, confirming
      the test catches it, then re-applying — noted in Dev Notes)
- [ ] Quality gates passed
- [ ] AI Work Log entry closed
- [ ] `docs/tasks/00-index.md` board updated to reflect Phase 3 complete

## Dev Notes

*(fill in once complete)*

## Related

- Risk: R-006 (docs/risks/data-integrity.md)
