FROM maven:3.6.3-openjdk-11-slim AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src /app/src
RUN mvn package

# Stage 2: Run the application
FROM openjdk:11-jre-slim
WORKDIR /app
COPY --from=build /app/target/air-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]