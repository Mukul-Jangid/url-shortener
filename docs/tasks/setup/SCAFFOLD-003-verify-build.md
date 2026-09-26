# [SCAFFOLD-003] Verify the scaffold before feature work

| Field | Value |
|---|---|
| **Type** | Task |
| **Module** | Setup |
| **Status** | Not Started |
| **Depends on** | SCAFFOLD-002 |
| **Blocks** | GF-101 |
| **AI Work Log** | Open when work starts |

## Summary

Establish a reproducible, passing build and health check before adding endpoints.

## Scope and plan

The current environment has Java 21 but no Maven executable or Maven wrapper. The project
targets Java 17. Set up a reproducible Maven invocation (prefer a committed wrapper), verify
Java 17 compatibility, and fix only scaffold issues exposed by the checks. Record tool versions.
No feature endpoints, stack upgrades, database replacement, or unrelated cleanup.

## Acceptance Criteria

- [ ] Maven invocation and prerequisites are documented and work from this folder.
- [ ] Compile, tests, and formatting checks pass with recorded commands and output summaries.
- [ ] Application starts and `/actuator/health` returns a successful health response.
- [ ] README describes the actual H2 console configuration and local-only intended use.
- [ ] Setup failures and fixes are recorded; historical scaffold logs link to this verification.

## AI Collaboration Plan

Open the work log, establish tooling, run the gates, fix discovered scaffold issues, and record
results. Do not mark earlier work accepted on behalf of its reviewer.

## Definition of Done

Acceptance criteria verified, work log and Dev Notes updated, and engineer acceptance recorded.

## Dev Notes

Initial inspection on 2026-09-26: `java -version` reported 21.0.12; `mvn -version` failed with
`mvn: command not found`. This folder has no `.git` directory. Git initialization is separate
from proving the application works; do not claim commits or merge checks exist here yet.
