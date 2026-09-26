# 0005: Build in small working iterations

- **Date**: 2026-09-26
- **Status**: Accepted — incremental delivery and guidelines-first direction explicitly given by the user
- **Task context**: SCAFFOLD-002
- **Supersedes**: 0003's commitment to automatic generator replacement; retains its initial
  random Base62 approach with database uniqueness and bounded retries. Supplements 0004's
  review policy without removing engineer ownership of approval or Done.

## Context

The repository has a small application scaffold and a detailed future backlog. Some tasks
prescribe caches, generator replacement, and analytics storage before a working core exists.
Some also postpone validation or documentation that the conventions already require.
The user wants guidelines settled first, a workable initial solution, and added complexity
only in subsequent iterations, with decisions explained in small, understandable pieces.

## Decision

Use the development workflow as the current execution policy. Verify the scaffold, then
deliver create-and-redirect with validation and tests. Add metadata and deactivation next.
Refine later tasks only when selected, keeping the exercise's existing scenario IDs.

Keep the initial random generator unless an observed problem or explicit exercise objective
justifies changing it. A counter encoded in Base62 is not by itself a security improvement;
bounded retries are already bounded. Do not claim either risk is automatically fixed by a
future refactor. Retain human approval before high-impact acceptance and engineer-owned Done.

## Alternatives considered

- Implement the full planned architecture first: delays the first usable result and commits
  to requirements that are still uncertain.
- Remove all later tasks: loses useful exercise context. Keep them as candidates and refine
  them before execution instead.

## Consequences

Each feature task includes its own tests and relevant documentation. Later review tasks check
completeness. Analytics does not require a cache, and the first data model has no analytics
fields. Small decisions go in Dev Notes; lasting decisions get an ADR.

The first version remains a local prototype with in-memory storage and no authentication.
This policy does not declare it ready for public deployment. Revisit scope when a concrete
user need, measured limitation, or confirmed assignment requirement warrants it.
