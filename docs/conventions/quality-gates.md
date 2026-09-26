# Quality gates

Complete the applicable checks before moving work to In Review. Engineer acceptance is
required for Done. Record exact commands and outcomes in the task work log.

| Change | Required checks |
|---|---|
| Java, application configuration, dependencies, or build | `mvn -q compile`, `mvn -q test`, `mvn -q spotless:check` |
| Documentation only | Check local links, board/task consistency, and claims against the actual repository; `git diff --check` |
| Brownfield implementation | Existing public-contract tests still pass; add tests for the changed behavior |
| High-impact implementation | Applicable automated checks plus engineer review before merge/acceptance |

Use `./mvnw` instead of `mvn` once a wrapper is added. A missing tool or failed command is a
recorded blocker, not a pass. No Java build is required for documentation-only changes.

Update architecture, decisions, and risks in the task that changes them. Later checkpoints
verify this happened. Do not alter public-contract assertions merely to make a refactor pass;
internal implementation tests may change with a documented reason.

## High-impact review

Use the list in [AI usage rules](ai-usage-rules.md). Explain which surfaces changed, their
failure cases, and the evidence for correctness. Consider accepted URL targets, code uniqueness
and predictability, schema changes, exposed data, dependency justification, and abuse controls
where relevant. An agent prepares this evidence before requesting engineer review.
