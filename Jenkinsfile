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
//         stage ('Running stage') {
//             steps {
//                 withMaven(maven: 'MAVEN_HOME') {
//                     sh 'mvn spring-boot:run -DskipTests'
//                 }
//
//             }
//         }
    }

}