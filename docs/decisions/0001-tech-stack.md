# 0001: Use Spring Boot (Java) as the implementation stack

- **Date**: 2026-09-26
- **Status**: Java version superseded by [0006](0006-java-21.md); other stack choices accepted
- **Task context**: Project setup (`SCAFFOLD-000`), predates the task backlog.

## Context

Need a stack that is fast to scaffold, has mature AI-tooling support (relevant to the
traceability/execution story this exercise is graded on), and is realistic for a financial
services engineering context.

## Decision

Spring Boot 3.3.x on Java 17.

## Alternatives considered

- **Node/Express** — faster to scaffold, but less representative of an enterprise financial
  services stack and weaker built-in conventions for layering/validation.
- **Python/FastAPI** — good AI-assist ergonomics, but same representativeness concern, and
  weaker enforced typing for a codebase meant to demonstrate disciplined engineering.

## Consequences

Slightly more boilerplate per feature. In exchange: strong typing, a mature ecosystem for
validation/persistence/observability, and an idiomatic layered architecture that makes
"codebase reasoning" (the brownfield requirement) easier to demonstrate clearly to a reviewer.

## Status

Java version superseded by [0006](0006-java-21.md); other stack choices remain accepted.
