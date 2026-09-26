# [BF-205] Update architecture/decisions docs post-refactor

| Field | Value |
|---|---|
| **Type** | Task |
| **Module** | ID Generation Hardening (Brownfield) |
| **Epic / Phase** | Phase 2 — Brownfield |
| **Status** | Not Started |
| **Priority** | Medium |
| **Depends on** | BF-202 |
| **Blocks** | Phase 2 completion |
| **AI Work Log** | docs/ai-work-log/entries/BF-205.md |

## Summary

Close the loop: this phase changed the system's shape, so the docs that describe that shape must
be updated as part of the phase, not as an afterthought — per the update rule in
`docs/architecture/README.md`.

## Acceptance Criteria

- [ ] New decision file added under `docs/decisions/` for the counter-based generation scheme,
      linked from `docs/decisions/00-index.md`
- [ ] `0003-id-generation-strategy.md` status updated to "Superseded by 000X"
- [ ] `docs/architecture/01-component-architecture.md` status table updated
- [ ] `docs/architecture/05-nfr-and-scalability.md` "Scalability" row updated
- [ ] `docs/risks/data-integrity.md` and `docs/risks/security.md` — R-001/R-002/R-003 marked
      Mitigated, pointing to BF-202/BF-204 as the closing tasks

## AI Collaboration Plan

- **Intent**: Documentation synchronization only — no code changes in this task.
- **Constraints**: Must not alter the historical decision files (0001–0004) — only add new ones
  and update status fields/links.
- **Acceptance criteria**: as listed above.
- **Technical context**: BF-201 through BF-204's actual outcomes (their Dev Notes sections).

## Definition of Done

- [ ] All docs listed above updated
- [ ] AI Work Log entry closed
- [ ] `docs/tasks/00-index.md` board updated to reflect Phase 2 complete

## Dev Notes

*(fill in once complete)*

## Related

- Decision: 0003 (superseded), new decision TBD
- Risk: R-001, R-002, R-003 (to be marked Mitigated)
