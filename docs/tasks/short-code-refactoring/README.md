# Short Code Refactoring & Caching

## Plain Language Overview

This task folder contains tasks for improving how short codes are generated and cached after the basic application works:

1. **BF-201 (Impact Analysis)**: Analyze all classes, repositories, and tests that touch short code generation before making changes.
2. **BF-202 (Refactor Code Generation)**: Upgrade short code creation from random string generation to a counter-based generator (or improved generator) to prevent code collision retries.
3. **BF-203 (Caching Layer)**: Add an in-memory caching layer (e.g. Caffeine cache) in front of short link redirects (`GET /{code}`) to speed up lookup times.
4. **BF-204 (Regression Tests)**: Run tests to verify that changing the code generator and adding caching did not break existing link creation or redirection behavior.
5. **BF-205 (Documentation Updates)**: Update architecture and decision docs to reflect the new generator and caching behavior.
