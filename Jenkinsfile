pipeline {
    agent any

    stages {
        stage ('Compile stage') {
            steps {
                withMaven(maven: 'MAVEN_HOME') {
                    sh 'mvn clean compile'
                }

            }
        }
        stage ('Deployment stage') {
            steps {
                withMaven(maven: 'MAVEN_HOME') {
                    sh 'mvn deploy'
                }

            }
        }
    }

}