backendVersion = '0.0.0'

pipeline {

    agent any

    environment {
        // internal parameters (docker)
        EPHEMERAL_HOST = "${params.EPHEMERAL_HOST}"
        CONTAINER_BACKEND_PATH = "${params.CONTAINER_BACKEND_PATH}"
        API_EPHEMERAL_URL = "http://${EPHEMERAL_HOST}"
        // external test parameters
        API_URL_NEWMAN = "${API_EPHEMERAL_URL}"
    }

    stages {
        stage('Internal testing') {
            agent {
                docker { image 'maven:3.6.3-jdk-11-slim' }
            }
            steps {
                sh 'mvn --version'
                sh 'echo "Iniciando pruebas"'
                sh 'cd /tmp && ls'
                sh 'echo "Obteniendo dependencias"'
                sh 'mvn -B -e -C -T 1C org.apache.maven.plugins:maven-dependency-plugin:3.0.2:go-offline'
                sh 'echo "Ejecutar pruebas unitarias"'
                sh 'mvn test'
            }
        }

        stage('Prepare backend version') {
            agent {
                docker { image 'maven:3.6.3-jdk-11-slim' }
            }
            steps {
                echo "Getting backend version with maven"
                echo "Before ${backendVersion}"
                sh 'mvn help:evaluate -Dexpression=project.version -q -DforceStdout > backend.txt'
                script {
                    backendVersion = "${CONTAINER_BACKEND_PATH}:" + readFile('backend.txt').trim() + "-" + env.BUILD_NUMBER
                }
                echo "After ${backendVersion}"
            }
        }

        stage('Setup compose environmet') {
            steps {
                echo "Building backend image ${backendVersion}"
                sh "docker build -t ${backendVersion} ."
                echo "Generate docker-compose file"
                sh "sed -i 's@{{BACKEND_DOCKER_IMAGE}}@${backendVersion}@g' docker-compose.dist"
                sh 'cat docker-compose.dist'
                sh "docker-compose -f docker-compose.dist up -d"
                sh "sleep 5"
                sh "docker-compose -f docker-compose.dist ps"
            }
        }

        stage("External testing Newman") {
            agent {
               docker { 
                    image 'postman/newman:alpine' 
                    args '--entrypoint="" --network host'
                }
            }
            steps {
                echo "Testing Newman"
                dir("${env.WORKSPACE}/e2e/newman") {
                    sh "newman run api-calculadora.postman_collection.json -e CLOUD.postman_environment.json --env-var 'url=${API_EPHEMERAL_URL}'"
                }
            }
        }
    }

    post {
        always {
            echo "Down ephemeral environment...."
            sh "docker-compose -f docker-compose.dist down"
        }

        success {
            echo "success"
        }

        unstable {
            echo "unstable"
        }

        failure {
            echo "failure"
        }

        changed {
            echo "changed"
        }
    }
}