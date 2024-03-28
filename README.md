# SETUP PROJECT
#### 1. Download the project
 * git clone https://github.com/Zingerd/air.git
#### 2. Find in project file air/docker-compose.yml  and use this command
 * docker-compose up
#### *After this the project will start working*
#### Samples : *docker-compose*
```
version: '3.8'

services:
  mysql:
    image: mysql:latest
    ports:
      - "3306:3306"
    environment:
      MYSQL_ROOT_PASSWORD: 123456
      MYSQL_DATABASE: air_companies
    volumes:
      - ./data/initTable.sql:/docker-entrypoint-initdb.d/initTable.sql

  app:
    build: .
    ports:
      - "8080:8080"
    depends_on:
      - mysql
    environment:
      SPRING_DATASOURCE_URL: jdbc:mysql://mysql:3306/air_companies
      SPRING_DATASOURCE_USERNAME: root
      SPRING_DATASOURCE_PASSWORD: 123456
    restart: "on-failure"
```
#### Samples : *Dockerfile*
```
FROM maven:3.6.3-openjdk-11-slim AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src /app/src
RUN mvn package


FROM openjdk:11-jre-slim
WORKDIR /app
COPY --from=build /app/target/air-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
```
# SETUP POSTMAN
1. #### FIND file in setup/Air Companies.postman_collection*
2. #### Open Postman, and import this collection to it  
   ![image](https://github.com/Zingerd/air/assets/50172465/b70fe17e-0d47-4bd3-871d-b97ceb120b89)
#### After that you have requests
# SETUP SQL
   #### *When running docker-compose, it automatically creates a DB and all necessary tables with pre-filled test data located in* 
    /data/initTable.sql folder

