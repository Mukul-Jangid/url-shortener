# [GF-101] `ShortUrl` domain entity + repository

| Field | Value |
|---|---|
| **Type** | Story |
| **Module** | Core URL Management |
| **Epic / Phase** | Phase 1 — Greenfield Core |
| **Status** | In Review |
| **Priority** | High |
| **Depends on** | SCAFFOLD-003 |
| **Blocks** | GF-106, GF-102 |
| **AI Work Log** | docs/ai-work-log/entries/GF-101.md (open when work starts) |

## Summary

Create the `ShortUrl` JPA entity and its Spring Data repository — the foundation every other
Phase 1 endpoint depends on.

## Description

Per `docs/architecture/02-data-model.md`, implement the `ShortUrl` entity (`id`, `code`, `originalUrl`,
`createdAt`, `active`; no analytics fields yet) and a `ShortUrlRepository` with at minimum a
`findByCodeAndActiveTrue` lookup method, plus whatever uniqueness constraint on `code` the ID
generation approach in `docs/decisions/0003-id-generation-strategy.md` requires.

## Acceptance Criteria

- [x] `ShortUrl` entity exists in `domain/` with fields matching `docs/architecture/02-data-model.md`
- [x] `code` column has a DB-level unique constraint
- [x] `ShortUrlRepository extends JpaRepository<ShortUrl, Long>` with a `findByCodeAndActiveTrue` method
- [x] H2 repository tests verify persistence, unique-code enforcement, and active-only lookup
- [x] `docs/architecture/02-data-model.md` schema evolution log updated with this change

## Technical Notes / Constraints

- Use `Instant` for `createdAt`, not `LocalDateTime` (avoid timezone ambiguity).
- No Lombok `@Data` on the entity (mutable equals/hashCode on JPA entities is a known footgun) —
  prefer explicit getters/setters or `@Getter`/`@Setter` only.

## AI Collaboration Plan

- **Intent**: Generate the entity + repository per the data model doc.
- **Constraints**: No Lombok `@Data` on the entity; must include a DB-level unique constraint on
  `code`; must not add fields beyond what's in `02-data-model.md` without flagging it back to
  the engineer first.
- **Acceptance criteria**: as listed above.
- **Technical context**: share `docs/architecture/02-data-model.md` and
  `docs/conventions/package-structure.md`.

## Definition of Done

- [x] Code implemented per acceptance criteria
- [x] Tests written and passing (repository test using H2)
- [x] Quality gates passed
- [x] AI Work Log entry closed
- [x] `02-data-model.md` updated

## Dev Notes

Implemented `ShortUrl` entity (`com.urlshortener.domain.ShortUrl`) with fields `id`, `code`, `originalUrl`, `createdAt` (`Instant`), and `active`.
Added unique index constraint on `code` column (`idx_short_url_code`).
Created `ShortUrlRepository` with `findByCodeAndActiveTrue` and `findByCode`.
Wrote integration tests in `ShortUrlRepositoryTest` verifying persistence, unique constraint enforcement (`DataIntegrityViolationException`), and active-only lookup.
Ran `./mvnw clean test spotless:check`: 4/4 tests passed cleanly.

## Related

- Decision: 0003-id-generation-strategy.md
- Risk: R-001, R-002 (docs/risks/data-integrity.md)
