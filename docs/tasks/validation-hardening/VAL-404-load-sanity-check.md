# [VAL-404] Load/latency sanity check, redirect path

| Field | Value |
|---|---|
| **Type** | Task |
| **Module** | Validation & Hardening |
| **Epic / Phase** | Phase 4 — Validation |
| **Status** | Not Started |
| **Priority** | Low |
| **Depends on** | BF-203 |
| **Blocks** | — |
| **AI Work Log** | docs/ai-work-log/entries/VAL-404.md |

## Summary

Lightweight sanity check (not a full load-testing setup) that the cached redirect path
(`BF-203`) actually improved p50/p95 latency versus the uncached Phase 1 baseline, and that
AMB-304's click recording didn't quietly erase that gain.

## Acceptance Criteria

- [ ] A simple benchmark (e.g., a small script or `@Test` using a timing harness) records
      before/after latency for `GET /{code}` — before = pre-BF-203 baseline, after = current
- [ ] Result documented in `docs/architecture/05-nfr-and-scalability.md` (Performance row)
- [ ] If AMB-304's click recording measurably regresses redirect latency, flagged back as a
      defect against AMB-304 — not silently accepted

## AI Collaboration Plan

- **Intent**: Produce a lightweight, honest latency comparison — not a rigorous production load
  test (out of scope for this exercise).
- **Constraints**: Keep tooling simple (no new heavy dependency for this alone).
- **Technical context**: BF-203, AMB-304.

## Definition of Done

- [ ] Benchmark run and result recorded
- [ ] `05-nfr-and-scalability.md` updated
- [ ] AI Work Log entry closed

## Dev Notes

*(fill in once complete)*

## Related

- Risk: R-005 (docs/risks/performance-scalability.md)
