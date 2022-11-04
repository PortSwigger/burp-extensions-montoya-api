import jetbrains.buildServer.configs.kotlin.v2018_2.BuildType
import jetbrains.buildServer.configs.kotlin.v2018_2.DslContext
import jetbrains.buildServer.configs.kotlin.v2018_2.buildSteps.GradleBuildStep
import jetbrains.buildServer.configs.kotlin.v2018_2.buildSteps.gradle
import jetbrains.buildServer.configs.kotlin.v2018_2.project
import jetbrains.buildServer.configs.kotlin.v2018_2.version

/*
The settings script is an entry point for defining a TeamCity
project hierarchy. The script should contain a single call to the
project() function with a Project instance or an init function as
an argument.

VcsRoots, BuildTypes, Templates, and subprojects can be
registered inside the project using the vcsRoot(), buildType(),
template(), and subProject() methods respectively.

To debug settings scripts in command-line, run the

    mvnDebug org.jetbrains.teamcity:teamcity-configs-maven-plugin:generate

command and attach your debugger to the port 8000.

To debug in IntelliJ Idea, open the 'Maven Projects' tool window (View
-> Tool Windows -> Maven Projects), find the generate task node
(Plugins -> teamcity-configs -> teamcity-configs:generate), the
'Debug' option is available in the context menu for the task.
*/

version = "2020.1"

project {
    buildType(PublishToNexus)
}

object PublishToNexus : BuildType({
    uuid = "76a8fa7a-4a29-4657-9d9d-951fab9cfbd6"
    name = "Publish to Nexus"
    type = Type.DEPLOYMENT

    vcs {
        root(DslContext.settingsRoot)
        cleanCheckout = true
    }

    steps {
        gradle {
            tasks = ":api:publishMavenJavaPublicationToInternalRepository"
            dockerImage = "docker-internal.devtools.portswigger.com/portswigger/desktop-linux:java-max"
            dockerPull = true
            dockerImagePlatform = GradleBuildStep.ImagePlatform.Linux
        }
    }

    failureConditions {
        executionTimeoutMin = 15
    }
})