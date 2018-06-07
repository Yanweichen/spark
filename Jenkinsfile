pipeline {
    agent any

    environment {
        name = "spark_demo"
    }

    tools{
        gradle 'gradle'
    }

    stages {
        stage('Build') {
            steps {
                sh 'echo "自动构建开始"'
                sh 'gradle clean --refresh-dependencies build'
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
                sh 'mv build/libs/*.jar /app/${name}.jar'
                sh 'java -jar /app/${name}.jar'
                sh 'echo "自动部署结束"'
            }
        }
    }
}