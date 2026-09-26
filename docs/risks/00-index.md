# Risk Register — Index

Master table — quick reference across all categories. Each category has its own file with fuller
context (why the risk exists, deeper mitigation reasoning) for the non-trivial entries. Update
this table's Status column as tasks close risks out; detail lives in the category file, not here.

| ID | Risk | Category | Likelihood | Impact | Status | Mitigating task |
|---|---|---|---|---|---|---|
| R-001 | Short-code collision on write causes silent overwrite/failed insert | [Data Integrity](data-integrity.md) | Medium | High | Open (interim, tracked) | GF-102; BF-201 review |
| R-002 | Excessive collision retries degrade write latency | [Performance/Scalability](performance-scalability.md) | Medium | Medium | Open (interim, tracked) | GF-102; BF-201 review |
| R-003 | Sequential/guessable short codes allow enumeration | [Security](security.md) | Medium | Medium | Open | GF-102; BF-201 review |
| R-004 | Unsafe schemes and malicious destinations | [Security](security.md) | Medium | High | Open | GF-102/GF-106; VAL-403 review |
| R-005 | Redirect endpoint becomes a bottleneck under load | [Performance/Scalability](performance-scalability.md) | Unknown until measured | Medium | Open | VAL-404; BF-203 if selected |
| R-006 | Race condition on click-count increment | [Data Integrity](data-integrity.md) | Low | Low-Medium | Mitigated | AMB-304, AMB-305 |
| R-007 | Unrestricted create endpoint enables spam/abuse | [Security](security.md) | Medium | Medium | Open | VAL-402 |
| R-008 | Datastore unavailability takes down redirect path entirely | [Availability](availability.md) | Low (prototype) / Medium (prod) | High | Documented limitation, not implemented | — |
| R-009 | AI-generated code weakens a security-sensitive area without review | [AI Usage](ai-usage.md) | Medium | High | Open (process control, ongoing) | conventions/ai-usage-rules.md |
| R-010 | AI-suggested dependency introduces vulnerable/unnecessary library | [AI Usage](ai-usage.md) | Low-Medium | Medium | Open (process control, ongoing) | conventions/ai-usage-rules.md |
| R-011 | Secrets/PII accidentally entered into an AI prompt | [AI Usage](ai-usage.md) | Low | High | Open (process control, ongoing) | decisions/0004-ai-usage-boundaries.md |
| R-012 | Analytics inadvertently stores PII without justification | [Security](security.md) | Medium | Medium | Open | AMB-301 |

## Status values

- **Open** — not yet mitigated.
- **Open (interim, tracked)** — accepted temporarily by an explicit decision (see linked ADR),
  with a planned task that closes it.
- **Mitigated** — mitigating task closed; link to it stays here permanently as history.
- **Documented limitation** — accepted as out-of-scope for this exercise, not silently ignored.

## Review cadence

Revisited at the end of every iteration in `docs/tasks/00-index.md`. New risks discovered mid-phase
are added immediately (new row here + entry in the relevant category file), not batched for
later.
