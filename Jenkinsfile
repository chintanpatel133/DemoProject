pipeline {
  agent any

  tools {
    maven 'Maven 3.8.5'
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
  }
}
