# Error Handling

- No raw stack traces or exception messages returned to the client.
- All error responses use RFC 7807 `ProblemDetail` (Spring's built-in support), with a stable
  `type`/`title` per error category and a human-readable `detail`.
- Domain exceptions extend a common base (`UrlShortenerException`) mapped centrally in a single
  `exception/` `@ControllerAdvice` — controllers never catch exceptions individually (see
  `GF-106` for the task that establishes this).
- Tests should assert on HTTP status and `ProblemDetail` `type`, not on exact message text —
  keeps tests resilient to later refactors (e.g., `BF-202`) that shouldn't change the contract.
