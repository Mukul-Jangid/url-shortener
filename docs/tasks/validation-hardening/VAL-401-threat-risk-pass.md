# [VAL-401] Threat/risk pass (formalize risk register)

| Field | Value |
|---|---|
| **Type** | Spike |
| **Module** | Validation & Hardening |
| **Epic / Phase** | Phase 4 — Validation |
| **Status** | Not Started |
| **Priority** | High |
| **Depends on** | Phase 1 |
| **Blocks** | VAL-402, VAL-403 |
| **AI Work Log** | docs/ai-work-log/entries/VAL-401.md |

## Summary

Deliberate checkpoint: re-review `docs/risks/` against the actual Phase 1 (and, if already done,
Phase 2/3) implementation — not the planned design — to catch anything that changed shape during
implementation and isn't reflected in the risk register yet.

## Acceptance Criteria

- [ ] Every risk file under `docs/risks/` reviewed against current `src/` state
- [ ] Any new risk discovered during implementation added, with an owning task
- [ ] Any risk that no longer applies (e.g., scope changed) marked accordingly with rationale,
      not silently deleted

## AI Collaboration Plan

- **Intent**: Audit risk register accuracy against real implementation.
- **Constraints**: This task produces documentation changes only — no `src/` changes.
- **Technical context**: current `src/` state, all of `docs/risks/`.

## Definition of Done

- [ ] `docs/risks/` reviewed and updated
- [ ] AI Work Log entry closed
- [ ] VAL-402/VAL-403 scoped/confirmed against the reviewed register

## Dev Notes

*(fill in once complete)*

## Related

- —
