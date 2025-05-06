pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "EMTask"
include(":app")
include(":common:data")
include(":common:android")
include(":common:domain")
include(":features")
include(":features:main")
include(":features:navigation")
include(":features:onboarding")
include(":features:sign_in")
include(":common")
