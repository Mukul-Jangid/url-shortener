# [GF-101] `ShortUrl` domain entity + repository

| Field | Value |
|---|---|
| **Type** | Story |
| **Module** | Core URL Management |
| **Epic / Phase** | Phase 1 — Greenfield Core |
| **Status** | Not Started |
| **Priority** | High |
| **Depends on** | SCAFFOLD-000 |
| **Blocks** | GF-102, GF-103, GF-104, GF-105 |
| **AI Work Log** | docs/ai-work-log/entries/GF-101.md (open when work starts) |

## Summary

Create the `ShortUrl` JPA entity and its Spring Data repository — the foundation every other
Phase 1 endpoint depends on.

## Description

Per `docs/architecture/02-data-model.md`, implement the `ShortUrl` entity (`code`, `originalUrl`,
`createdAt`, `active`, `clickCount`) and a `ShortUrlRepository` with at minimum a
`findByCodeAndActiveTrue` lookup method, plus whatever uniqueness constraint on `code` the ID
generation approach in `docs/decisions/0003-id-generation-strategy.md` requires.

## Acceptance Criteria

- [ ] `ShortUrl` entity exists in `domain/` with fields matching `docs/architecture/02-data-model.md`
- [ ] `code` column has a DB-level unique constraint
- [ ] `ShortUrlRepository extends JpaRepository<ShortUrl, Long>` with a `findByCodeAndActiveTrue` method
- [ ] Entity does not leak into any API response directly (enforced by not existing yet — future
      tasks must map through `dto/`)
- [ ] `docs/architecture/02-data-model.md` schema evolution log updated with this change

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

- [ ] Code implemented per acceptance criteria
- [ ] Tests written and passing (repository test using H2)
- [ ] Quality gates passed
- [ ] AI Work Log entry closed
- [ ] `02-data-model.md` updated

## Dev Notes

*(fill in once complete)*

## Related

- Decision: 0003-id-generation-strategy.md
- Risk: R-001, R-002 (docs/risks/data-integrity.md)
