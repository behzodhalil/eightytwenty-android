pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        maven("https://jitpack.io")
    }
    enableFeaturePreview("VERSION_CATALOGS")
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "EightyTwenty"
include(":app")
include(":core-ui")
include(":ui-undo-redo")
include(":ui-toast")
include(":ui-expandable-view")
include(":core-data")
include(":domain")
include(":features")
include(":features:onboarding")
include(":core")
include(":core:features")
include(":features:auth")
include(":features:auth:domain")
include(":features:auth:ui")
include(":features:home")
include(":features:home:ui")
include(":features:note")
include(":features:note:ui")
