pipeline {
    agent any

    stages {
        stage ('Compile stage') {
            steps {
                withMaven(maven: 'MAVEN_HOME') {
                    bat 'mvn clean compile'
                }

            }
        }
        stage ('Running server stage') {
            steps {
                withMaven(maven: 'MAVEN_HOME') {
                    sh 'mvn spring-boot:run -DskipTests'
                }

            }
        }
    }

}