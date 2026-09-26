# Control flow for AI-assisted execution

The binding process is [development workflow](../conventions/development-workflow.md).

1. Select an authorized task in the current iteration and check its dependencies.
2. Resolve scope and acceptance criteria; open its work log and mark it In Progress.
3. Implement the smallest useful change with appropriate tests.
4. Update relevant docs and Dev Notes, including decisions and limitations.
5. Run applicable quality gates and record evidence. Resolve failures within scope.
6. Move complete, verified work to In Review. The engineer reviews the concrete result,
   records acceptance or requested changes, and owns the transition to Done.
7. High-impact work requires explicit engineer approval before merge/acceptance.

Routine authorized local work does not require repeated permission. Never invent human
approval or test results. Keep one work log per task with meaningful rounds inside it.
