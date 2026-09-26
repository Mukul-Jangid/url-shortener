# [SCAFFOLD-002] Establish incremental development guidelines

| Field | Value |
|---|---|
| **Type** | Task |
| **Module** | Setup and guidelines |
| **Status** | In Review |
| **Depends on** | Existing scaffold and documentation |
| **AI Work Log** | [SCAFFOLD-002](../../ai-work-log/entries/SCAFFOLD-002.md) |

## Summary

Explore the existing project and establish a clear path for agents to build a working core
before adding complexity. Requested directly by the user on 2026-09-26.

## Scope

Guidelines, documentation consistency, task sequencing, and an agent entry point. No feature
implementation, dependency installation, or assertion that the scaffold has passed its gates.

## Acceptance Criteria

- [x] Agent entry point links to current guidelines and task board.
- [x] Guidelines explain readiness, iteration scope, verification, review, and decision records.
- [x] First working flow and next setup task are explicit; later complexity is conditional.
- [x] Immediate task dependencies and scope agree with the guidelines.
- [x] Known documentation contradictions are corrected without fabricating historical approval.
- [x] Documentation links and task-board consistency are checked.

## AI Collaboration Plan

Read the scaffold, conventions, architecture, decisions, tasks, and work logs. Record findings,
update the documents, and check consistency. Preserve accepted ADR history and task IDs.

## Definition of Done

Applicable documentation checks pass, Dev Notes and work log contain evidence, and engineer
acceptance is recorded under the existing review policy.

## Dev Notes

Added an agent entry point and development workflow; recorded ADR 0005 without rewriting
accepted decision history. Reordered the first flow, moved basic validation before endpoints,
kept tests/docs in their feature tasks, removed premature analytics fields and mandatory cache
dependencies, and corrected unsupported risk claims. Historical Closed/Done labels were
corrected to match pending review. GitHub setup was completed as SCAFFOLD-004 at the user's
request before resuming this task. Validation passed: 71 local Markdown links across 68 documents resolve; all 27 board
entries match task titles/statuses/dependencies; the dependency graph has no cycles;
`git diff --check` passes. No source or build configuration changed. Maven tests were not
run for this documentation-only task. Engineer acceptance remains pending.
