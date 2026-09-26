# Risks — Security

## R-003: Sequential/guessable short codes allow enumeration

- **Likelihood**: Medium — depends entirely on the ID generation scheme chosen in `BF-202`.
- **Impact**: Medium — allows an attacker to enumerate and discover other users' short links,
  which may be sensitive even without an authentication layer.
- **Mitigation**: Document the initial random generator's length and randomness choice.
  Base62 or an offset alone does not provide unpredictability. A future generator change must
  evaluate enumeration explicitly. Short codes are not a substitute for authorization.
- **Owning tasks**: `GF-102`; reassess in `BF-201` before selecting `BF-202`

## R-004: Unsafe schemes and malicious destinations

- **Status**: Open; URL creation is not implemented yet.
- **Impact**: Unsafe URI schemes and phishing through otherwise valid external destinations.
- **Initial control**: GF-102/GF-106 reject malformed URLs and non-HTTP/HTTPS schemes at
  creation. The service issues redirects; it does not fetch target content on the server.
- **Remaining limitation**: A valid HTTP/HTTPS destination can still be malicious. Syntax and
  scheme validation do not eliminate phishing or establish a comprehensive destination policy.
- **Later review**: VAL-401/VAL-403 define any extra restrictions for the chosen deployment.
  Do not introduce DNS resolution or server-side requests without a specific requirement.
- **Owning tasks**: GF-102/GF-106 initially; VAL-403 for additional policy if selected.

## R-007: Unrestricted create endpoint enables spam/abuse

- **Likelihood**: Medium — any public write endpoint with no rate limiting is a predictable
  abuse target.
- **Impact**: Medium — resource exhaustion, spam link generation, reputational risk if the
  service is used to host malicious redirects at scale.
- **Mitigation**: Rate limiting on `POST /api/v1/urls` only — scoped away from the redirect path
  so legitimate traffic isn't affected.
- **Owning task**: `VAL-402`

## R-012: Analytics inadvertently stores PII without justification

- **Likelihood**: Medium — depends entirely on which analytics interpretation is chosen in
  `AMB-301` (e.g., storing raw IP addresses or precise device fingerprints without necessity).
- **Impact**: Medium — unnecessary PII collection is both a compliance and a trust risk, even in
  a prototype, and sets a bad precedent for "production-grade" claims.
- **Mitigation**: Scope decision made explicitly and in writing in `AMB-301` *before*
  implementation; if any identifying data is stored, the retention/anonymization approach must
  be documented alongside that decision, not left implicit.
- **Owning task**: `AMB-301`
