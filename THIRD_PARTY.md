# Third party software

- **ViaBackwards** (GPL-3.0-or-later), the project Wyvern is forked from: https://github.com/ViaVersion/ViaBackwards
- **ViaVersion** and its libraries (GPL-3.0-or-later / their own licences), used as a dependency: https://github.com/ViaVersion/ViaVersion
- **ViaRewind** (GPL-3.0-or-later): modified copy of its common sources in `rewind/`, see [NOTICE.md](NOTICE.md): https://github.com/ViaVersion/ViaRewind
- Other build and runtime dependencies are declared in `gradle/libs.versions.toml` and keep their own licences.

"Minecraft" is a trademark of Mojang AB. Wyvern is not affiliated with Mojang, Microsoft or ViaVersion.
- **ViaVersion** is a dependency of Wyvern and must be installed on the server. A fork with the Eaglercraft bridge for newer clients is at https://github.com/eaglercraft-clients/viaversion, which also carries an unmodified copy of **ViaRewind** in `third_party/viarewind`.
