# Risks — AI Usage

These are risks in *how AI is used to build this system*, distinct from risks in the system's
domain logic. They're process risks, mitigated by rules rather than code.

## R-009: AI-generated code weakens a security-sensitive area without adequate review

- **Likelihood**: Medium — this is a known failure mode of AI-assisted development generally,
  not specific to this project.
- **Impact**: High — could silently introduce a vulnerability that looks like normal code.
- **Mitigation**: Mandatory `Reviewer Decision: APPROVED` line in the relevant
  `docs/ai-work-log/entries/<TASK-ID>.md` for every task on the high-impact list (see
  `docs/conventions/ai-usage-rules.md`). No such change is merged on AI output alone, however
  plausible it looks.
- **Owning control**: `docs/conventions/ai-usage-rules.md`

## R-010: AI-suggested dependency introduces a vulnerable or unnecessary library

- **Likelihood**: Low-Medium.
- **Impact**: Medium — supply-chain risk, or simply unnecessary complexity/maintenance burden.
- **Mitigation**: Any new third-party dependency suggested by AI requires a one-line
  justification recorded in the work-log entry that introduced it (why it's needed, not just
  that it works) — see `GF-108` for a worked example of this rule in a task file.
- **Owning control**: `docs/conventions/ai-usage-rules.md`

## R-011: Secrets or PII accidentally entered into an AI prompt

- **Likelihood**: Low for this project (no real credentials exist in it), but the rule matters
  regardless of this specific project's current state.
- **Impact**: High if it ever did occur — credential leakage to a third-party service.
- **Mitigation**: Explicit rule recorded in `docs/decisions/0004-ai-usage-boundaries.md`: no
  credentials, secrets, or production data ever enter a prompt for this project.
- **Owning control**: `docs/decisions/0004-ai-usage-boundaries.md`
