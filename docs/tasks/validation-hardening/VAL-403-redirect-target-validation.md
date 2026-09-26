# [VAL-403] Redirect target validation (anti open-redirect)

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

Mitigate R-004 (open redirect / phishing surface) by validating the target URL at creation time
(`GF-102`), not at redirect time. **High-impact task — requires Reviewer Decision: APPROVED**
(redirect target validation is on the high-impact list in `docs/conventions/ai-usage-rules.md`).

## Acceptance Criteria

- [ ] Only `http`/`https` schemes accepted; anything else (`javascript:`, `data:`, `file:`,
      etc.) rejected at creation with `400`
- [ ] Targets resolving to private/link-local/loopback IP ranges rejected (SSRF-adjacent
      hardening)
- [ ] Validation happens on `POST /api/v1/urls` (`GF-102`), not on every redirect — the redirect
      path stays fast (doesn't re-validate on each hit)
- [ ] Existing valid Phase 1 test URLs still pass (regression check against GF-102 tests)

## AI Collaboration Plan

- **Intent**: Add a scheme/target allowlist check at creation time only.
- **Constraints**: Validation must happen once, at creation — not repeated on every redirect
  (performance); must not break GF-102's existing valid-URL test cases.
- **Acceptance criteria**: as listed above.
- **Technical context**: `GF-102`, `docs/risks/security.md` R-004.

## Definition of Done

- [ ] Code implemented per acceptance criteria
- [ ] Tests written and passing (valid URLs, rejected schemes, rejected private IPs)
- [ ] Quality gates passed, including manual security review checklist
- [ ] AI Work Log entry closed **with Reviewer Decision: APPROVED**
- [ ] `docs/risks/security.md` R-004 marked Mitigated

## Dev Notes

*(fill in once complete)*

## Related

- Risk: R-004 (docs/risks/security.md)
