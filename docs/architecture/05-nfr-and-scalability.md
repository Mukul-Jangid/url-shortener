# Non-Functional Requirements & Scalability

Status: initial targets, to be revisited as tasks land — each row should eventually point to the
task(s) that actually satisfy it, and a test/measurement that proves it.

| NFR | Target | Approach | Status / owning task |
|---|---|---|---|
| Reliability (redirect path) | Degrade gracefully rather than fail outright on brief datastore unavailability | Caching layer in front of lookups; documented fallback behavior | `tasks/id-generation-hardening/BF-203-*.md` |
| Performance | Redirect (`GET /{code}`) optimized ahead of write path — read:write ratio for a URL shortener is heavily read-skewed | Cache-first lookup; avoid unnecessary joins/queries on hot path | `BF-203`, load check in `tasks/validation-hardening/VAL-404-*.md` |
| Security | No open-redirect; rate-limited creation; no unjustified PII in analytics | Scheme allowlist on write; rate limiter; analytics scope decision in `AMB-301` | `tasks/validation-hardening/VAL-402-*.md`, `VAL-403-*.md` |
| Scalability | Code generation must not require a global lock or become a write bottleneck | Move off naive random-retry generator to counter-based scheme | `tasks/id-generation-hardening/BF-202-*.md` |
| Data integrity | No lost updates on concurrent click recording | Atomic DB-level increment, not read-modify-write | `tasks/analytics/AMB-304-*.md` |

## Known limitation for this exercise (documented, not silently ignored)

H2 in-memory persistence (see `docs/decisions/0002-persistence-choice.md`) means the "reliability
under datastore failure" NFR is not truly testable end-to-end in this prototype — there's no
separate datastore to simulate failing. This is called out explicitly rather than glossed over;
a production deployment would need this proven against the real target datastore.
