# Risks — Availability

## R-008: Datastore unavailability takes down the redirect path entirely

- **Likelihood**: Low for this prototype (H2 in-memory has no separate failure mode to trigger),
  Medium for a real production deployment against a networked datastore.
- **Impact**: High — the redirect path is the core value of the service; if it's unavailable,
  the whole product is down.
- **Mitigation**: Documented as a known limitation for this exercise rather than implemented —
  see `docs/decisions/0002-persistence-choice.md`. A production mitigation (read replica, or
  serving stale cache entries as a fallback on datastore failure) is noted in
  `docs/architecture/05-nfr-and-scalability.md` but deliberately not built, since there's no real
  external datastore in this prototype to meaningfully test against.
- **Owning task**: None — intentionally scoped out, documented rather than silently ignored.
