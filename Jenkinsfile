pipeline {
    environment {
        registry = "jtutzo/katalaw"
        registryCredential = 'dockerhub'
        dockerImage = ''
    }
    agent none
    stages {
        stage('Properties') {
            agent any
            steps{
                script {
                    sh 'echo $BRANCH_NAME'
                }
            }
        }
        stage('Build app') {
            agent {
                docker {
                    image 'maven:3-alpine'
                    args '-u root -v /root/jenkins/.m2:/root/.m2'
                 }
            }
            steps {
                sh 'mvn clean install'
            }
        }
        stage('Building image') {
            agent any
            steps{
                script {
                    dockerImage = docker.build registry + ":prd"
                }
            }
        }
        stage('Deploy image') {
            agent any
            steps{
                script {
                    docker.withRegistry( '', registryCredential ) {
                        dockerImage.push()
                    }
                }
            }
        }
        stage('Deploy containers') {
            agent any
            steps{
                sh 'whoami'
                sh "kubectl apply -k ./"
            }
        }
    }
}
