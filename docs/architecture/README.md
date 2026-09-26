# Architecture Documentation — Index

This folder is split by concern instead of one long file, so a future contributor (human or AI)
can load just the piece relevant to the task at hand instead of the whole system picture.

| File | Covers |
|---|---|
| `00-overview.md` | System purpose, scope, what's in/out |
| `01-component-architecture.md` | Layering, package responsibilities, component diagram |
| `02-data-model.md` | Entities, relationships, schema evolution notes |
| `03-api-design.md` | Endpoint inventory, request/response conventions, versioning |
| `04-control-flow-ai-execution.md` | How AI-assisted work flows through this project, step by step |
| `05-nfr-and-scalability.md` | Reliability, performance, security, scalability targets and how they're being met |

## Update rule

Whenever a task in `docs/tasks/` changes the actual shape of the system (new component, changed
data model, new endpoint, changed NFR posture), the relevant file here is updated **as part of
that task**, not as a separate cleanup pass. A task is not `Done` if architecture docs are stale
relative to what got built — see `docs/conventions/quality-gates.md`.
