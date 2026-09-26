# Reliability, performance, and scalability

These are iteration goals, not claims about implemented behavior. No feature endpoints or
performance measurements exist yet.

| Concern | First working version | Revisit when |
|---|---|---|
| Correctness | Unique codes, bounded collision retries, explicit errors, behavior tests | Evidence exposes a correctness gap |
| Reliability | Direct H2 lookups; data is lost on restart | Durable storage or datastore-outage behavior becomes a requirement |
| Performance | Simple lookup by unique code; no cache | A baseline measurement or explicit exercise goal justifies BF-203 |
| Input handling | Validate syntax and HTTP/HTTPS targets at creation | VAL-401 identifies additional controls needed for the intended deployment |
| Abuse | Local prototype; no authentication or rate limiting | Public exposure is proposed; scope VAL-402/VAL-403 first |
| Analytics | No fields or writes yet | AMB-301 defines counting and failure behavior |
| Generation | Random Base62, DB uniqueness, bounded retry | BF-201 finds a reason to change it; a counter is not automatically safer |

H2 keeps setup simple but does not demonstrate durable production storage or external-database
failure recovery. A cache, if added, needs explicit consistency and failure behavior; its
presence alone does not establish availability. No production latency or scale target is
promised without a workload and measurements.
