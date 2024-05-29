pipeline {
    agent any

    environment {
        EC2_INSTANCE_IP = '13.127.0.213'  // Your EC2 instance IP address
        SSH_CREDENTIALS_ID = '4ae40fa5-e867-4f0c-baa2-24c291672b34'  // Your SSH credentials ID in Jenkins
        DOCKER_IMAGE_NAME = 'todo-manager'  // Name of your Docker image
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/IAmTheInfinity24/ToDo-Manager.git'  // Your GitHub repository URL
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean install'  // Your Maven build command
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    def app = docker.build("${env.DOCKER_IMAGE_NAME}:${env.BUILD_ID}")
                }
            }
        }

        stage('Deploy to EC2') {
            steps {
                sshagent (credentials: ['${env.SSH_CREDENTIALS_ID}']) {
                    sh """
                        scp -o StrictHostKeyChecking=no Dockerfile MyVM_1@${env.EC2_INSTANCE_IP}:~/Dockerfile
                        scp -o StrictHostKeyChecking=no target/*.jar MyVM_1@${env.EC2_INSTANCE_IP}:~/app.jar
                        ssh -o StrictHostKeyChecking=no MyVM_1@${env.EC2_INSTANCE_IP} << EOF
                        docker build -t ${env.DOCKER_IMAGE_NAME}:latest -f ~/Dockerfile .
                        docker stop ${env.DOCKER_IMAGE_NAME} || true
                        docker rm ${env.DOCKER_IMAGE_NAME} || true
                        docker run -d -p 8080:8080 --name ${env.DOCKER_IMAGE_NAME} ${env.DOCKER_IMAGE_NAME}:latest
                        EOF
                    """
                }
            }
        }
    }

    post {
        success {
            echo 'Pipeline executed successfully!'
        }
        failure {
            echo 'Pipeline failed.'
        }
    }
}
