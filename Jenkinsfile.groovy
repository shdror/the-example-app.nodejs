pipeline {
    agent none
    stages {
        stage('Build') {
            agent {
                node {
                    label "master"
                }
            }
            steps {
                //
               echo "Building..."
               sh "npm install"
            }
        }
        stage('Test') {
            agent {
                node {
                    label "Dev"
                }
            }
            steps {
                //
               echo "Testing..."
               sh "npm run start:dev &"
               sh "sleep 60 && npm test"

            }
        }
        stage('Deploy') {
            agent {
                node {
                    label "Dev"
                }
            }
            steps {
                //
               echo "Deploying..."
            }
        }
    }
}

