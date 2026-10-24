// ViaRewind's common sources (see NOTICE.md) built against Wyvern in place of ViaBackwards.
dependencies {
    compileOnlyApi(projects.wyvernCommon)
    compileOnly(libs.velocityNative)

    testImplementation(platform("org.junit:junit-bom:5.11.4"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation(projects.wyvernCommon)
    testImplementation(libs.viaver)
    testImplementation(libs.netty)
    testImplementation(libs.guava)
}

tasks.test {
    useJUnitPlatform()
}

// Tests that run inside Wyrm's in-client Via; see ../docs/BUILDING.md. Publish Wyrm to Maven Local, then pass -PwithWyrm.
if (providers.gradleProperty("withWyrm").isPresent) {
    sourceSets.test { java.srcDir("src/wyrmTest/java") }
    dependencies {
        testImplementation("com.viaversion:viaversion-client:${libs.versions.viaver.get()}")
    }
}
