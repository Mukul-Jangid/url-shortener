# [VAL-403] Review additional destination policy

| Field | Value |
|---|---|
| **Type** | Story |
| **Module** | Validation & Hardening |
| **Epic / Phase** | Phase 4 — Validation |
| **Status** | Not Started |
| **Priority** | High |
| **Depends on** | VAL-401 |
| **Blocks** | — |
| **AI Work Log** | docs/ai-work-log/entries/VAL-403.md |

## Summary

Review R-004 against the intended deployment. GF-102 already validates syntax and HTTP/HTTPS
schemes. Define any additional destination restrictions before adding them. Redirecting to
external destinations is core functionality; scheme validation alone does not prevent phishing. **High-impact task — requires Reviewer Decision: APPROVED**
(redirect target validation is on the high-impact list in `docs/conventions/ai-usage-rules.md`).

## Acceptance Criteria

- [ ] Only `http`/`https` schemes accepted; anything else (`javascript:`, `data:`, `file:`,
      etc.) rejected at creation with `400`
- [ ] Document whether additional target restrictions are required and why. Do not add DNS
      lookups or server-side fetching by default; assess address changes if a DNS policy is chosen
- [ ] Validation happens on `POST /api/v1/urls` (`GF-102`), not on every redirect — the redirect
      path stays fast (doesn't re-validate on each hit)
- [ ] Existing valid Phase 1 test URLs still pass (regression check against GF-102 tests)

## AI Collaboration Plan

- **Intent**: Review existing validation and implement only the additional policy selected by VAL-401.
- **Constraints**: Validation must happen once, at creation — not repeated on every redirect
  (performance); must not break GF-102's existing valid-URL test cases.
- **Acceptance criteria**: as listed above.
- **Technical context**: `GF-102`, `docs/risks/security.md` R-004.

## Definition of Done

- [ ] Code implemented per acceptance criteria
- [ ] Tests written and passing (valid URLs, rejected schemes, and any newly chosen destination restrictions)
- [ ] Quality gates passed, including manual security review checklist
- [ ] AI Work Log entry closed **with Reviewer Decision: APPROVED**
- [ ] R-004 describes tested controls and remaining phishing/abuse limitations honestly

## Dev Notes

*(fill in once complete)*

## Related

- Risk: R-004 (docs/risks/security.md)
