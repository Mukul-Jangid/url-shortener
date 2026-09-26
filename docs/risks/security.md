# Risks — Security

## R-003: Sequential/guessable short codes allow enumeration

- **Likelihood**: Medium — depends entirely on the ID generation scheme chosen in `BF-202`.
- **Impact**: Medium — allows an attacker to enumerate and discover other users' short links,
  which may be sensitive even without an authentication layer.
- **Mitigation**: Avoid pure sequential codes; if a counter-based scheme is adopted (as planned),
  obfuscate via Base62 encoding with bit-mixing or an offset rather than exposing the raw counter.
- **Owning task**: `BF-202`

## R-004: Open redirect via malicious scheme or target

- **Likelihood**: Medium — this is a well-known, commonly-exploited class of vulnerability for
  any service that redirects based on user-supplied input.
- **Impact**: High — enables phishing (redirecting through a trusted-looking short domain to a
  malicious destination) and potentially SSRF-adjacent issues if internal targets are reachable.
- **Mitigation**: Scheme allowlist (`http`/`https` only) and rejection of private/link-local IP
  targets, enforced once at creation time (`VAL-403`) rather than on every redirect, to avoid
  adding latency to the hot path.
- **Owning task**: `VAL-403` — flagged as high-impact, requires explicit Reviewer Decision.

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
