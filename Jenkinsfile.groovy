pipeline {
    agent {
        label 'sbt'
    }
    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/tmtsoftware/csw-prod.git'
            }
        }
        stage('Build') {
            steps {
                sh "sbt clean compile"
            }
        }

        stage('Tests') {
            parallel {
                stage('Unit Tests') {
                    steps {
                        sh "sbt test:test"
                    }
                }
                stage('Multi-Jvm') {
                    agent {
                        label 'sbt'   
                    }
                    steps {
                        sh "sbt csw-location/multi-jvm:test"
                        sh "sbt csw-config-client/multi-jvm:test"
                        sh "sbt csw-config-client-cli/multi-jvm:test"
                        sh "sbt csw-framework/multi-jvm:test"
                    }
                }
            }
        }
    }
}
                