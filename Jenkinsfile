pipeline {
  agent {
      label 'sbt-1'
  }
  stages {
      stage('Checkout') {
          steps {
            git 'https://github.com/tmtsoftware/csw-prod.git'
          }
      }
      stage('Build') {
          steps {
            sh "sbt 'scalafmt --test'"
            sh "sbt -Dcheck.cycles=true clean scalastyle compile"
          }
      }

      stage('Tests') {
        parallel {
            stage('Unit and Component Tests') { // Component tests cover the scenario of multiple components in single container
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
                    label 'sbt-2'
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