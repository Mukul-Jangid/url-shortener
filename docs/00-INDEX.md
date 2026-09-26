# docs/ — Engineering Guardrails

Start with [development guidelines](conventions/development-workflow.md), then the
[task board](tasks/00-index.md) and the selected task. [AGENTS.md](../AGENTS.md) is the
entry point for agents. Current iteration scope takes precedence over historical backlog
design suggestions; accepted decision changes are recorded in new ADRs.

This folder contains the development rules and project record. It exists *before* any feature code is
written, and every subsequent AI-assisted task must operate inside the boundaries it sets.
Nothing in `src/` should be created or modified by an AI tool without a corresponding entry
in `ai-work-log/` first.

## Folder structure

```
docs/
├── 00-INDEX.md              (this file)
├── architecture/             what we're building — split by concern, not one long file
│   ├── README.md             (folder index)
│   ├── 00-overview.md
│   ├── 01-component-architecture.md
│   ├── 02-data-model.md
│   ├── 03-api-design.md
│   ├── 04-control-flow-ai-execution.md
│   └── 05-nfr-and-scalability.md
├── decisions/                 ADR log — initial decisions + every decision made since
│   ├── 00-index.md            (chronological log, links every ADR)
│   ├── template.md
│   └── 0001-*.md, 0002-*.md, ... (never edited after acceptance; superseded, not rewritten)
├── tasks/                     Jira-style task specs, one file per task, grouped by module
│   ├── 00-index.md            (the board — status at a glance)
│   ├── _template/TASK-TEMPLATE.md
│   ├── core-url-management/   (Phase 1 — greenfield)
│   ├── id-generation-hardening/ (Phase 2 — brownfield)
│   ├── analytics/             (Phase 3 — ambiguous scenario)
│   ├── validation-hardening/  (Phase 4 — validation)
│   └── documentation/         (Phase 5 — docs)
├── ai-work-log/                traceability — one entry file per task
│   ├── 00-index.md
│   ├── template.md
│   └── entries/<TASK-ID>.md
├── risks/                      risk register, split by category
│   ├── 00-index.md             (master table, quick reference)
│   ├── data-integrity.md
│   ├── performance-scalability.md
│   ├── security.md
│   ├── availability.md
│   └── ai-usage.md
└── conventions/                 rules every change (AI or human) must follow
    ├── 00-index.md
    ├── package-structure.md, naming.md, error-handling.md, ai-usage-rules.md,
    │   quality-gates.md, testing.md, logging.md, commit-hygiene.md
```

## Reading order (for a human reviewer or a new contributor — or a future agent)

1. **conventions/development-workflow.md** and **tasks/00-index.md** — current rules and scope.
2. **architecture/** — what we're building, component layout, control flow for how AI is used.
3. **decisions/** — every non-trivial technical choice, with rationale and alternatives
   considered, in the order they were made. This is what lets a future agent understand *why*
   the codebase looks the way it does, not just what it currently looks like.
4. **tasks/** — the backlog: decomposition, dependencies, sequencing, grouped by module, each
   task a self-contained spec that becomes a historical record once completed (see the `Dev
   Notes` section every task file has).
5. **ai-work-log/** — one traceability file per task: prompt/intent, constraints given, what was
   generated, and whether it was accepted/edited/rejected, with rationale.
6. **risks/** — failure modes by category, likelihood/impact, and the task that mitigates each
   one. Includes risks specific to *using AI* on this codebase, not just risks in the domain.
7. **conventions/** — the rules every AI-generated or human-written change must follow.

## Mapping to the assignment's Core Requirements

| Assignment requirement | Where it's satisfied |
|---|---|
| 1. Requirement Understanding | `tasks/00-index.md` sequencing rationale + each task's Description; `tasks/analytics/AMB-301-*.md` for the ambiguous scenario specifically |
| 2. Task Decomposition | `tasks/` (all modules) |
| 3. Codebase Reasoning (Brownfield) | `decisions/` + `tasks/id-generation-hardening/BF-201-*.md` (dedicated Impact Analysis task) |
| 4. AI-Assisted Execution | `ai-work-log/` + `conventions/ai-usage-rules.md` |
| 5. Engineering Output Generation | `src/` + `architecture/` |
| 6. Validation and Risk Control | `risks/` + `conventions/quality-gates.md` |
| 7. Controlled Oversight | `conventions/ai-usage-rules.md` (sign-off rule) + every `ai-work-log/entries/*.md` has a `Reviewer Decision` field only the engineer fills in |
| 8. Final Engineering Summary | `docs/FINAL_SUMMARY.md`, produced by `tasks/documentation/DOC-502-*.md` once all scenarios are complete — not scaffolded yet, since it summarizes work not yet done |

## Ground rule

No task in `tasks/` is "In Progress" until it has a corresponding open file in
`ai-work-log/entries/`, and no task is "Done" until: its work-log entry is closed (with a
Reviewer Decision if high-impact), the relevant Quality Gates in `conventions/quality-gates.md`
pass, its `Dev Notes` section is filled in, and — if the task changed system shape — the
relevant `architecture/`, `decisions/`, or `risks/` file is updated as part of the same task,
not as a later cleanup pass.
