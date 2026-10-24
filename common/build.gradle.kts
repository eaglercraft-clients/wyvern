plugins {
    id("net.kyori.blossom")
    id("org.jetbrains.gradle.plugin.idea-ext")
}

sourceSets {
    main {
        blossom {
            javaSources {
                property("version", project.version.toString())
                property("impl_version", "git-Wyvern-${project.version}:${rootProject.latestCommitHash()}")
            }
        }
    }
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.11.4"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation(libs.viaver) // EaglerChannelStorage implements a ViaVersion interface

    compileOnlyApi(libs.viaver)
    compileOnlyApi(libs.netty)
    compileOnlyApi(libs.guava)
    compileOnlyApi(libs.checkerQual)
}

// Tests that run Wyvern inside Wyrm's in-client Via (WyvernClient). They need Wyrm's `client` module, which is not on
// the public ViaVersion repository: publish Wyrm to Maven Local first, then pass -PwithWyrm. See docs/BUILDING.md.
if (providers.gradleProperty("withWyrm").isPresent) {
    sourceSets.test { java.srcDir("src/wyrmTest/java") }
    dependencies {
        testImplementation("com.viaversion:viaversion-client:${libs.versions.viaver.get()}")
        testImplementation(libs.netty)
        testImplementation(libs.guava)
    }
}

java {
    withJavadocJar()
}

tasks.test {
    useJUnitPlatform()
}

// Task to quickly test/debug code changes using https://github.com/ViaVersion/ViaProxy
// For further instructions see the ViaProxy repository README
val prepareViaProxyFiles = tasks.register<Copy>("prepareViaProxyFiles") {
    description = "Prepares the ViaProxy run dir for the runViaProxy task"
    dependsOn(project.tasks.shadowJar)

    from(project.tasks.shadowJar.map { it.archiveFile.get().asFile })
    into(layout.projectDirectory.dir("run/jars"))

    val projectName = project.name
    rename { "${projectName}.jar" }
}

val cleanupViaProxyFiles = tasks.register<Delete>("cleanupViaProxyFiles") {
    description = "Deletes ViaProxy logs and the compiled project jar from its jars dir"
    delete(
        layout.projectDirectory.file("run/jars/${project.name}.jar"),
        layout.projectDirectory.dir("run/logs")
    )
}

val viaProxyConfiguration: Configuration = configurations.create("viaProxyConfiguration") {
    dependencies.add(rootProject.libs.viaProxy.get().copy().setTransitive(false))
}

tasks.register<JavaExec>("runViaProxy") {
    description = "Runs ViaProxy locally"
    dependsOn(prepareViaProxyFiles)
    finalizedBy(cleanupViaProxyFiles)

    mainClass.set("net.raphimc.viaproxy.ViaProxy")
    classpath = viaProxyConfiguration
    workingDir = layout.projectDirectory.dir("run").asFile
    jvmArgs = listOf("-DskipUpdateCheck", "-Dviaproxy.gui.autoStart")

    if (System.getProperty("viaproxy.disableExtraPlatforms") != null) {
        jvmArgs("-Dviaproxy.enableViaBedrock=false", "-Dviaproxy.enableViaLegacy=false", "-Dviaproxy.enableViaAprilFools=false")
    }
}
