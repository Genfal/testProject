FROM openjdk:11

WORKDIR /app

COPY ./target/project-1.0-SNAPSHOT.jar .

ENTRYPOINT ["java", "-jar", "project-1.0-SNAPSHOT.jar"]

EXPOSE 8080
