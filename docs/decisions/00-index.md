# Decisions — Index

Chronological log of every ADR in this folder. **Decisions are never edited after acceptance.**
If a decision changes, a new file supersedes it and both are linked to each other — this is what
lets a future agent understand *why* the codebase looks the way it does, not just what it
currently looks like.

## Numbering

`NNNN-short-slug.md`, numbered sequentially in the order decisions were made (not by topic).
Use `template.md` for every new one.

## Log

| # | Title | Status | Date | Supersedes / Superseded by |
|---|---|---|---|---|
| [0001](0001-tech-stack.md) | Use Spring Boot (Java) as the implementation stack | Accepted | 2026-09-26 | — |
| [0002](0002-persistence-choice.md) | H2 in-memory for prototype; Postgres documented as production target | Accepted | 2026-09-26 | — |
| [0003](0003-id-generation-strategy.md) | Naive random Base62 generator (interim) | Accepted (interim) | 2026-09-26 | Expected to be superseded during `BF-202` |
| [0004](0004-ai-usage-boundaries.md) | AI usage boundaries for this project | Accepted | 2026-09-26 | — |

*(New rows are added here at the same time a new decision file is created — never after the fact.)*
