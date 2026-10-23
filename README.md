# Wyvern

**ViaBackwards for Eaglercraft: lets older clients, Eaglercraft clients included, join newer Minecraft servers.**

Wyvern is a fork of [ViaBackwards](https://github.com/ViaVersion/ViaBackwards) by the ViaVersion
contributors, licensed under the GNU GPL v3 (see [`LICENSE`](LICENSE) and [`NOTICE.md`](NOTICE.md)). It requires
[ViaVersion](https://hangar.papermc.io/ViaVersion/ViaVersion). All of ViaBackwards' version translation is kept; what this
fork adds is handling for the things Eaglercraft clients do that vanilla clients do not.

## What is different from ViaBackwards

- **Eaglercraft plugin channels.** Eaglercraft's own channels (`EAG|Skins-1.8` and the like) are not legal channel names
  on 1.13+ servers, so ViaVersion drops them for clients on 1.12.2 and older. This fork maps them to valid identifiers
  (`eag:skins-1.8`) and gives replies back to the client under the spelling it used. Details and limits:
  [`docs/EAGLER.md`](docs/EAGLER.md).
- **Own identity.** New name, package (`org.eaglercraftclients.wyvern`), plugin ids, config file
  (`wyvern.yml`) and build artifact, so it does not collide with upstream ViaBackwards. Do not install both.

What is **not** done yet is listed honestly in [`docs/EAGLER.md`](docs/EAGLER.md). In short: the channel bridge is covered
by unit tests, but it has not been run against a real Eaglercraft client and server pair.

## Platforms

Bukkit/Paper, Velocity, Fabric and Sponge, as upstream. Needs JDK 17+ to build.

## Building

```sh
./gradlew build
```

The jar is written to `build/libs/`. Every push is also built by GitHub Actions; the jar is attached to the run as an artifact.

## Credits

ViaBackwards and ViaVersion are the work of the contributors listed in the
[upstream repository](https://github.com/ViaVersion/ViaBackwards/graphs/contributors).
This fork keeps their copyright notices and licence. Eaglercraft is by lax1dude and ayunami2000 and contributors.

## Documentation and licence

- [Repository layout](docs/ARCHITECTURE.md), [building](docs/BUILDING.md), [Eaglercraft support](docs/EAGLER.md)
- [Contributing](CONTRIBUTING.md), [Code of Conduct](CODE_OF_CONDUCT.md), [Security](SECURITY.md), [Changelog](CHANGELOG.md)
- Licence: GPL-3.0-or-later, see [LICENSE](LICENSE), [NOTICE.md](NOTICE.md) and [THIRD_PARTY.md](THIRD_PARTY.md)
