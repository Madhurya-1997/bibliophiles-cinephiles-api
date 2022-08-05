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
                    bat 'mvn spring-boot:run -DhttpPort=7000'
//                     bat 'cd E:\webapps'
//                     bat 'del new'
//                     bat 'mkdir new'
//                     bat 'copy E:\Portfolio\bibliophiles cinephiles\bib-and-cine-api\bib-and-cine-api\target\bib-and-cine-api-0.0.1-SNAPSHOT.jar E:\webapps\new\bib-and-cine-api-0.0.1-SNAPSHOT.jar'
//                     bat 'cd E:\webapps\new'
//                     bat 'java -jar bib-and-cine-api-0.0.1-SNAPSHOT.jar'
                }

            }
        }
    }

}