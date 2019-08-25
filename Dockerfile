FROM openjdk:8

WORKDIR "/katalaw"

EXPOSE 8080

COPY target/katalaw-0.0.1-SNAPSHOT.jar ./

CMD ["java", "-jar", "katalaw-0.0.1-SNAPSHOT.jar"]