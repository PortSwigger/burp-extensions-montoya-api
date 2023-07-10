import jetbrains.buildServer.configs.kotlin.v2018_2.BuildType
import jetbrains.buildServer.configs.kotlin.v2018_2.DslContext
import jetbrains.buildServer.configs.kotlin.v2018_2.buildSteps.GradleBuildStep
import jetbrains.buildServer.configs.kotlin.v2018_2.buildSteps.ScriptBuildStep
import jetbrains.buildServer.configs.kotlin.v2018_2.buildSteps.gradle
import jetbrains.buildServer.configs.kotlin.v2018_2.project
import jetbrains.buildServer.configs.kotlin.v2018_2.version
import jetbrains.buildServer.configs.kotlin.v2018_2.buildSteps.script
import jetbrains.buildServer.configs.kotlin.v2018_2.triggers.vcs

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
    buildType(CompileAndGenerateJavaDocs)
    buildType(PublishToNexus)
}

object CompileAndGenerateJavaDocs : BuildType({
    uuid = "603cefbe-4e79-4e9d-a12f-91923d088ca3"
    name = "Compile and generate Java Docs"
    maxRunningBuilds = 1

    vcs {
        root(DslContext.settingsRoot)
        cleanCheckout = true
    }

    steps {
        script {
            scriptContent = """
                git rm -r docs
            """
            dockerImage = "docker-internal.devtools.portswigger.com/portswigger/desktop-linux:java-max"
            dockerPull = true
            dockerImagePlatform = ScriptBuildStep.ImagePlatform.Linux
        }

        gradle {
            tasks = "javadoc"
            dockerImage = "docker-internal.devtools.portswigger.com/portswigger/desktop-linux:java-max"
            dockerPull = true
            dockerImagePlatform = GradleBuildStep.ImagePlatform.Linux
        }

        script {
            scriptContent = """
                mv build/docs .
                git add docs
                git commit -m "Automated update of Java Docs"
                git pull
                git push
            """
            dockerImage = "docker-internal.devtools.portswigger.com/portswigger/desktop-linux:java-max"
            dockerPull = true
            dockerImagePlatform = ScriptBuildStep.ImagePlatform.Linux
        }
    }

    triggers {
        vcs {
            triggerRules = """
                    -:.teamcity/**
                    -:docs/**
                    -:comment=^\[Gradle Release Plugin\].*${'$'}:gradle.properties
                """.trimIndent()
            perCheckinTriggering = false
            enableQueueOptimization = false
            branchFilter = """
                    +:<default>
                """.trimIndent()
        }
    }

    failureConditions {
        executionTimeoutMin = 5
    }
})

object PublishToNexus : BuildType({
    uuid = "76a8fa7a-4a29-4657-9d9d-951fab9cfbd6"
    name = "Publish to Nexus"
    type = Type.DEPLOYMENT

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        gradle {
            tasks = "release -Prelease.useAutomaticVersion=true"
            dockerImage = "docker-internal.devtools.portswigger.com/portswigger/desktop-linux:java-max"
            dockerPull = true
            dockerImagePlatform = GradleBuildStep.ImagePlatform.Linux
        }
    }

    dependencies {
        dependency(CompileAndGenerateJavaDocs) {
        }
    }

    failureConditions {
        executionTimeoutMin = 5
    }
})