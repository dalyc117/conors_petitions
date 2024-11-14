pipeline {
    agent any

    tools {
        git 'Default'
    }

    stages {
        stage('GetProject') {
            steps {
                git branch: 'main', url: 'https://github.com/dalyc117/conors_petitions.git'
            }
        }
        stage('Build') {
            steps {
                sh "mvn clean:clean"

                sh "mvn dependency:copy-dependencies"

                sh "mvn compiler:compile"
            }
        }

        stage('Test') {
            steps {
                sh "mvn test:test"
            }
        }

        stage('Package & Archive'){
            steps {
                echo 'Package'
            }
        }

        stage('Deploy'){
            steps {
                echo 'Deploy'
            }
        }
    }
}