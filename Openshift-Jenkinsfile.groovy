pipeline {
    agent none
    stages {
        stage('Checkout') {
            agent {
                label 'sbt'
            }
            steps {
                git 'https://github.com/tmtsoftware/csw-prod.git'
            }
        }
        stage('Build') {
            agent {
                label 'sbt'
            }
            steps {
                sh "sbt cleancompile"
            }
        }
        stage('Tests') {
            failFast true
            parallel {
                stage('Unit and Component Tests') {
                    agent {
                        label 'sbt'
                    }
                    steps {
                        sh "sbt -DenableCoverage=true test:test"
                    }
                    post {
                        always {
                            sh "sbt -DenableCoverage=true coverageReport"
                            sh "sbt coverageAggregate"
                            junit '**/target/test-reports/*.xml'
                            publishHTML(target: [
                                    allowMissing         : true,
                                    alwaysLinkToLastBuild: false,
                                    keepAll              : true,
                                    reportDir            : './target/scala-2.12/scoverage-report',
                                    reportFiles          : 'index.html',
                                    reportName           : "Scoverage Report"
                            ])
                        }
                    }
                }
                stage('Multi-Jvm Test') { // These tests cover the scenario of multiple components in multiple containers on same machine.
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
                // stage('Multi-Node Test') { // These tests cover the scenario of multiple components in multiple containers on different machines.
                //     steps {
                //         sh "sbt -DenableCoverage=false csw-location/multiNodeTest"
                //         sh "sbt -DenableCoverage=false csw-config-client/multiNodeTest"
                //         sh "sbt -DenableCoverage=false csw-framework/multiNodeTest"
                //     }
                // }
            }
        }
    }
}   