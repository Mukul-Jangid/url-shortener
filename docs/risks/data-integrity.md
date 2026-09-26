# Risks — Data Integrity

## R-001: Short-code collision on write causes silent overwrite or failed insert

- **Likelihood**: Medium (while the interim naive generator is in use — see
  `docs/decisions/0003-id-generation-strategy.md`)
- **Impact**: High — a collision that isn't handled could overwrite an existing mapping or fail
  a create request the caller expected to succeed.
- **Mitigation**: DB-level uniqueness constraint on `code` + bounded retry in Phase 1
  (`GF-102`), tested including concurrent inserts/constraint failures as applicable. Any later
  generator change must also account for codes already stored; no automatic elimination claim.
- **Owning tasks**: `GF-101`, `GF-102`; reassess in `BF-201`

## R-006: Race condition on click-count increment under concurrent redirects

- **Likelihood**: Low (Mitigated)
- **Impact**: Low-Medium — undercounts analytics if unhandled; now mitigated.
- **Status**: Mitigated (`AMB-304`, `AMB-305`)
- **Mitigation**: Atomic DB-level increment (`UPDATE short_urls SET click_count = click_count + 1, last_accessed_at = :now WHERE code = :code`). Verified via multi-threaded integration test (`UrlAnalyticsConcurrencyTest.java`).
- **Owning tasks**: `AMB-304` (implementation), `AMB-305` (proof via test)
