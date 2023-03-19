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
        stage ('Build stage') {
            steps {
                withMaven(maven: 'MAVEN_HOME') {
                    bat 'mvn clean install -DskipTests'
                }

            }
        }
        stage ('Containerization stage') {
                    steps {
                        bat 'docker build -t 883630/bibandcinedb:latest .'
                    }
                }
    }

}