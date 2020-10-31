# Purpose
This repository contains a series of alternate implementations of the same 
problem : managing users, user groups and their respective privileges.

The areas all implementations must cover are :

* Network API
    - REST serving Hypermedia
    - REST serving JSON
    - REST serving XML

* Data access
    - JPA
    - Hibernate
    - MyBatis
    - JDBC Template
    - Raw JDBC

* Unit testing
    - JUnit 5
    - PITest

* Acceptance testing
    - Newman

* Functional testing
    - Cucumber

* Monitoring
    - Actuator

* Database
    - H2
    - Informix

* Build chain
    - Maven

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
<description>Spring skeleton for a RESTful service</description>
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
