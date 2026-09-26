# Naming Conventions

- Classes: `PascalCase`, suffixed by role where it aids clarity (`UrlController`, `UrlService`,
  `ShortUrlRepository`, `UrlNotFoundException`).
- REST resources: plural nouns, versioned (`/api/v1/urls`), lowercase, hyphenated if multi-word.
- Test methods: `methodName_condition_expectedOutcome` (e.g.,
  `createShortUrl_whenLongUrlInvalid_throwsBadRequest`).
- Task IDs: `<PREFIX>-<number>`, prefix indicates module/phase (`GF-` greenfield, `BF-`
  brownfield, `AMB-` ambiguous scenario, `VAL-` validation/hardening, `DOC-` documentation,
  `SCAFFOLD-` pre-task setup). Task IDs are permanent once assigned — never reused or renumbered.
