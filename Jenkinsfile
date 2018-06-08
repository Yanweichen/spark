pipeline {
    agent any

    environment {
        name = "spark_demo"
    }

    tools {
        gradle 'gradle-4.8'
    }

    stages {
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
                sh 'cp build/libs/*.jar /app/${name}.jar'
                sh 'cp Dockerfile /app/Dockerfile'
                sh 'sh ShellFile.sh ${name} "build/libs" "8080" "8099"'
                sh 'rm -rf /app/${name}.jar'
                sh 'rm -rf /app/Dockerfile'
                sh 'echo "自动部署结束"'
            }
        }
    }
}