@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven("https://plugins.gradle.org/m2/")
        jcenter() // Warning: this repository is going to shut down soon
    }
}

rootProject.name = "WebToonDemo"
include(":app")