dependencies {
    compileOnlyApi(projects.wyvernCommon)
    compileOnly(libs.velocity) {
        // Requires Java 17
        exclude("com.velocitypowered", "velocity-brigadier")
    }
    annotationProcessor(libs.velocity)
}
