# [SCAFFOLD-003] Verify the scaffold before feature work

| Field | Value |
|---|---|
| **Type** | Task |
| **Module** | Setup |
| **Status** | In Review |
| **Depends on** | SCAFFOLD-002 |
| **Blocks** | GF-101 |
| **AI Work Log** | [SCAFFOLD-003](../../ai-work-log/entries/SCAFFOLD-003.md) |

## Summary

Establish a reproducible, passing build and health check before adding endpoints.

## Scope and plan

Maven 3.9.16 is installed. The user explicitly selected Java 21 as the project target;
see decision 0006. Verify a full JDK 21, a reproducible Maven invocation, and fix only scaffold
issues exposed by checks. Record tool versions. Add a Maven wrapper pinned to the verified Maven version and materialize the documented
Java packages using package documentation, without feature placeholders.
No feature endpoints, unrelated stack upgrades, database replacement, or unrelated cleanup.

## Acceptance Criteria

- [x] Documented Java package folders exist and explain their responsibilities.
- [x] Maven wrapper allows a fresh checkout with JDK 21 to build without a global Maven install.
- [x] Maven invocation and prerequisites are documented and work from this folder.
- [x] Compile, tests, and formatting checks pass with recorded commands and output summaries.
- [x] Application starts and `/actuator/health` returns a successful health response.
- [x] README describes the actual H2 console configuration and local-only intended use.
- [x] Setup failures and fixes are recorded; historical scaffold logs link to this verification.

## AI Collaboration Plan

Open the work log, establish tooling, run the gates, fix discovered scaffold issues, and record
results. Do not mark earlier work accepted on behalf of its reviewer.

## Definition of Done

Acceptance criteria verified, work log and Dev Notes updated, and engineer acceptance recorded.

## Dev Notes

Initial inspection on 2026-09-26: `java -version` reported 21.0.12; `mvn -version` failed with
`mvn: command not found`. At initial inspection this folder had no `.git` directory. SCAFFOLD-004 subsequently
initialized and pushed it to GitHub; repository setup does not establish build correctness.

Java 21 update: Maven target and current prerequisites now use Java 21 per decision 0006.
`mvn -q compile test spotless:check` stops at compile with `release version 21 not supported`.
The installed Java runtime lacks `javac` and `lib/ct.sym`; install the full JDK 21 and rerun
the gates. Tests, formatting, and application startup have not passed. `git diff --check` passes.
