FROM openjdk:11-jdk
LABEL "maintainer"="omrahina"
COPY target/*.jar patient-0.0.1-SNAPSHOT.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","/patient-0.0.1-SNAPSHOT.jar"]