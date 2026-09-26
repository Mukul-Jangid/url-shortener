# [SCAFFOLD-003] AI Work Log — Build setup

- **Opened**: 2026-09-26
- **Closed**: Pending verification and engineer acceptance
- **Task**: [Verify the scaffold](../../tasks/setup/SCAFFOLD-003-verify-build.md)

## Round 1 — Use Java 21

- **Intent**: User explicitly requested Java 21 as the project target.
- **Scope**: Change the Maven target and current setup guidance; preserve historical decisions
  with a superseding ADR. No feature work or unrelated dependency upgrades.
- **Context**: Maven 3.9.16 runs on Java 21.0.12. Only the Java runtime packages are installed;
  `javac` and `lib/ct.sym` are absent. The previous Java 17 target failed to compile.
- **Plan**: Set `java.version` to 21, update current docs, then run build gates and report results.
- **Engineer decision**: Java 21 explicitly requested; final build acceptance pending.

- **Output**: Java 21 Maven target, updated current setup guidance, and ADR 0006.
- **Verification**: `mvn -q compile test spotless:check` failed at compile with
  `release version 21 not supported`; remaining gates did not run. `git diff --check` passed.
- **Blocker**: Full JDK 21 is absent; the installed JRE is incomplete for compilation.
  Install JDK 21, then rerun build and startup checks. No feature code changed.

## Round 2 — Project structure and verification

- **Intent**: User installed the JDK and requested proper project folders, then a remote branch push.
- **Scope**: Existing documented layers represented by package-info.java files, pinned Maven
  wrapper, scaffold checks and formatting, and concise setup documentation. No feature classes.
- **Tooling**: JDK/javac 21.0.12.1 and Maven 3.9.16 confirmed. Maven Wrapper is build tooling
  for repeatable checkout setup; no application dependency is added.
- **Plan**: Build/test/format, start the application, check health, commit and push the current branch.
