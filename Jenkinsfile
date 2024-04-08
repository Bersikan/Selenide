pipeline {
    agent any

    stages {
        stage('Test') {
            steps {
                bat "gradle clean test"
                bat "git submodule update --init --recursive"
            }

            post {
                // If Gradle was able to run the tests, even if some of the test
                // failed, record the test results and archive the jar file.
                success {
                   publishHTML([
                      allowMissing: false,
                      alwaysLinkToLastBuild: false,
                      keepAll: false,
                      reportDir: 'build/reports/tests/test/',
                      reportFiles: 'index.html',
                      reportName: 'Report',
                      reportTitles: '',
                      useWrapperFileDirectly: true])
                }
            }
        }
    }
}