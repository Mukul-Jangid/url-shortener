# [AMB-304] Concurrency-safe click recording

| Field | Value |
|---|---|
| **Type** | Task |
| **Module** | Analytics (Ambiguous Scenario) |
| **Epic / Phase** | Phase 3 — Ambiguous Requirement |
| **Status** | Not Started |
| **Priority** | High |
| **Depends on** | AMB-302, BF-203 |
| **Blocks** | AMB-305 |
| **AI Work Log** | docs/ai-work-log/entries/AMB-304.md |

## Summary

Wire click recording into the redirect hot path (`GET /{code}`, already cached per BF-203)
without introducing lost updates under concurrent traffic (R-006).

## Impact Analysis

- Modules/files affected: `service` layer for redirect lookup (touches the same method BF-203
  optimized — sequencing matters, see `docs/tasks/00-index.md`)
- Existing behavior that must not change: redirect status codes/headers; cache behavior from BF-203

## Acceptance Criteria

- [ ] Click recording uses an atomic DB-level increment (e.g., `UPDATE ... SET count = count +
      1`), not a read-modify-write in application code
- [ ] Recording a click does not add noticeable latency to the redirect response (measured, not
      assumed — coordinate with VAL-404)
- [ ] Recording failure (e.g., transient DB error) does not fail the redirect itself — the
      redirect succeeds even if analytics recording is best-effort (explicit trade-off, recorded
      in Dev Notes)

## AI Collaboration Plan

- **Intent**: Add atomic, non-blocking-to-the-redirect click recording.
- **Constraints**: Must not turn the redirect into a read-modify-write on the same row it's
  redirecting from; must not fail the redirect if recording fails.
- **Acceptance criteria**: as listed above.
- **Technical context**: BF-203 (redirect path), AMB-302 (schema).

## Definition of Done

- [ ] Code implemented per acceptance criteria
- [ ] Quality gates passed
- [ ] AI Work Log entry closed
- [ ] `docs/risks/data-integrity.md` R-006 updated with mitigation reference

## Dev Notes

*(fill in once complete, including the explicit trade-off decision on recording-failure behavior)*

## Related

- Risk: R-006 (docs/risks/data-integrity.md)
