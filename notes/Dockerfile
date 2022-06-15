FROM openjdk:11-jdk
LABEL "maintainer"="omrahina"
COPY target/*.jar notes-0.0.1-SNAPSHOT.jar
EXPOSE 8082
ENTRYPOINT ["java","-jar","/notes-0.0.1-SNAPSHOT.jar"]