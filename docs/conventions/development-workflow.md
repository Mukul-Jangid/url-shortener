# Development workflow

Effective from 2026-09-26, following the user's direction to establish guidelines first and
build a workable solution in small iterations. See [decision 0005](../decisions/0005-incremental-development.md).

## 1. Choose the smallest useful outcome

One iteration should end with behavior someone can try. The first feature iteration is:
create a short link, follow it, and get a clear error for an invalid URL or unknown code.
Metadata and deactivation follow once that flow works.

Keep Java, Spring Boot, Maven, H2, and the existing controller/service/repository structure.
Start with direct database lookups and random Base62 codes with a unique constraint and bounded
collision retries. Add abstractions only when they simplify a current responsibility.

Basic correctness belongs in the first version: input validation, bounded operations, database
uniqueness, clear errors, and tests. Caching, production infrastructure, detailed analytics,
and generator replacement belong to later, explicitly scoped work. Do not deliberately build
a defect to manufacture a future refactoring exercise.

## 2. Make a task ready before starting it

The task must contain:

- A user-visible outcome or a concrete setup/documentation deliverable.
- What is included and what is deferred.
- Acceptance criteria that can be checked, including relevant failure cases.
- Dependencies and a small implementation plan.
- How it will be verified and whether it touches a high-impact area.

Resolve questions that change the task's behavior before implementation. Routine choices
within the agreed scope can be made and documented by the agent. Future tasks can remain
brief; refine them when their iteration is selected. A backlog entry is not authorization
to implement every feature it mentions.

## 3. Implement and verify together

Open one work-log file per task before edits and record meaningful rounds in that file.
Update the board and task status together. Build the behavior and its tests in the same task.
Use the [quality gates](quality-gates.md) appropriate to the files changed.

Before review, update affected documentation and record commands, results, limitations, and
deviations in Dev Notes. Later documentation or testing tasks are checkpoints, not permission
to postpone this work. Stop expanding a task once its acceptance criteria are met.

## 4. Keep review separate from permission to work

| Status | Meaning |
|---|---|
| Not Started | Backlog item; check readiness and dependencies before work |
| In Progress | Authorized work is underway and its log is open |
| Blocked | A stated dependency or missing input prevents progress; record the next action |
| In Review | Deliverable and applicable checks are complete; engineer review is pending |
| Done | Engineer accepted the work and applicable gates passed |

Read-only exploration and authorized local edits/checks do not require repeated permission.
An agent completes reviewable work before asking for the review required by existing policy.
High-impact approval is required before merge/acceptance, not before every implementation step.
Do not fill in the engineer's decision or claim a failed/unrun gate passed. A task with an
unresolved required gate remains In Progress or Blocked, with the reason recorded.

## 5. Document decisions at the right size

Use plain language and concrete examples. Keep one topic per page or section. Explain:

1. What problem are we solving now?
2. What did we choose and why is it enough for this iteration?
3. What trade-off or limitation are we accepting?
4. What evidence or requirement would make us revisit it?

Use task Dev Notes for small local choices. Use a numbered ADR for changes to public behavior,
data ownership, architecture, dependencies with lasting impact, or development policy. Link
to the source of a decision instead of copying it across documents. Accepted ADR content is
historical; a new ADR supersedes it, with status/link updates allowed on the old record.

## 6. End each iteration with evidence

Demonstrate the working flow, run relevant gates, and review known limitations. Select the
next improvement based on a real requirement, observed problem, or explicit exercise goal.
For an exercise-driven change, name the learning objective and trade-off; do not claim an
unmeasured performance or security improvement.

The original assignment is referenced in historical logs but is not present in this folder.
Keep its existing scenario backlog for traceability; do not invent additional assignment
requirements or treat every originally suggested design as mandatory.
