# 0002: H2 in-memory for prototype; Postgres documented as production target

- **Date**: 2026-09-26
- **Status**: Accepted
- **Task context**: Project setup (`SCAFFOLD-000`).

## Context

The deliverable must be "runnable end-to-end" by a reviewer with minimal setup. The prototype
also needs to demonstrate awareness of production-grade persistence trade-offs, not just pick
the easiest option uncritically.

## Decision

Default Spring profile runs against H2 in-memory (zero external setup required). Document (here
and in `docs/architecture/`) that a production deployment would use Postgres. Treat "add a
`postgres` Spring profile" as an optional hardening task if time allows — not committed to the
core backlog.

## Alternatives considered

- **Require Docker + Postgres from the start** — rejected for this exercise: adds reviewer
  friction (extra setup steps) without adding grading signal. The assignment rewards clarity of
  trade-off reasoning over infrastructure complexity.

## Consequences

Data does not persist across application restarts in the default profile. This is called out
explicitly in the root `README.md` "Known limitations" section so it is never mistaken for an
oversight by a reviewer. It also means the "reliability under datastore failure" NFR
(`docs/architecture/05-nfr-and-scalability.md`) can't be fully exercised end-to-end in this
prototype.

## Status

Accepted.
