# Building

Requirements: JDK 21 or newer. Gradle is provided by the wrapper.

```
./gradlew build          # compile, test, and assemble every platform
```

The distributable jar is `universal/build/libs/Wyvern-<version>.jar`. CI (`.github/workflows/build.yml`) builds on every
push and uploads the jar as an artifact.

## Tests that need Wyrm

`common/src/wyrmTest` runs Wyvern inside Wyrm's in-client Via. Wyrm's `client` module is not on the public ViaVersion
repository, so publish Wyrm to Maven Local first and pass `-PwithWyrm`:

```
(cd ../wyrm && ./gradlew :viaversion:publishToMavenLocal :viaversion-client:publishToMavenLocal)
./gradlew -PwithWyrm :wyvern-common:test
```

Without the flag these tests are not compiled, and the build uses the upstream ViaVersion snapshot as before.
