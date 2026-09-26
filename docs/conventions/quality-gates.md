# Quality Gates

Must pass before a task moves to `Done` in `docs/tasks/00-index.md`.

| Gate | Command / Check | Blocking? |
|---|---|---|
| Compile | `mvn -q compile` | Yes |
| Unit + integration tests | `mvn -q test` | Yes |
| Formatting | `mvn -q spotless:check` | Yes |
| Manual security review | Checklist below, for changes touching a high-impact surface | Yes, for high-impact changes only |
| Regression check | For brownfield tasks: existing tests for the touched module still pass with assertions unmodified (only setup/mocking may change) | Yes, for brownfield tasks |
| Docs sync | Architecture/decisions/risks docs updated if the task changed system shape | Yes |

## Manual security review checklist (high-impact changes only)

- [ ] Does this change affect what URLs/schemes are accepted as redirect targets?
- [ ] Does this change affect uniqueness/predictability of short codes?
- [ ] Does this change expose any new data in an API response that wasn't there before?
- [ ] Does this change introduce a new dependency, and if so, is it justified in the work log?
- [ ] Does this change alter rate-limiting or abuse-control behavior?
