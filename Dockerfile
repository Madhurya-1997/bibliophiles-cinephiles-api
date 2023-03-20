FROM openjdk:17
LABEL maintainer="madhurya"
ADD ./target/bib-and-cine-api-0.0.1-SNAPSHOT.jar bib-and-cine-api-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/bib-and-cine-api-0.0.1-SNAPSHOT.jar"]
