pipeline {
    agent none
    stages {
        stage('use docker') {
            agent {
                node { label "master" }
                //docker { image 'node:7-alpine' }
            }
            steps {
                //docker
                echo "docker in use?"
                sh "docker build -t the-example-app.nodejs ."
                sh "docker run -p 3000:3000 -d the-example-app.nodejs"

            }
        }
        
        /*
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
               sh "npm pack"
               echo "upload to artifactory"
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
        }*/
    }
}


