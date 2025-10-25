# Spring Boot + SQL Server example: CRUD Operations Rest API

## Project Information
- Name: `spring-boot-sql-server`
- Group ID: `com.adphilip`
- Artifact ID: `spring-boot-sql-server`
- Version: `0.0.1-SNAPSHOT`
- Description: Spring Boot + SQL Server (MSSQL) example with JPA
- Java Version: 21

## Project Structure
```
src/
├── main/
│   ├── java/
│   │   └── com/adphilip/spring/mssql/
│   │       ├── SpringBootSqlServerApplication.java
│   │       ├── controller/
│   │       │   └── TutorialController.java
│   │       ├── model/
│   │       │   └── Tutorial.java
│   │       └── repository/
│   │           └── TutorialRepository.java
│   └── resources/
│       └── application.properties
└── test/
    ├── java/
    │   └── com/adphilip/spring/mssql/
    │       └── SpringBootSqlServerApplicationTests.java
    └── resources/
        └── application.properties
```

## Dependencies
1. Spring Boot Starters:
   - `spring-boot-starter-web`: Web application support
   - `spring-boot-starter-data-jpa`: JPA for database operations
   - `spring-boot-starter-actuator`: Application monitoring
   - `spring-boot-starter-test`: Testing support

2. Database:
   - `mssql-jdbc`: SQL Server driver (runtime scope)
   - `h2`: H2 database (test scope)

3. Testing & Performance:
   - JMH Framework (test scope):
     * `jmh-core`: 1.37
     * `jmh-generator-annprocess`: 1.37

## Build Configuration
- Build Tool: Maven
- Plugins:
  * `spring-boot-maven-plugin`: Spring Boot support
  * `maven-compiler-plugin`: Compilation with JMH annotation processing

For more detail, please visit:
> [Spring Boot CRUD Operations example with SQL Server](https://www.bezkoder.com/spring-boot-sql-server/)

In this tutorial, we're gonna build a Spring Boot Rest CRUD API example with Maven that use Spring Data JPA to interact with H2 database. You'll know:

- How to configure Spring Data, JPA, Hibernate to work with Database
- How to define Data Models and Repository interfaces
- Way to create Spring Rest Controller to process HTTP requests
- Way to use Spring Data JPA to interact with H2 Database

Front-end that works well with this Back-end
> [Axios Client](https://www.bezkoder.com/axios-request/) / [Javascript Fetch API Client](https://www.bezkoder.com/javascript-fetch/)

> [Angular 8 Client](https://www.bezkoder.com/angular-crud-app/) / [Angular 10 Client](https://www.bezkoder.com/angular-10-crud-app/) / [Angular 11 Client](https://www.bezkoder.com/angular-11-crud-app/) / [Angular 12 Client](https://www.bezkoder.com/angular-12-crud-app/) / [Angular 13 Client](https://www.bezkoder.com/angular-13-crud-example/) / [Angular 14 Client](https://www.bezkoder.com/angular-14-crud-example/) / [Angular 15 Client](https://www.bezkoder.com/angular-15-crud-example/)

> [Vue 2 Client](https://www.bezkoder.com/vue-js-crud-app/) / [Vue 3 Client](https://www.bezkoder.com/vue-3-crud/) / [Vuetify Client](https://www.bezkoder.com/vuetify-data-table-example/)

> [React Client](https://www.bezkoder.com/react-crud-web-api/) / [React Redux Client](https://www.bezkoder.com/react-redux-crud-example/)

More Practice:
> [Spring Boot File upload example with Multipart File](https://www.bezkoder.com/spring-boot-file-upload/)

> [Spring Boot Pagination & Filter example | Spring JPA, Pageable](https://www.bezkoder.com/spring-boot-pagination-filter-jpa-pageable/)

> [Spring Data JPA Sort/Order by multiple Columns | Spring Boot](https://www.bezkoder.com/spring-data-sort-multiple-columns/)

> [Spring Boot Repository Unit Test with @DataJpaTest](https://www.bezkoder.com/spring-boot-unit-test-jpa-repo-datajpatest/)

> [Deploy Spring Boot App on AWS – Elastic Beanstalk](https://www.bezkoder.com/deploy-spring-boot-aws-eb/)

Exception Handling:
> [Spring Boot @ControllerAdvice & @ExceptionHandler example](https://www.bezkoder.com/spring-boot-controlleradvice-exceptionhandler/)

> [@RestControllerAdvice example in Spring Boot](https://www.bezkoder.com/spring-boot-restcontrolleradvice/)

Other databases:
> [Spring Boot JPA + H2 example: Build a CRUD Rest APIs](https://www.bezkoder.com/spring-boot-jpa-h2-example/)

> [Spring Boot JPA + MySQL - Building Rest CRUD API example](https://www.bezkoder.com/spring-boot-jpa-crud-rest-api/)

> [Spring Boot JPA + PostgreSQL - Building Rest CRUD API example](https://www.bezkoder.com/spring-boot-postgresql-example/)

Security:
> [Spring Boot + Spring Security JWT Authentication & Authorization](https://www.bezkoder.com/spring-boot-jwt-authentication/)

Run both Back-end & Front-end in one place:
> [Integrate Angular with Spring Boot Rest API](https://www.bezkoder.com/integrate-angular-spring-boot/)

> [Integrate React.js with Spring Boot Rest API](https://www.bezkoder.com/integrate-reactjs-spring-boot/)

> [Integrate Vue.js with Spring Boot Rest API](https://www.bezkoder.com/integrate-vue-spring-boot/)

## Performance Test Results

The application has been tested for performance with the following results:

### Read Operations (findByPublished)
- Average response time: 8.80 ms
- Minimum response time: 4 ms
- Maximum response time: 19 ms
- Transactions per second (TPS): ~114 read operations/second
- Sample size: 5 iterations

### Write Operations (Bulk Insert)
- Total records inserted: 1000
- Total execution time: ~20.146 seconds
- Transactions per second (TPS): ~50 write operations/second

Note: These performance metrics were gathered in the following test environment:

Test Environment Specifications:
- Database: 
  * Production: Microsoft SQL Server
    - Used for actual deployment
    - Configured through application.properties
    - Full RDBMS capabilities
    - Persistent storage
    
  * Testing: H2 in-memory database
    - Version: Latest (included with Spring Boot)
    - Mode: In-memory for fast test execution
    - Features used:
      * Compatible SQL syntax with SQL Server
      * Automatic schema generation
      * In-memory storage for fast testing
      * No persistence between runs
      * Console available at /h2-console (when enabled)
    - Configuration in test/resources/application.properties:
      * URL: jdbc:h2:mem:testdb
      * Username: sa
      * Password: (empty)
      * DDL auto-update enabled
    - Benefits:
      * Fast test execution
      * No external dependencies
      * Lightweight
      * SQL Server compatibility
      * Perfect for unit/integration tests
- Java Version: Spring Boot 3.5.0
- Operating System: macOS
- Test Framework: 
  * JUnit 5 for unit testing
  * JMH (Java Microbenchmark Harness) version 1.37
    - Professional-grade benchmarking tool by OpenJDK team
    - Used for precise performance measurements
    - Features used:
      * Warmup iterations to ensure JVM optimization
      * Multiple measurement iterations for accuracy
      * Statistical analysis of results
      * Prevents common benchmarking mistakes
    - Dependencies in pom.xml:
      * jmh-core: Core benchmarking engine
      * jmh-generator-annprocess: Annotation processor for benchmark compilation
- ORM: Hibernate with Spring Data JPA
  * Hibernate Version: 6.x (included with Spring Boot 3.5.0)
  * Features used:
    - JPA Entity Mapping (@Entity, @Id, @Column)
    - Automatic table creation (hibernate.ddl-auto=update)
    - Query optimization with lazy loading
    - Second-level caching disabled (default)
    - Transaction management via @Transactional
    - Batch insert capabilities
    - SQL dialect: H2Dialect
  * Hibernate Statistics: enabled for performance monitoring
  * Session Management: handled by Spring's OpenSessionInView
- Connection Pool: HikariCP (default Spring Boot configuration)
  * HikariCP is a lightning-fast, reliable JDBC connection pool
  * Default settings: maximum pool size = 10 connections
  * Minimum idle connections = 10
  * Connection timeout = 30 seconds
  * Idle timeout = 600000 (10 minutes)
  * Maximum lifetime = 1800000 (30 minutes)
- Test Data: 1000 records for bulk operations
- Test Types: Repository-level performance testing
- Transaction Management: Spring's @Transactional with default isolation level

Production performance may vary based on:
- Server hardware specifications
- Database configuration and tuning
- Network latency
- Connection pool settings
- Transaction isolation level

## Run Spring Boot application
```
mvn spring-boot:run
```

