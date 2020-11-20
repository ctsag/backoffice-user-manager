# Purpose
This repository contains a series of alternate implementations of the same 
problem : managing users, user groups and their respective privileges.

The areas all implementations must cover are :

* Database
    - H2
        - data-jpa
    - MySQL
    - Informix
        - data-rest

* Data access
    - Data REST
        - data-rest
    - Data JPA
        - data-jpa
    - Hibernate
    - MyBatis
    - JDBC Template
    - Raw JDBC

* Network API
    - REST serving Hypermedia (automatic)
        - data-rest
    - REST serving Hypermedia (manual)
        - data-jpa
    - REST serving JSON
    - REST serving XML

* Developer side testing
    - Unit test framework
        - JUnit 5
            - data-rest
            - data-jpa
    - Mutation testing
        - PIT
            - data-rest
            - data-jpa
    - Assertions library
        - JUnit 5 + Hamcrest
            - data-jpa
        - AssertJ
            - data-rest
    - Coverage reports
        - JaCoCo
            - data-jpa
            - data-rest
    - Static code analysis
        - ?
    - Build acceptance testing
        - Newman
            - data-jpa
            - data-rest
    - Integration testing
        - Spring Boot
            - data-jpa
        - REST Assured
            - data-rest

* QA side testing
    - BDD automation
        - Cucumber
            - data-jpa
            - data-rest
        - FitNesse
            - data-jpa
            - data-rest
    - Build acceptance testing
        - Postman
            - data-jpa
            - data-rest

* Database migrations
    - Liquibase
        - data-jpa
    - Flyway
        - data-rest

* Monitoring
    - Actuator
        - data-jpa
        - data-rest

* Build tool
    - Maven
        - data-jpa
        - data-rest

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
