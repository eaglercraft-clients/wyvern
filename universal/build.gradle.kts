dependencies {
    api(projects.wyvernCommon)
    api(projects.wyvernBukkit)
    api(projects.wyvernVelocity)
    api(projects.wyvernFabric)
}

tasks {
    shadowJar {
        manifest {
            attributes["paperweight-mappings-namespace"] = "mojang"
        }
        archiveFileName.set("Wyvern-${project.version}.jar")
        destinationDirectory.set(rootProject.projectDir.resolve("build/libs"))
    }
    sourcesJar {
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
        rootProject.subprojects.forEach { subproject ->
            if (subproject == project) return@forEach
            val sourceSets = subproject.extensions.findByType(SourceSetContainer::class) ?: return@forEach
            from(sourceSets.named("main").map { it.allSource })
        }
    }
}
