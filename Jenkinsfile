pipeline {
    agent any

    environment {
        APP_PREFIX = "47.100.115.11:18881"
        APP_NAME = 'courage'
        APP_VERSION  = '1.0.0-SNAPSHOT'
    }
    stages {
         stage('Build by Gradle') {
            steps {
                 script {
                     sh 'pwd'
                     sh 'ls -al'
                     sh 'ls -al /root/.gradle/'
                     sh '/home/muyi/soft/gradle-6.4.1/bin/gradle clean '
                     sh '/home/muyi/soft/gradle-6.4.1/bin/gradle bootJar  '
                 }
             }
         }
         stage('Run') {
             steps {
                 script {
                     result = sh (script: "docker ps -aq --filter name=${APP_NAME}", returnStdout: true).trim()
                     if(result != ""){
                         sh "docker stop `docker ps -aq --filter name=${APP_NAME}` "
                         sh "docker rm   `docker ps -aq --filter name=${APP_NAME}` "
                     }
                     sh 'docker run -d --name ${APP_NAME} -p 18881:18881 -v /etc/localtime:/etc/localtime ${APP_PREFIX}/${APP_NAME}:${APP_VERSION}'
                 }
             }
         }
    }
}
