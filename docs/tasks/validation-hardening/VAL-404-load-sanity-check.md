# [VAL-404] Load/latency sanity check, redirect path

| Field | Value |
|---|---|
| **Type** | Task |
| **Module** | Validation & Hardening |
| **Epic / Phase** | Phase 4 — Validation |
| **Status** | Not Started |
| **Priority** | Low |
| **Depends on** | GF-107 |
| **Blocks** | — |
| **AI Work Log** | docs/ai-work-log/entries/VAL-404.md |

## Summary

Measure a simple redirect baseline before optimizing. If caching or analytics is later
selected, repeat the same workload to compare latency. Record environment and workload so
measurements can be interpreted; no heavy load-testing framework is needed.

## Acceptance Criteria

- [ ] A lightweight benchmark records redirect p50/p95, workload, environment, and baseline
- [ ] If comparing a selected change, use the same workload before/after; do not require
      caching merely to run this task or assume an improvement in advance
- [ ] Result documented in `docs/architecture/05-nfr-and-scalability.md` (Performance row)
- [ ] If AMB-304's click recording measurably regresses redirect latency, flagged back as a
      defect against AMB-304 — not silently accepted

## AI Collaboration Plan

- **Intent**: Produce a lightweight, honest latency comparison — not a rigorous production load
  test (out of scope for this exercise).
- **Constraints**: Keep tooling simple (no new heavy dependency for this alone).
- **Technical context**: current redirect implementation; BF-203/AMB-304 only if implemented.

## Definition of Done

- [ ] Benchmark run and result recorded
- [ ] `05-nfr-and-scalability.md` updated
- [ ] AI Work Log entry closed

## Dev Notes

*(fill in once complete)*

## Related

- Risk: R-005 (docs/risks/performance-scalability.md)
