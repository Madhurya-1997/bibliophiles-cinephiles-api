FROM maven:3.8.2-jdk-11 as build
WORKDIR /bib-and-cine-api
COPY . .
RUN mvn clean install
CMD mvn spring-boot:run