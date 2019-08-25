pipeline {
    environment {
        registry = "jtutzo/katalaw"
    }
    agent none
    stages {
        stage('Build app') {
            agent {
                docker {
                    image 'maven:3-alpine'
                 }
            }
            steps {
                sh 'mvn clean install'
            }
        }
        stage('Building image') {
            agent any
            steps {
                script {
                    docker.build registry + ":dev"
                }
            }
        }
    }
}
