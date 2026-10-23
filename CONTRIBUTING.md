# Contributing to Wyvern

Wyvern is licensed under GPL-3.0-or-later (see [LICENSE](LICENSE), [NOTICE.md](NOTICE.md)). By contributing you agree your
changes are released under the same licence.

- Build and test: see [docs/BUILDING.md](docs/BUILDING.md). Run `./gradlew build` before opening a pull request.
- Keep changes focused; one topic per pull request.
- New source files need the licence header used by the existing files (copy from any file in `eagler/`).
- Code in `org.eaglercraftclients.wyvern.eagler` must have unit tests where the logic is testable without a server.
- Do not add code copied from Mojang/Microsoft sources or decompiled game code.
- Security issues: follow [SECURITY.md](SECURITY.md), not a public issue.
