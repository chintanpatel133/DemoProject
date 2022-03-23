pipeline {
  agent any

  tools {
    maven 'Maven 3.0.5'
    jdk 'jdk8'
  }

  stages {
    stage("Tools initialization") {
      steps {
        sh "mvn --version"
        sh "java -version"
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
