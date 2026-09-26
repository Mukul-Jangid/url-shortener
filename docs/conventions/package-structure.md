# Package Structure

```
com.urlshortener
├── controller   — REST endpoints only. No business logic. Maps DTO <-> service calls.
├── service      — Business rules live here. No direct HTTP/JPA-specific concerns leaking in.
├── repository   — Spring Data JPA interfaces only.
├── domain       — JPA entities / core model.
├── dto          — Request/response payloads. Never expose entities directly over the API.
├── exception    — Custom exceptions + @ControllerAdvice mapping to ProblemDetail.
└── config       — Cross-cutting Spring configuration.
```

**Dependency rule**: a class only depends "downward" in this list — controller → service →
repository. Services never depend on controllers; repositories never depend on services. This is
enforced by convention and code review for this project's size, not by build tooling (e.g., no
ArchUnit rule configured — could be added as a hardening task if desired, but isn't in the
current backlog).

Each package is tracked with a `package-info.java` describing its responsibility. Add classes
when their feature task starts. Keep tests under the matching `src/test/java` package as tests
are introduced; do not create empty test classes or speculative subpackages.
