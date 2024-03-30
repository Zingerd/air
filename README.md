# SETUP PROJECT
      Java 11
      Spring Boot 2.2
      MySQL
      Rest API
      Docker
      GIT
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

# Table of describe requests

| Request                                       |              Request Type              | Description                                                                                                                                                                                          |
|-----------------------------------------------|:--------------------------------------:|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| /api/air/companies/{id}                       |  <span style="color:green">GET</span>  | Get information about a specific Air Company (CRUD)                                                                                                                                                  |
| /api/air/companies                            |  <span style="color:green">GET</span>  | Get information about all Air Companies (CRUD)                                                                                                                                                       |
| /api/air/companies                            | <span style="color:orange">POST</span> | Create new Air Company(CRUD)                                                                                                                                                                         |
| /api/air/companies/{id}                       |  <span style="color:gray">PUT</span>   | Update information about Air Companies (CRUD)                                                                                                                                                        |
| /api/air/companies/{id}                       | <span style="color:red">DELETE</span>  | Delete Air Companies (CRUD)                                                                                                                                                                          |
| /api/airplanes/move/                          | <span style="color:orange">POST</span> | Endpoint to move airplanes between companies                                                                                                                                                         |
| /api/companies/{companyName}/flights/{status} |  <span style="color:green">GET</span>  | Endpoint to find all Air Company Flights by status                                                                                                                                                   |
| /api/flights/active                           |  <span style="color:green">GET</span>  | Endpoint to find all Flights in ACTIVE status and started more than 24 hours ago;                                                                                                                    |
| /api/airplanes/add                            | <span style="color:orange">POST</span> | Endpoint to add new Airplane                                                                                                                                                                         |
| /api/flights/add                              | <span style="color:orange">POST</span> | Endpoint to add new Flight (set status to PENDING)                                                                                                                                                   |
| /api/flights/change-status                    | <span style="color:orange">POST</span> | Endpoint to change Flight status:<br/> if status to change is DELAYED – set delay started at<br/>if status to change is ACTIVE – set started at<br/> if status to change is COMPLETED – set ended at |
| /api/flights/change-status                    |  <span style="color:green">GET</span>  | Endpoint to find all Flights in COMPLETED status and difference between started and ended time is bigger than the estimated flight time                                                              |

# SETUP SQL
   #### *When running docker-compose, it automatically creates a DB and all necessary tables with pre-filled test data located in* 
    /data/initTable.sql folder
```
docker exec -it air-mysql-1 mysql -uroot -p
Enter password:123456
mysql> SHOW DATABASES;
+--------------------+
| Database           |
+--------------------+
| air_companies      |
| information_schema |
| mysql              |
| performance_schema |
| sys                |
+--------------------+
```
```
mysql> use air_companies;
mysql> SHOW TABLES;
+-------------------------+
| Tables_in_air_companies |
+-------------------------+
| air_company             |
| airplane                |
| flight                  |
+-------------------------+
```

