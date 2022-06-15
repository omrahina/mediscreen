FROM openjdk:11-jdk
LABEL "maintainer"="omrahina"
COPY target/*.jar assessment-0.0.1-SNAPSHOT.jar
EXPOSE 8083
ENTRYPOINT ["java","-jar","/assessment-0.0.1-SNAPSHOT.jar"]