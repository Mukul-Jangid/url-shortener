# URL Shortener — AI-Assisted Engineering Exercise

## Status

Application scaffold present; no feature endpoints yet. Incremental development guidelines are prepared for review before feature work. See [development guidelines](docs/conventions/development-workflow.md) and the
[task board](docs/tasks/00-index.md). Build verification is tracked in SCAFFOLD-003: Maven is
not installed in the inspected environment and no wrapper is present.

Private repository: [Mukul-Jangid/url-shortener](https://github.com/Mukul-Jangid/url-shortener).

## Before you write any feature code

Read `docs/00-INDEX.md` first. It explains what each folder in `docs/` is for and how they gate
each other. In short: no task starts without a plan in `docs/tasks/`, no AI interaction happens
without a corresponding entry in `docs/ai-work-log/entries/`, and no change merges without
passing the gates in `docs/conventions/quality-gates.md`.

## Requirements

- Java 17
- Maven 3.9+ (or use the included `mvnw` wrapper once added)

## Run it

```bash
mvn spring-boot:run
```

The app starts on `http://localhost:8080`. Health check: `http://localhost:8080/actuator/health`.
H2 console (currently enabled in the default configuration; intended for local use): `http://localhost:8080/h2-console` (JDBC URL:
`jdbc:h2:mem:urlshortener`, user `sa`, empty password).

## Test it

```bash
mvn test
```

## Known limitations (current state)

- No feature endpoints yet (Phase 0 only) — this will be updated as Phase 1 lands.
- H2 is in-memory: data does not persist across restarts (see [decision 0002](docs/decisions/0002-persistence-choice.md)).
- No authentication/authorization layer (not in scope per current requirement normalization —
  see [system scope](docs/architecture/00-overview.md)).

## Where things are documented

| Question | See |
|---|---|
| Why did we choose X? | `docs/decisions/` |
| What's the system shape? | `docs/architecture/` |
| What's being built, in what order? | `docs/tasks/00-index.md` (board), then per-task files by module |
| How was AI used, and what did the engineer accept/reject? | `docs/ai-work-log/entries/` |
| What could go wrong, and how is it guarded against? | `docs/risks/00-index.md` |
| What rules does all code (including AI-generated) follow? | `docs/conventions/` |
