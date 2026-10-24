# Changelog

## Unreleased

- Added the `rewind` module: ViaRewind's common code built against Wyvern, plus `RewindClient`, so an Eaglercraft 1.8 client with in-client Via can join 1.9+ servers.
- Added `WyvernClient`: Wyvern inside an Eaglercraft client that embeds Via with Wyrm's `client` module, so the client can join newer servers.
- Renamed the project to Wyvern (package `org.eaglercraftclients.wyvern`, config `wyvern.yml`, jar `Wyvern-<version>.jar`).
- Added the Eaglercraft plugin channel bridge (`EAG|...` <-> `eag:...`) for the 1.13 <-> 1.12.2 step.
- Removed upstream publishing, funding and agent configuration.
- Based on ViaBackwards 5.12.2-SNAPSHOT (upstream commit c3bdf72).
