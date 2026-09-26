# [GF-108] OpenAPI/schema definitions, Phase 1

| Field | Value |
|---|---|
| **Type** | Task |
| **Module** | Core URL Management |
| **Epic / Phase** | Phase 1 — Greenfield Core |
| **Status** | In Review |
| **Priority** | Medium |
| **Depends on** | GF-102 |
| **Blocks** | — |
| **AI Work Log** | docs/ai-work-log/entries/GF-108.md |

## Summary

Add springdoc-openapi so the API is self-documenting, and link the generated spec from
`docs/architecture/03-api-design.md` instead of hand-maintaining a duplicate schema in Markdown.

## Acceptance Criteria

- [x] `springdoc-openapi-starter-webmvc-ui` dependency added (justify in AI Work Log per
      `docs/conventions/ai-usage-rules.md` — new dependency rule)
- [x] `/swagger-ui.html` renders all Phase 1 endpoints with request/response schemas
- [x] Each DTO field has a description annotation for anything non-obvious (e.g., `code` format)
- [x] `docs/architecture/03-api-design.md` updated to link to the live spec instead of restating it

## AI Collaboration Plan

- **Intent**: Wire up OpenAPI generation and annotate existing DTOs/controllers.
- **Constraints**: One new dependency only (springdoc); justify it in the work log.
- **Acceptance criteria**: as listed above.
- **Technical context**: all Phase 1 controllers/DTOs.

## Definition of Done

- [x] Code implemented per acceptance criteria
- [x] Quality gates passed
- [x] AI Work Log entry closed (with dependency justification)
- [x] `03-api-design.md` updated

## Dev Notes

*(fill in once complete)*

## Related

- —
