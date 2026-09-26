# [BF-201] Impact analysis: ID generation + redirect path

| Field | Value |
|---|---|
| **Type** | Spike |
| **Module** | ID Generation Hardening (Brownfield) |
| **Epic / Phase** | Phase 2 — Brownfield |
| **Status** | Not Started |
| **Priority** | High |
| **Depends on** | Phase 1 complete (GF-107) |
| **Blocks** | BF-202, BF-203 |
| **AI Work Log** | docs/ai-work-log/entries/BF-201.md |

## Summary

**Required before any code changes in this phase.** Identify every module that depends on the
current random-Base62 generator and every consumer of `GET /{code}` that assumes current
behavior/latency, so the refactor in BF-202/BF-203 doesn't silently break something outside its
intended blast radius. This is the task that satisfies the assignment's "Codebase Reasoning
(Brownfield)" requirement most directly.

## Description

This is analysis output, not code. Deliverable is a written impact map (added to this file's Dev
Notes) covering: which classes call the generator, what the DB uniqueness constraint currently
assumes, what tests currently encode assumptions about generation behavior (e.g., testing the
retry-bound), and what the current redirect path's contract is (status codes, headers) that must
survive caching being added.

## Acceptance Criteria

- [ ] Written list of every file/class touching ID generation (service, repository, tests)
- [ ] Written list of every test that would break or need updating due to BF-202/BF-203
- [ ] Explicit statement of the redirect endpoint's current external contract (status codes,
      headers, timing assumptions) that BF-203 must preserve
- [ ] Risks R-001/R-002/R-003 (docs/risks/) reviewed and confirmed still accurately describe the
      current implementation before the refactor begins

## AI Collaboration Plan

- **Intent**: Produce an impact map by reading the current codebase, not by assumption.
- **Constraints**: This task produces no `src/` changes — output is analysis only, written into
  this file's Dev Notes.
- **Acceptance criteria**: as listed above.
- **Technical context**: all of Phase 1 (`GF-101`–`GF-108`), `docs/risks/`.

## Definition of Done

- [ ] Impact map written into Dev Notes below
- [ ] AI Work Log entry closed
- [ ] BF-202 and BF-203 task files updated with any scope refinement this analysis surfaces

## Dev Notes

*(fill in with the actual impact map once this runs — this becomes the reference for anyone
touching ID generation again later)*

## Related

- Risk: R-001, R-002, R-003
