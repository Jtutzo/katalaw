FROM maven:3.5.4-jdk-8

WORKDIR "/katalaw"

EXPOSE 8080

COPY target/katalaw-0.0.1-SNAPSHOT.jar ./

VOLUME ["/katalaw"]
