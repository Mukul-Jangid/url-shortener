# [BF-202] Refactor: counter-based Base62 ID generation

| Field | Value |
|---|---|
| **Type** | Task |
| **Module** | ID Generation Hardening (Brownfield) |
| **Epic / Phase** | Phase 2 — Brownfield |
| **Status** | Not Started |
| **Priority** | High |
| **Depends on** | BF-201 |
| **Blocks** | BF-204, BF-205 |
| **AI Work Log** | docs/ai-work-log/entries/BF-202.md |

## Summary

Replace the naive random-retry generator (`docs/decisions/0003-id-generation-strategy.md`) with
a counter-based scheme, removing unbounded-retry risk (R-002) and reducing enumeration
guessability (R-003). **High-impact task — requires Reviewer Decision: APPROVED.**

## Description

Design a monotonic-counter-backed generator (e.g., DB sequence or an atomic counter table)
encoded to Base62, with enough obfuscation (e.g., bit-mixing or a fixed offset) that codes aren't
trivially sequential-looking to an external caller, without reintroducing collision-retry risk.

## Impact Analysis

*(carried over from BF-201's findings — do not re-derive from scratch; reference it)*

- Modules/files affected: *(fill from BF-201 output)*
- Existing behavior that must not change: `POST /api/v1/urls` response shape; `GET /{code}`
  contract per BF-201's findings
- Tests that must still pass unmodified (assertions unchanged, setup may change): all GF-102 and
  GF-103 integration tests

## Acceptance Criteria

- [ ] New generator has no retry loop (uniqueness guaranteed by construction, not by chance)
- [ ] Codes are not trivially sequential/guessable (documented reasoning, not just "trust me")
- [ ] `POST /api/v1/urls` and `GET /{code}` external contracts unchanged (verified by BF-204)
- [ ] `docs/decisions/0003-id-generation-strategy.md` marked superseded; new decision file added

## AI Collaboration Plan

- **Intent**: Replace the generator internals only; external API contract must not change.
- **Constraints**: No change to `POST`/`GET` request/response shapes; must not reintroduce a
  retry loop; must be justified against R-003 (enumeration).
- **Acceptance criteria**: as listed above.
- **Technical context**: BF-201's impact map, `GF-101`/`GF-102`.

## Definition of Done

- [ ] Code implemented per acceptance criteria
- [ ] Tests updated (internals) — BF-204 covers external contract regression separately
- [ ] Quality gates passed, including the manual security review checklist
- [ ] AI Work Log entry closed **with Reviewer Decision: APPROVED**
- [ ] New decision file added, 0003 marked superseded

## Dev Notes

*(fill in once complete)*

## Related

- Decision: 0003-id-generation-strategy.md (to be superseded)
- Risk: R-001, R-002, R-003
