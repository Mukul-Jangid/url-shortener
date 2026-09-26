# Control Flow for AI-Assisted Execution

This is the process every task in `docs/tasks/` follows. It exists so "AI-assisted" doesn't mean
"AI decides" — the engineer stays the control point at every arrow below.

```
1. Engineer picks a task file from docs/tasks/<module>/
2. Engineer fills in the task's "AI Collaboration Plan" section if not already done:
     intent, constraints, acceptance criteria, technical context to hand the AI
3. Engineer opens a new file in docs/ai-work-log/entries/ (from the template) for this task
4. Engineer prompts the AI tool using the framing from step 2 — not an open-ended request
5. AI produces output (code / tests / docs / review comments)
6. Engineer reviews and records in the work-log entry:
     a. Accepted as-is    -> rationale, proceed to quality gates
     b. Accepted w/ edits -> diff summary + rationale for edits
     c. Rejected          -> rationale, re-prompt or write manually
7. Change passes Quality Gates (docs/conventions/quality-gates.md) before merge
8. High-impact changes (docs/conventions/ai-usage-rules.md "high-impact list") require an
   explicit "Reviewer Decision: APPROVED" line in the work-log entry — no silent merges
9. Task file's Status updated; task file's "Dev Notes" section filled in with what actually
   happened (this is what makes the task file useful as a future reference, not just a plan)
10. docs/architecture/, docs/decisions/, and/or docs/risks/ updated if the task changed system
    shape, made a new decision, or introduced/mitigated a risk
```

## Why each task gets its own work-log file instead of one shared log

A single growing log file becomes hard for a future agent to search or reason about once there
are dozens of entries across unrelated modules. One file per task (named by task ID) means:
context for a given piece of code can be found by matching the task ID referenced in its
commit/comment, without scanning an ever-growing document. `docs/ai-work-log/00-index.md`
maintains the chronological/task-ID index across all of them.

## Roles

- **Engineer (you)**: defines intent/constraints, prompts, reviews, decides accept/edit/reject,
  approves high-impact changes, owns correctness and production-readiness.
- **AI (this conversation)**: generates code/tests/docs within the stated constraints, flags
  trade-offs and risks it notices, never merges its own output, never marks a task `Done`.
