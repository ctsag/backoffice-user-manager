# Purpose
This repository contains a series of alternate implementations of the same
problem : managing users, user groups and their respective privileges.

The areas all implementations must cover are :

|                              | data-rest                   | data-jpa                 | jdbc-template    | jdbc-raw          | mybatis           | hibernate      | data-mongo     | data-redis     |
|------------------------------|-----------------------------|--------------------------|----------------- |-------------------|-------------------|----------------|----------------|----------------|
| **Database**                 | Informix                    | PostgreSQL               | MySQL            | H2                | Informix          | PostgreSQL     | MongoDB        | Redis          |
| **Data access**              | Data REST                   | Data JPA                 | JDBC Template    | Raw JDBC          | MyBatis           | Hibernate      | Data Mongo     | Data Redis     |
| **API**                      | REST/Hypermedia (automatic) | REST/Hypermedia (manual) | REST/JSON        | REST/XML          | GraphQL           | ???            | ???            | ???            |
| **API documentation**        | Springfox/Swagger           | Springdoc                | Spring REST Docs | ???               | ???               | ???            | ???            | ???            |
| **Authentication**           | Spring Security             | Spring Security          | Apache Shiro     | ???               | ???               | ???            | ???            | ???            |
| **Unit testing**             | JUnit 5                     | JUnit 5                  | TestNG           | ???               | ???               | ???            | ???            | ???            |
| **Mutation testing**         | PIT                         | PIT                      | PIT              | PIT               | PIT               | PIT            | PIT            | PIT            |
| **Assertions library**       | JUnit/Hamcrest              | AssertJ                  | ???              | ???               | ???               | ???            | ???            | ???            |
| **Coverage reports**         | JaCoCo                      | Cobertura                | OpenClover       | ???               | ???               | ???            | ???            | ???            |
| **Static code analysis**     | SonarQube                   | Checkstyle               | PMD              | FindBugs          | ???               | ???            | ???            | ???            |
| **Vulnerability checks**     | OWASP                       | OWASP                    | OWASP            | OWASP             | OWASP             | OWASP          | OWASP          | OWASP          |
| **Build acceptance testing** | Postman/Newman              | Postman/Newman           | Postman/Newman   | Postman/Newman    | Postman/Newman    | Postman/Newman | Postman/Newman | Postman/Newman |
| **Integration testing**      | REST Assured                | Spring Test              | ???              | ???               | ???               | ???            | ???            | ???            |
| **BDD automation**           | Cucmber                     | FitNesse                 | ???              | ???               | ???               | ???            | ???            | ???            |
| **Database migrations**      | Liquibase (manual)          | Liquibase (automatic)    | Flyway           | Liquibase (Maven) | ???               | ???            | ???            | ???            |
| **Monitoring**               | Actuator                    | Micrometer               | ???              | ???               | ???               | ???            | ???            | ???            |
| **Build tool**               | Maven                       | Maven                    | Gradle           | ???               | ???               | ???            | ???            | ???            |
| **Build automation**         | GitHub Actions              | GitHub Actions           | Jenkins          | Travis CI         | Circle CI         | GoCD           | ???            | ???            |
| **Packaging**                | JAR executable              | JAR executable           | Docker           | Heroku            | Spring Native     | Heroku         | Tomcat WAR     | ???            |
| **IDE**                      | IntelliJ IDEA               | IntelliJ IDEA            | STS              | Eclipse           | NetBeans          | ???            | ???            | ???            |

# Candidates

Other technologies we want to incorporate :

* JMeter
* JMX
* ActiveMQ
* RabbitMQ
* Micronaut
* Java 14

# Common practices

* The project properties present in the pom.xml file will follow this pattern :

```xml
<groupId>gr.nothingness.backoffice-user-manager</groupId>
<artifactId>data-jpa</artifactId>
<version>0.0.1-SNAPSHOT</version>
<name>Data JPA</name>
<description>Data JPA implementation</description>
```

* Lombok will always be a dependency and will be used for the following :
    - Getters and setters that do not require custom code
    - Any constructor that does not require custom code
    - Setting up loggers
    - toString()

* A README.md file will exist for all implementations describing the areas involved

* Prefer @Component instead of @Bean

* No comments unless absolutely necessary

* No javadoc unless we're talking about a public library or API

* All IntelliJ IDEA warnings must be resolved or justifiably suppressed

* Prefer simple lamdas instead of loops, and prefer method references instead of lamda expressions
