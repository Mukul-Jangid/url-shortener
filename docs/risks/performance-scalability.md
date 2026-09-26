# Risks — Performance / Scalability

## R-002: Unbounded retry loop under high collision rate

- **Likelihood**: Medium while the interim naive generator (`docs/decisions/0003-*.md`) is in use.
- **Impact**: Medium — degrades write latency and could exhaust request-handling threads under
  sustained high collision rates.
- **Mitigation**: Capped retry attempts + fail-fast error in Phase 1 (`GF-102`); eliminated
  entirely by `BF-202`'s counter-based scheme.
- **Owning task**: `BF-202`

## R-005: Redirect endpoint becomes a bottleneck under load

- **Likelihood**: High by design — a URL shortener's read:write ratio is heavily skewed toward
  redirects, so this is the expected hot path, not an edge case.
- **Impact**: Medium — degraded latency or availability under load if not addressed before any
  real traffic.
- **Mitigation**: Caching layer in front of redirect lookups (`BF-203`), with a load/latency
  sanity check (`VAL-404`) to confirm the improvement is real and that later changes (e.g.,
  `AMB-304`'s click recording) don't quietly erase it.
- **Owning tasks**: `BF-203`, `VAL-404`
