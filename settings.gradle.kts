pluginManagement {
    repositories {
        mavenLocal()
        gradlePluginPortal()
    }
}

rootProject.name = "DLibrary"

include("common")
include("bukkit")
include("bukkit:plugin")
findProject(":bukkit:plugin")?.name = "plugin"
