# Working on this project

Build a working version first, then improve it in small, useful steps. Do not add complexity
just because it appears in the future backlog.

## Read before changing files

1. [Development guidelines](docs/conventions/development-workflow.md): scope, task readiness,
   decisions, and review rules.
2. [Task board](docs/tasks/00-index.md): current iteration and next task.
3. The selected task, relevant architecture pages, and referenced decisions/conventions.

## Execution rules

- Complete the authorized task, including appropriate checks and documentation. Routine local
  reads, edits, and checks do not need repeated confirmation.
- Before implementation, present and discuss the plan (scope, files to touch, acceptance criteria, constraints) with the developer. Open its work log and keep the board in sync.
- Implement the smallest working behavior. Include correctness checks and tests with that
  behavior; do not defer them to a later testing phase.
- Do not add caching, analytics fields, new infrastructure, generic frameworks, or dependencies
  in anticipation of a later task. State the current need first.
- Write plain, simple language for all code comments, API documentation, OpenAPI annotations, decisions, and Dev Notes (avoid complex jargon or academic phrasing).
  Record local choices in task Dev Notes; use an ADR for lasting cross-task decisions.
- Finish implementation, tests, and documentation, run quality gates locally, present the diff/summary to the developer, and obtain explicit developer approval BEFORE running `git commit` or `git push`. **AUTOMATIC COMMITS OR PUSHES BY THE AGENT ARE STRICTLY FORBIDDEN.**
  Agents may move work to `In Review`; the engineer owns `Done` and high-impact approval under
  [AI usage rules](docs/conventions/ai-usage-rules.md). Never invent a human approval.
- Distinguish implemented behavior, proposed behavior, and unverified claims. Report missing
  tools and failed checks honestly. Documentation-only changes do not require Java tests.
- Preserve accepted decision history. Add a superseding ADR when a decision changes; only
  status and supersession links may be updated in an accepted ADR.
- Do not implement feature code as part of a guidelines or planning task.

Current project: Java 21 target (full JDK required), Spring Boot, Maven, and H2 in memory. Feature endpoints do not
exist yet. See the board for setup verification before starting features.
