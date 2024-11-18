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
                sh "mvn surefire:test"
            }
        }

        stage('Package'){
            steps {
                sh 'mvn package'
            }
        }

        stage('Archive'){
            steps {
                archiveArtifacts allowEmptyArchive: true,
                    artifacts:'**/conors_petitions*.war'
            }
        }

        stage('Deploy'){
            steps {
                sh 'docker build -f Dockerfile -t myapp . '
                sh 'docker rm -f "myappcontainer" || true'
                sh 'docker run --name "myappcontainer" -p 8081:8080 --detach myapp:latest'
            }
        }
    }
}