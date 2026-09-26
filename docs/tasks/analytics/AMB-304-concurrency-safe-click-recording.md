# [AMB-304] Concurrency-safe click recording

| Field | Value |
|---|---|
| **Type** | Task |
| **Module** | Analytics (Ambiguous Scenario) |
| **Epic / Phase** | Phase 3 — Ambiguous Requirement |
| **Status** | Not Started |
| **Priority** | High |
| **Depends on** | AMB-302, GF-103 |
| **Blocks** | AMB-305 |
| **AI Work Log** | docs/ai-work-log/entries/AMB-304.md |

## Summary

Wire click recording into the existing redirect path without lost updates under healthy
concurrent traffic (R-006). Caching is not a prerequisite. Refine this task after AMB-301;
the criteria below assume a simple counter and must match the chosen scope before coding.

## Impact Analysis

- Modules/files affected: existing redirect service and chosen analytics storage
- Existing behavior that must not change: redirect status codes/headers; cache behavior only
  if a cache actually exists by then

## Acceptance Criteria

- [ ] Click recording uses an atomic DB-level increment (e.g., `UPDATE ... SET count = count +
      1`), not a read-modify-write in application code
- [ ] Record the observed latency impact and AMB-301's chosen recording approach; no
      asynchronous infrastructure or undefined "no noticeable latency" requirement
- [ ] Recording failure (e.g., transient DB error) does not fail the redirect itself — the
      redirect succeeds even if analytics recording is best-effort (explicit trade-off, recorded
      in Dev Notes)

## AI Collaboration Plan

- **Intent**: Add correct click recording using AMB-301's chosen failure and timing behavior.
- **Constraints**: Must not turn the redirect into a read-modify-write on the same row it's
  redirecting from; must not fail the redirect if recording fails.
- **Acceptance criteria**: as listed above.
- **Technical context**: GF-103 (redirect path), AMB-301 (scope), AMB-302 (schema); BF-203 only if implemented.

## Definition of Done

- [ ] Code implemented per acceptance criteria
- [ ] Quality gates passed
- [ ] AI Work Log entry closed
- [ ] `docs/risks/data-integrity.md` R-006 updated with mitigation reference

## Dev Notes

*(fill in once complete, including the explicit trade-off decision on recording-failure behavior)*

## Related

- Risk: R-006 (docs/risks/data-integrity.md)
