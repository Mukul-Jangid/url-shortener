# Decisions — Index

Chronological log of every ADR in this folder. **Decisions are never edited after acceptance.**
Only status and supersession links may be updated in an accepted file.
If a decision changes, a new file supersedes it and both are linked to each other — this is what
lets a future agent understand *why* the codebase looks the way it does, not just what it
currently looks like.

## Numbering

`NNNN-short-slug.md`, numbered sequentially in the order decisions were made (not by topic).
Use `template.md` for every new one.

## Log

| # | Title | Status | Date | Supersedes / Superseded by |
|---|---|---|---|---|
| [0001](0001-tech-stack.md) | Use Spring Boot (Java) as the implementation stack | Accepted | 2026-09-26 | Java version superseded by [0006](0006-java-21.md) |
| [0002](0002-persistence-choice.md) | H2 in-memory for prototype; Postgres documented as production target | Accepted | 2026-09-26 | — |
| [0003](0003-id-generation-strategy.md) | Naive random Base62 generator (interim) | Partially superseded | 2026-09-26 | Planning commitment superseded by [0005](0005-incremental-development.md); initial algorithm retained |
| [0004](0004-ai-usage-boundaries.md) | AI usage boundaries for this project | Accepted | 2026-09-26 | — |
| [0005](0005-incremental-development.md) | Build in small working iterations | Accepted (user direction) | 2026-09-26 | Partially supersedes 0003 |
| [0006](0006-java-21.md) | Use Java 21 | Accepted (user request) | 2026-09-26 | Java version in 0001 |
| [0007](0007-analytics-scope-and-data-model.md) | Atomic counter analytics scope and data model | Accepted | 2026-09-26 | — |

*(New rows are added here at the same time a new decision file is created — never after the fact.)*
