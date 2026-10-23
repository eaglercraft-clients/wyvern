# Repository layout

| Folder | Contents |
| --- | --- |
| `common/` | Platform independent core: protocol rewriters (`protocol/`), the Eaglercraft bridge (`eagler/`), config, API, tests. |
| `bukkit/` | Bukkit/Spigot/Paper plugin entry point and `plugin.yml`. |
| `fabric/` | Fabric mod entry point and `fabric.mod.json`. |
| `velocity/` | Velocity plugin entry point. |
| `universal/` | Assembles the single multi-platform jar (`Wyvern-<version>.jar`). |
| `build-logic/` | Shared Gradle convention plugins. |
| `docs/` | Documentation: this file, [BUILDING](BUILDING.md), [EAGLER](EAGLER.md). |
| `.github/` | CI workflow, issue and pull request templates, CODEOWNERS. |

Licence files at the root: `LICENSE` (GPL-3.0), `NOTICE.md` (upstream credit and what changed), `THIRD_PARTY.md`.
