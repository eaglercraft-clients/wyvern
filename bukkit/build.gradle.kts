dependencies {
    compileOnlyApi(projects.wyvernCommon)
    compileOnly(libs.paper) {
        exclude("com.google.code.gson", "gson")
        exclude("javax.persistence", "persistence-api")
    }
}
