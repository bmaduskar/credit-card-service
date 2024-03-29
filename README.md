# Credit Card Service Application

A spring boot application which exposes 2 RestEndpoints to execute credit cart related maintenance i.e Add new cards and list all cards from a system.

  - Spring Boot 2.0.3.Release
  - H2 Database Runtime (Memory)
  - Spring Boot JDBC
  - SpringFox Swagger
  - SpringFox UI
  - Junits
  - SureFire Reports
  - Maven 4.0


### Installation

This application is built using maven and all dependencies will be added automatically.

```sh
$ cd credit-card-service
$ mvn clean
$ mvn clean spring-boot:run
```

For Running Tests and Generating the Test Report:

```sh
$ mvn test
$ mvn surefire-report:report-only
$ mvn site -DgenerateReports=false
```
### SpringFox Swagger

Swagger UI will be available on the following link:

```sh
localhost:8080/ccp/v1/swagger-ui.html
localhost:8080/ccp/v1/v2/api-docs
```
