# Risks — Performance / Scalability

## R-002: Excessive collision retries increase creation latency

- **Status**: Open; no generator is implemented yet.
- **Impact**: Extra database work and slower creation under collisions.
- **Initial control**: GF-102 bounds retries and tests the failure response. Bounded retries
  do not constitute an unbounded loop; do not retain that claim once the control is verified.
- **Revisit**: BF-201 may evaluate a different generator if evidence or an exercise goal
  justifies it. A replacement must handle existing codes and prove its own properties.
- **Owning task**: GF-102; later review in BF-201.

## R-005: Redirect lookup may become a bottleneck under load

- **Status**: Open; no workload measurements yet.
- **Impact**: Slow responses at an unknown workload threshold.
- **Initial control**: Simple lookup by unique code, behavior tests, and a baseline in VAL-404
  when performance work is selected.
- **Revisit**: BF-203 considers a bounded cache only with a stated need, a baseline, and a
  deactivation-consistency plan. A cache is not required to prove the first version works.
- **Owning tasks**: VAL-404, BF-201, BF-203 if selected.
