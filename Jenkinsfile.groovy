pipeline {
    //agent none
    agent {
                node { label "Dev" }
                //docker { image 'node:7-alpine' }
    }
    stages {
        stage('Build') {
            /*agent {
                node { label "Dev" }
                //docker { image 'node:7-alpine' }
            }*/
            steps {
                //docker
                echo "docker in use?"
                sh """
                    docker build -t the-example-app.nodejs .
                    docker_container=`docker run -p 3000:3000 -d the-example-app.nodejs`
                    # npm run test:unit
                    echo \$docker_container
                    docker stop \$docker_container
                    npm pack
                    """

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
        }*/
        stage('Test') {
            agent {
                node {
                    label "Dev"
                }
            }
            steps {
                //
             script {
              try {
               echo "Testing..."
                sh """
                    docker_container=`docker run -p 3000:3000 -d the-example-app.nodejs`
                    npm run test:unit
                    echo \$docker_container
                    docker stop \$docker_container
                    npm pack
                    """
              }
              catch (all) {
                 currentBuild.result="UNSTABLE"
              }
            }
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



