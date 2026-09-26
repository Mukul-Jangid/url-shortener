# Commit Hygiene

- Commit messages reference the task ID they implement, e.g.
  `GF-102: add POST /api/v1/urls create endpoint`.
- A commit that is substantially AI-generated and accepted with only minor edits should say so
  in the commit body, pointing to the work-log entry rather than duplicating its content:
  `AI-assisted: see docs/ai-work-log/entries/GF-102.md`.
- One task = one logical set of commits where practical; avoid mixing unrelated task IDs in a
  single commit, since it breaks the traceability the whole `docs/` structure is built around.
