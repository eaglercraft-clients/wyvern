# Building

Requirements: JDK 21 or newer. Gradle is provided by the wrapper.

```
./gradlew build          # compile, test, and assemble every platform
```

The distributable jar is `universal/build/libs/Wyvern-<version>.jar`. CI (`.github/workflows/build.yml`) builds on every
push and uploads the jar as an artifact.
