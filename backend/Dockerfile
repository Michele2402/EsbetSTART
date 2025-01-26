FROM eclipse-temurin:22-jdk-jammy

RUN apt update && apt install -y

COPY target/esbetstart-0.0.1-SNAPSHOT.jar /starter/esbetstart-0.0.1-SNAPSHOT.jar
COPY src/main/resources/application-production.properties /starter/config/application-production.properties

WORKDIR /starter
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/starter/esbetstart-0.0.1-SNAPSHOT.jar", "--spring.config.location=/starter/config/application-production.properties"]
