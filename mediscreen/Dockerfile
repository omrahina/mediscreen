FROM openjdk:11-jdk
LABEL "maintainer"="omrahina"
COPY target/*.jar mediscreen-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/mediscreen-0.0.1-SNAPSHOT.jar"]