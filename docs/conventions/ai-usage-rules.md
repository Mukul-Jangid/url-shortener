# AI Usage Rules

Binding — see `docs/decisions/0004-ai-usage-boundaries.md` for the rationale behind these.

1. No secrets, credentials, or production data ever go into a prompt.
2. Every AI interaction touching `src/` gets an entry in `docs/ai-work-log/entries/<TASK-ID>.md`
   *before* the resulting code is considered mergeable.
3. High-impact changes require an explicit `Reviewer Decision: APPROVED` line from the engineer
   — never inferred, never implicit. High-impact list:
   - Authentication/authorization
   - Redirect target validation logic (open-redirect surface)
   - Short-code / ID generation strategy
   - Database schema changes
   - Public API request/response contract changes
   - Rate-limiting/abuse-control logic
4. New third-party dependencies suggested by AI require a one-line justification in the
   corresponding work-log entry (why it's needed, not just that it works).
5. The engineer, not the AI, decides when a task is `Done` in `docs/tasks/`.
6. If AI output can't be explained by the engineer in their own words, it isn't accepted as-is —
   either it's understood well enough to take ownership of, or it's reworked/rejected.
