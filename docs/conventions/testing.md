# Testing Conventions

- Write behavior tests in the task that adds or changes the behavior. GF-107 and BF-204 are
  coverage checkpoints; they do not defer feature testing.
- Unit tests for meaningful `service/` business rules (mock repositories); avoid duplicate
  tests of trivial delegation when integration coverage already checks the behavior.
- Integration tests (`@SpringBootTest` + `MockMvc` or `TestRestTemplate`) for controller-level
  behavior, using the H2 in-memory profile.
- Concurrency-sensitive logic (e.g., click-count increments, `AMB-304`) gets an explicit
  concurrent-access test — a single-threaded happy-path test is not sufficient and should not be
  accepted as satisfying the acceptance criteria.
- Brownfield tasks (Phase 2) must include a regression test proving the external API contract is
  unchanged, in addition to tests for the new internal behavior — see `BF-204`.
- Tests assert on HTTP status / `ProblemDetail` `type`, not exact message strings, so later
  refactors don't cause brittle unrelated test failures.
