# System Overview

## Purpose

A URL shortener service: accepts a long URL, returns a short code that redirects to it, and
tracks usage analytics. Built as the artifact for an AI-assisted software engineering exercise —
see the root `README.md` and `docs/tasks/` for how it's being developed, not just what it does.

## In scope (current interpretation — see `docs/decisions/` for how this was decided)

- Create / redirect / lookup / deactivate short URLs
- Basic click analytics (scope finalized in `docs/tasks/analytics/AMB-301-*.md`)
- Reasonable reliability characteristics for the redirect hot path (caching, graceful degradation)
- Abuse controls (rate limiting, redirect-target validation)

## Explicitly out of scope (unless a future task adds it deliberately)

- User accounts / authentication
- Custom aliases chosen by the caller
- Link expiry
- Multi-tenant isolation

If any of these get added later, that decision and its rationale belongs in
`docs/decisions/` before implementation starts, and this file gets updated to move the item out
of "out of scope."

## Consumers of this system (assumed)

Single API consumer type for this exercise (no distinct "admin" vs "end user" roles modeled).
If that assumption changes, it changes the shape of `03-api-design.md` non-trivially and should
be raised as a new decision, not folded in silently.
