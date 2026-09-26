# 0003: Naive random Base62 generator for short codes (interim)

- **Date**: 2026-09-26
- **Status**: Accepted (interim) — expected to be superseded during `BF-202`
- **Task context**: Anticipates `GF-102` (create endpoint) and `BF-202` (brownfield refactor).

## Context

Short-code generation has real correctness and scalability consequences (collision handling,
enumeration/guessability risk, distributed uniqueness). It should not be decided implicitly by
an AI suggestion mid-implementation without engineer sign-off — it needs to be decided up front,
deliberately, even if the first version is intentionally naive.

## Decision

Phase 1 (`GF-102`) implements a random Base62 string generator with a DB-uniqueness constraint
and a bounded retry loop on collision. This is **explicitly flagged as a known-naive
implementation**, not a final design — the goal is a working greenfield system quickly, with the
refactor already planned rather than discovered later as a surprise.

This decision's planned supersession *is* the brownfield scenario required by the assignment:
`BF-202` will refactor this to a counter-based Base62 scheme (or similar) to remove the
unbounded-retry risk and reduce enumeration guessability. See `docs/risks/data-integrity.md`
(R-001, R-002) and `docs/risks/security.md` (R-003).

## Alternatives considered

- **Build the counter-based scheme immediately, skip the naive version** — rejected on purpose:
  the assignment specifically requires a demonstrated brownfield scenario, and refactoring a
  real (if naive) working implementation is a more honest demonstration of "codebase reasoning"
  than refactoring something that was never actually deployed.

## Consequences

Phase 1 carries known, documented risk (R-001, R-002, R-003) for the duration between `GF-102`
and `BF-202`. This is acceptable because it's tracked, bounded in time, and the whole point of
the exercise is to show the refactor, not to avoid ever having the flaw.

## Status

Accepted as an interim decision. Will be marked "Superseded by 000X" once `BF-202` lands, with a
new decision file capturing the counter-based design.
