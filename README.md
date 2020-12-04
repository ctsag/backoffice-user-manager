#Purpose
This repository contains a series of alternate implementations of the same 
problem : managing users, user groups and their respective privileges.

The areas all implementations must cover are :

* Database
    - PostgreSQL
        - data-jpa
        - hibernate
    - MongoDB
        - data-mongo
    - MySQL
        - jdbc-templtae
        - jdbc-raw
    - Informix
        - data-rest
        - mybatis
    - Redis
        - data-redis

* Data access
    - Data REST
        - data-rest
    - Data JPA
        - data-jpa
    - Data Mongo
        - data-mongo
    - Hibernate
        - hibernate
    - MyBatis
        - mybatis
    - JDBC Template
        - jdbc-template
    - Raw JDBC
        - jdbc-raw
    - Redis
        - data-redis

* Network API
    - REST serving Hypermedia (automatic)
        - data-rest
    - REST serving Hypermedia (manual)
        - data-jpa
    - REST serving JSON
        - data-redis
        - hibernate
        - mybatis
        - jdbc-template
        - jdbc-raw
    - REST serving XML
        - data-mongo

* API documentation
    - Spring REST Docs/Asciidoctor
        - data-jpa
    - Springdoc
        - data-redis
        - data-rest
        - hibernate
        - mybatis
        - jdbc-template
        - jdbc-raw
    - Springfox/Swagger
        - data-mongo
* Security
    - Spring Security
        - *

* Developer side testing
    - Unit test framework
        - JUnit 5
            - *
    - Mutation testing
        - PIT
            - *
    - Assertions library
        - JUnit 5 + Hamcrest
            - data-redis
            - data-rest
            - hibernate
            - mybatis
            - jdbc-template
            - jdbc-raw
        - AssertJ
            - data-jpa
    - Coverage reports
        - JaCoCo
            - *
    - Static code analysis
        - ?
    - Build acceptance testing
        - Newman
            - *
    - Integration testing
        - Spring Boot
            - data-jpa
        - REST Assured
            - data-redis
            - data-rest
            - hibernate
            - mybatis
            - jdbc-template
            - jdbc-raw

* QA side testing
    - BDD automation
        - Cucumber
            - *
        - FitNesse
            - *
    - Build acceptance testing
        - Postman
            - *

* Database migrations
    - Liquibase
        - data-rest
        - hibernate
        - mybatis
    - Flyway
        - data-redis
        - data-jpa
        - jdbc-template
        - jdbc-raw

* Monitoring
    - Actuator
        - data-redis
        - data-rest
        - data-jpa
        - mybatis
        - jdbc-template
        - jdbc-raw
    - Micrometer
        - hibernate

* Build tool
    - Maven
        - *

# Common practices

All implementations will comply to the following ruless :

* The following frameworks will be used
    - Spring Boot
    - Java 8
    - Maven
    - IntelliJ IDEA

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

# List of implementations

This is the list of those implementations and a brief description of how
each implementation differs from all others :
