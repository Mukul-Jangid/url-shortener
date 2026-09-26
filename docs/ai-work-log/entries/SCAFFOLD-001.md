# [SCAFFOLD-001] AI Work Log — Restructure docs into per-concern folders

- **Task file**: n/a — a guardrail-structure change requested directly, predates a formal task
  file of its own (the change *is* the tasks folder being created).
- **Opened**: 2026-09-26
- **Closed**: (leave open — final Engineer Decision to be filled in after your review)

## Round 1 — 2026-09-26

- **Intent**: Restructure the flat `docs/*.md` files (from SCAFFOLD-000) into folders: multiple
  files per architecture concern; an initial-plus-ongoing decisions log; a Jira-style task spec
  per task, organized by module, usable as a durable historical reference; risks split by
  category; conventions split by topic; and a per-task AI work log instead of one shared file.
- **Constraints given to the AI**: Task files must follow a Jira-style structure (type, module,
  status, priority, dependencies, acceptance criteria, definition of done). Tasks must be
  broken out by module/folder. Decisions folder needs both the initial decisions and a place for
  decisions made during development, so a future agent can understand past developer decisions.
- **Acceptance criteria given to the AI**: Every previous flat doc's content preserved (nothing
  silently dropped), reorganized into the new structure, with index files tying each folder
  together and cross-references between folders (tasks ↔ decisions ↔ risks ↔ architecture) kept
  consistent.
- **Technical context given to the AI**: The full existing `docs/` tree from SCAFFOLD-000.
- **Prompt summary**: "Folders in docs — architecture should have multiple files, decisions
  should have initial + ongoing decisions, tasks should be Jira-style specs broken out by
  module, and other folders should follow the same idea."
- **AI output summary**:
  - `docs/architecture/` — `README.md` (index) + 6 topic files (overview, component
    architecture, data model, API design, AI control flow, NFRs)
  - `docs/decisions/` — `00-index.md` + `template.md` + 4 initial ADRs (0001–0004)
  - `docs/tasks/` — `00-index.md` (board) + `_template/TASK-TEMPLATE.md` + 25 individual
    Jira-style task files across 5 module folders (core-url-management, id-generation-hardening,
    analytics, validation-hardening, documentation)
  - `docs/ai-work-log/` — `00-index.md` + `template.md` + `entries/` (this entry and
    SCAFFOLD-000's migrated entry)
  - `docs/risks/` — (see RISKS restructuring, in progress alongside this entry)
  - `docs/conventions/` — (see CONVENTIONS restructuring, in progress alongside this entry)
- **Engineer decision**: *(fill in once reviewed)*
- **Rationale**: *(fill in — e.g., "task granularity is right" or "want fewer/more task files
  per module")*
- **Edits made (if any)**: *(fill in)*
- **Quality gates result**: N/A (documentation-only change; no `src/` touched).

## Final disposition

- **Engineer decision**: *(fill in)*
- **Reviewer Decision**: Not applicable — documentation-only change.
