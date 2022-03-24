pipeline {
  agent any

  tools {
    maven 'Maven 3.6.3'
    jdk 'jdk8'
  }

  environment {
    DOCKER_REGISTRY = 'mydevopstechlabs.jfrog.io'
    DOCKER_REPO = 'mydevopstechlabs-backend-release-local'
    DOCKER_IMAGE_NAME = 'Dockerfile'
    DOCKER_FILE_PATH = '.'
  }

  stages {
    stage("Tools initialization") {
      steps {
        sh "java --version"
        sh "mvn -version"
      }
    }
    stage("Checkout Code") {
      steps {
        git branch: 'master',
          url: 'https://github.com/chintanpatel133/DemoProject.git'
      }
    }
    stage("Packing Application") {
      steps {
        sh "mvn clean package"
      }
    }
    stage("SonarQube Analysis") {
      steps {
        withSonarQubeEnv('sonar') {
          sh "mvn sonar:sonar"
        }
      }
    }
    stage('Quality Gate') {
      steps {
        script {
          def qualitygate = waitForQualityGate()
          if (qualitygate.status != "OK") {
            echo "Pipeline aborted due to quality gate coverage failure: ${qualitygate.status}"
          } else {
            echo "SonarQube analysis succesfull"
          }
        }
      }
    }
    stage('Build & Push the Docker Image') {
      steps {
        script {
          docker.build("${DOCKER_REGISTRY}/$DOCKER_REPO/com.mydevopstechlabs.hms/demo:$BUILD_NUMBER", "-f ${DOCKER_IMAGE_NAME} ${DOCKER_FILE_PATH}")
        }
      }
    }
    stage('Push the docker image to Artifactory') {
      steps {
        script {
          def server = Artifactory.server 'artifactory-mydevopstechlabs'
          def rtDocker = Artifactory.docker server: server
          rtDocker.addProperty("Jenkins-build", "${BUILD_URL}".toLowerCase()).addProperty("Git-Url", "${GIT_URL}".toLowerCase())
          def buildInfo = rtDocker.push "${DOCKER_REGISTRY}/${DOCKER_REPO}/com.mydevopstechlabs.hms/demo:${BUILD_NUMBER}", "${DOCKER_REPO}"
          server.publishBuildInfo buildInfo
        }
      }
    }
  }
}
