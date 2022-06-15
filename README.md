# Mediscreen

Mediscreen's main purpose is to detect risk factors for type 2 diabetes based on patients' data.

## Installation

### Prerequisites

- Java [11+](https://adoptopenjdk.net/?variant=openjdk8&jvmVariant=hotspot)
- Maven [3.6+](https://maven.apache.org/download.cgi)
- Docker 17.03.1-ce-rc1 +
- docker-compose version 1.11.2

### Run

Build the project

```bash
mvn clean install
```

Build docker images for each service. Make sure you are at the same level as the Dockerfile each time.

```bash
docker build -t mediscreen/patient .
```

```bash
docker build -t mediscreen/notes .
```

```bash
docker build -t mediscreen/assessment .
```

```bash
docker build -t mediscreen/mediscreen .
```

Finally, execute the docker-compose command

```bash
docker-compose up
```
