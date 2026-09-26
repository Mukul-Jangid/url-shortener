# [DOC-501] README setup/run instructions

| Field | Value |
|---|---|
| **Type** | Task |
| **Module** | Documentation |
| **Epic / Phase** | Phase 5 — Documentation Finalization |
| **Status** | Not Started (initial version already exists from SCAFFOLD-000; this task finalizes it) |
| **Priority** | Medium |
| **Depends on** | Phase 1 |
| **Blocks** | — |
| **AI Work Log** | docs/ai-work-log/entries/DOC-501.md |

## Summary

Bring the root `README.md` up to date with the real, final API surface, setup steps, and known
limitations — the SCAFFOLD-000 version was necessarily provisional (no endpoints existed yet).

## Acceptance Criteria

- [ ] Setup/run instructions verified to actually work end-to-end (someone with a clean checkout
      and Java 17 + Maven can run it following only the README)
- [ ] Example `curl` requests for every Phase 1 endpoint (and analytics, once AMB-303 lands)
- [ ] "Known limitations" section reflects actual final state, not the Phase 0 placeholder list
- [ ] Links to `docs/00-INDEX.md` remain accurate (folder names/paths unchanged or links updated)

## AI Collaboration Plan

- **Intent**: Finalize README against the real, completed system.
- **Constraints**: Every example command must be verified to actually work, not just plausible.
- **Technical context**: all completed task files' Dev Notes sections (they describe what was
  actually built, which may differ from the original plan).

## Definition of Done

- [ ] README updated and verified against a clean run
- [ ] Quality gates passed (n/a for docs beyond spelling/link check)
- [ ] AI Work Log entry closed

## Dev Notes

*(fill in once complete)*

## Related

- —
