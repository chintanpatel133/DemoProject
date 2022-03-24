pipeline {
  agent any

  tools {
    maven 'Maven 3.6.3'
    jdk 'jdk8'
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
        withSonarQubeEnv(credentialsId: 'sonar_token') {
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
  }
}
