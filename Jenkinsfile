pipeline {
    agent any

    environment {
        name = "spark_demo"
        project_port = "8080"
        docker_port = "8099"
    }

    tools {
        gradle 'gradle-4.8'
    }

    stages {
        stage('Checkout') {
            checkout([$class: 'GitSCM', branches: [[name: '*/master']]
                      , doGenerateSubmoduleConfigurations: false
                      , extensions: []
                      , submoduleCfg: []
                      , userRemoteConfigs: [[credentialsId: '4f6ac063-bd6b-4cc7-9a60-6bfc822259af'
                                             , url: 'https://github.com/Yanweichen/spark.git']]])
        }
        stage('Build') {
            steps {
                sh 'echo "自动构建开始"'
                sh 'gradle clean build'
                sh 'echo "自动构建结束"'
            }
        }
        stage('Test') {
            steps {
                sh 'echo "自动测试开始"'
                sh 'gradle test'
                sh 'echo "自动测试结束"'
            }
        }
        stage('Deliver') {
            steps {
                sh 'echo "自动部署开始"'
                sh 'rm -rf ${name}.jar'
                sh 'cp build/libs/*.jar ${name}.jar'
                sh 'sh ShellFile.sh ${name} ${project_port} ${docker_port}'
                sh 'echo "自动部署结束"'
            }
        }
    }
}