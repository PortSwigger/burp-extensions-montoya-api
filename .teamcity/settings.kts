import jetbrains.buildServer.configs.kotlin.v2018_2.BuildType
import jetbrains.buildServer.configs.kotlin.v2018_2.DslContext
import jetbrains.buildServer.configs.kotlin.v2018_2.buildSteps.GradleBuildStep
import jetbrains.buildServer.configs.kotlin.v2018_2.buildSteps.ScriptBuildStep
import jetbrains.buildServer.configs.kotlin.v2018_2.buildSteps.gradle
import jetbrains.buildServer.configs.kotlin.v2018_2.buildSteps.script
import jetbrains.buildServer.configs.kotlin.v2018_2.project
import jetbrains.buildServer.configs.kotlin.v2018_2.version

version = "2020.1"

project {
    buildType(GenerateArtifactsAndPublishToNexus)
}

object GenerateArtifactsAndPublishToNexus : BuildType({
    uuid = "603cefbe-4e79-4e9d-a12f-91923d088ca3"
    name = "Generate artifacts and publish to Nexus"
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
                git pull --rebase
                git push
            """
            dockerImage = "docker-internal.devtools.portswigger.com/portswigger/desktop-linux:java-max"
            dockerPull = true
            dockerImagePlatform = ScriptBuildStep.ImagePlatform.Linux
        }

        gradle {
            tasks = "release -Prelease.useAutomaticVersion=true"
            dockerImage = "docker-internal.devtools.portswigger.com/portswigger/desktop-linux:java-max"
            dockerPull = true
            dockerImagePlatform = GradleBuildStep.ImagePlatform.Linux
        }
    }

    failureConditions {
        executionTimeoutMin = 5
    }
})