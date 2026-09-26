# [GF-108] OpenAPI/schema definitions, Phase 1

| Field | Value |
|---|---|
| **Type** | Task |
| **Module** | Core URL Management |
| **Epic / Phase** | Phase 1 — Greenfield Core |
| **Status** | Not Started |
| **Priority** | Medium |
| **Depends on** | GF-102, GF-103, GF-104, GF-105, GF-106 |
| **Blocks** | — |
| **AI Work Log** | docs/ai-work-log/entries/GF-108.md |

## Summary

Add springdoc-openapi so the API is self-documenting, and link the generated spec from
`docs/architecture/03-api-design.md` instead of hand-maintaining a duplicate schema in Markdown.

## Acceptance Criteria

- [ ] `springdoc-openapi-starter-webmvc-ui` dependency added (justify in AI Work Log per
      `docs/conventions/ai-usage-rules.md` — new dependency rule)
- [ ] `/swagger-ui.html` renders all Phase 1 endpoints with request/response schemas
- [ ] Each DTO field has a description annotation for anything non-obvious (e.g., `code` format)
- [ ] `docs/architecture/03-api-design.md` updated to link to the live spec instead of restating it

## AI Collaboration Plan

- **Intent**: Wire up OpenAPI generation and annotate existing DTOs/controllers.
- **Constraints**: One new dependency only (springdoc); justify it in the work log.
- **Acceptance criteria**: as listed above.
- **Technical context**: all Phase 1 controllers/DTOs.

## Definition of Done

- [ ] Code implemented per acceptance criteria
- [ ] Quality gates passed
- [ ] AI Work Log entry closed (with dependency justification)
- [ ] `03-api-design.md` updated

## Dev Notes

*(fill in once complete)*

## Related

- —
