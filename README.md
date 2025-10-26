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
   - `spring-boot-starter-cache`: Caching support

2. Database:
   - `mssql-jdbc`: SQL Server driver (runtime scope)
   - `h2`: H2 database (test scope)

3. Testing & Performance:
   - JMH Framework (test scope):
     * `jmh-core`: 1.37
     * `jmh-generator-annprocess`: 1.37

## Performance Optimizations
### Current Performance (as of October 25, 2025)
- Transactions per second (TPS): 540.54
- Average response time: 1.85ms
- Minimum response time: 1ms
- Maximum response time: 3ms

### Implemented Optimizations
1. Database Indexing
   - Added index on 'published' column for faster querying
   - Significantly reduced database lookup time

2. Connection Pool (HikariCP) Configuration
   - Maximum pool size: 20 connections
   - Minimum idle: 10 connections
   - Connection timeout: 20000ms
   - Maximum lifetime: 1200000ms (20 minutes)
   - Idle timeout: 300000ms (5 minutes)

3. Hibernate Optimizations
   - Batch size: 50
   - Show SQL: enabled (for development)
   - Format SQL: enabled (for development)
   - Statistics: enabled (for monitoring)

4. Spring Cache Implementation
   - Enabled caching with @EnableCaching
   - Implemented cache on findByPublished() method
   - Significantly reduced database hits for repeated queries

### Performance Improvement Results
- Initial Performance:
  * TPS: 142.86
  * Average time: 7.0ms
  * Min time: 2ms
  * Max time: 27ms

- After Optimizations:
  * TPS: 540.54 (3.78x improvement)
  * Average time: 1.85ms (3.78x faster)
  * Min time: 1ms
  * Max time: 3ms (9x improvement in worst case)

### Key Achievements
1. 278% increase in throughput (TPS)
2. 73% reduction in average response time
3. 89% reduction in maximum response time
4. More consistent performance with smaller time variance

## Build Configuration
- Build Tool: Maven
- Plugins:
  * `spring-boot-maven-plugin`: Spring Boot support
  * `maven-compiler-plugin`: Compilation with JMH annotation processing

For more detail, please visit:
> [Spring Boot CRUD Operations example with SQL Server](https://www.adphilip.com/spring-boot-sql-server/)

In this tutorial, we're gonna build a Spring Boot Rest CRUD API example with Maven that use Spring Data JPA to interact with H2 database. You'll know:

- How to configure Spring Data, JPA, Hibernate to work with Database
- How to define Data Models and Repository interfaces
- Way to create Spring Rest Controller to process HTTP requests
- Way to use Spring Data JPA to interact with H2 Database

Front-end that works well with this Back-end
> [Axios Client](https://www.adphilip.com/axios-request/) / [Javascript Fetch API Client](https://www.adphilip.com/javascript-fetch/)

> [Angular 8 Client](https://www.adphilip.com/angular-crud-app/) / [Angular 10 Client](https://www.adphilip.com/angular-10-crud-app/) / [Angular 11 Client](https://www.adphilip.com/angular-11-crud-app/) / [Angular 12 Client](https://www.adphilip.com/angular-12-crud-app/) / [Angular 13 Client](https://www.adphilip.com/angular-13-crud-example/) / [Angular 14 Client](https://www.adphilip.com/angular-14-crud-example/) / [Angular 15 Client](https://www.adphilip.com/angular-15-crud-example/)

> [Vue 2 Client](https://www.adphilip.com/vue-js-crud-app/) / [Vue 3 Client](https://www.adphilip.com/vue-3-crud/) / [Vuetify Client](https://www.adphilip.com/vuetify-data-table-example/)

> [React Client](https://www.adphilip.com/react-crud-web-api/) / [React Redux Client](https://www.adphilip.com/react-redux-crud-example/)

More Practice:
> [Spring Boot File upload example with Multipart File](https://www.adphilip.com/spring-boot-file-upload/)

> [Spring Boot Pagination & Filter example | Spring JPA, Pageable](https://www.adphilip.com/spring-boot-pagination-filter-jpa-pageable/)

> [Spring Data JPA Sort/Order by multiple Columns | Spring Boot](https://www.adphilip.com/spring-data-sort-multiple-columns/)

> [Spring Boot Repository Unit Test with @DataJpaTest](https://www.adphilip.com/spring-boot-unit-test-jpa-repo-datajpatest/)

> [Deploy Spring Boot App on AWS – Elastic Beanstalk](https://www.adphilip.com/deploy-spring-boot-aws-eb/)

Exception Handling:
> [Spring Boot @ControllerAdvice & @ExceptionHandler example](https://www.adphilip.com/spring-boot-controlleradvice-exceptionhandler/)

> [@RestControllerAdvice example in Spring Boot](https://www.adphilip.com/spring-boot-restcontrolleradvice/)

Other databases:
> [Spring Boot JPA + H2 example: Build a CRUD Rest APIs](https://www.adphilip.com/spring-boot-jpa-h2-example/)

> [Spring Boot JPA + MySQL - Building Rest CRUD API example](https://www.adphilip.com/spring-boot-jpa-crud-rest-api/)

> [Spring Boot JPA + PostgreSQL - Building Rest CRUD API example](https://www.adphilip.com/spring-boot-postgresql-example/)

Security:
> [Spring Boot + Spring Security JWT Authentication & Authorization](https://www.adphilip.com/spring-boot-jwt-authentication/)

Run both Back-end & Front-end in one place:
> [Integrate Angular with Spring Boot Rest API](https://www.adphilip.com/integrate-angular-spring-boot/)

> [Integrate React.js with Spring Boot Rest API](https://www.adphilip.com/integrate-reactjs-spring-boot/)

> [Integrate Vue.js with Spring Boot Rest API](https://www.adphilip.com/integrate-vue-spring-boot/)

## Performance Test Results

The application has been tested for performance with both SQL Server and H2 databases with optimized configurations:

### H2 In-Memory Performance (October 26, 2025) - ⚡ OPTIMIZED
Testing against H2 in-memory database with full Hibernate optimizations:
- **Average response time:** 1.90 ms
- **Minimum response time:** 1 ms
- **Maximum response time:** 4 ms
- **Transactions per second (TPS):** 526.32
- **Sample size:** 20 iterations
- **Batch insert:** 1000 records successfully inserted

### SQL Server Performance (October 26, 2025)
Testing against actual SQL Server database running in Docker (localhost:1434):
- **Average response time:** 5.50 ms
- **Minimum response time:** 2 ms
- **Maximum response time:** 50 ms
- **Transactions per second (TPS):** 181.82
- **Sample size:** 20 iterations
- **Batch insert:** 1000 records successfully inserted

### Performance Comparison: H2 vs SQL Server

| Metric | H2 (Optimized) | SQL Server | Winner |
|--------|----------------|-----------|---------|
| **Average Time** | 1.90 ms | 5.50 ms | **H2 is 189% faster** ⚡ |
| **TPS** | 526.32 | 181.82 | **H2 has 189% higher throughput** 🚀 |
| **Min Time** | 1 ms | 2 ms | **H2 (50% faster)** |
| **Max Time** | 4 ms | 50 ms | **H2 (92% more stable)** |

### Key Findings
- **H2 is significantly faster** as expected for an in-memory database - almost **3x faster** than SQL Server!
- **Critical optimization:** Both databases now have matching configurations:
  * Database indexing (`idx_published` on `published` column)
  * Hibernate query plan cache (2048 entries)
  * Connection pooling (HikariCP with 20 max connections)
  * Batch processing (batch_size=50)
  * Hibernate statistics enabled
- **Before optimization:** H2 was incorrectly slower (15.55ms vs 5.50ms) due to missing optimizations
- **After optimization:** H2 achieves expected in-memory performance (1.90ms)

### Recommendations
- **Use H2 for:**
  * Development and testing (ultra-fast, no setup required)
  * Unit/integration tests (526 TPS throughput)
  * Prototyping and demos
- **Use SQL Server for:**
  * Production deployments (data persistence, ACID compliance)
  * Multi-user environments
  * Enterprise features (backups, replication, security)

## Database Configuration Profiles

The application supports multiple database profiles for easy switching between environments:

### Available Profiles
1. **sqlserver** - Production SQL Server database
2. **h2** - In-memory H2 database for testing

### Profile Configuration Files
- `application.properties` - Main configuration (sets active profile)
- `application-sqlserver.properties` - SQL Server specific settings
- `application-h2.properties` - H2 specific settings

### Switching Between Databases

**Option 1: Edit application.properties**
```properties
# Set to 'sqlserver' or 'h2'
spring.profiles.active=sqlserver
```

**Option 2: Command Line**
```bash
# Run with SQL Server
./mvnw spring-boot:run -Dspring-boot.run.profiles=sqlserver

# Run with H2
./mvnw spring-boot:run -Dspring-boot.run.profiles=h2

# Run tests with SQL Server
./mvnw test -Dspring.profiles.active=sqlserver

# Run tests with H2
./mvnw test -Dspring.profiles.active=h2

# Run specific performance test with H2
./mvnw test -Dtest=TutorialRepositoryPerformanceTests -Dspring.profiles.active=h2
```

**Option 3: Environment Variable**
```bash
export SPRING_PROFILES_ACTIVE=h2
./mvnw spring-boot:run
```

### SQL Server Configuration
- **URL:** jdbc:sqlserver://localhost:1434
- **Database:** adphilip_db
- **Encryption:** Enabled with self-signed certificate
- **Dialect:** SQLServerDialect
- **DDL Auto:** update
- **Connection Pool:** HikariCP (max 20, min idle 10)

### H2 Configuration
- **URL:** jdbc:h2:mem:testdb
- **Mode:** In-memory
- **Dialect:** H2Dialect
- **DDL Auto:** create-drop
- **Connection Pool:** HikariCP (max 10, min idle 5)

## SQL Server Setup with Docker

### Prerequisites
- Docker Desktop installed on your Mac

### Initial Setup

**1. Pull SQL Server Docker Image**
```bash
docker pull mcr.microsoft.com/mssql/server:2022-latest
```

**2. Start SQL Server Container**
```bash
docker run -e 'ACCEPT_EULA=Y' \
  -e 'MSSQL_SA_PASSWORD=Str0ngP@ssw0rd!#2025' \
  -p 1434:1433 \
  --name sqlserver \
  -d mcr.microsoft.com/mssql/server:2022-latest
```

**3. Create Database and User**

Create a file named `setup-db.sql`:
```sql
-- Create database
IF NOT EXISTS (SELECT * FROM sys.databases WHERE name = 'adphilip_db')
BEGIN
    CREATE DATABASE adphilip_db;
END
GO

USE adphilip_db;
GO

-- Create login
IF NOT EXISTS (SELECT * FROM sys.server_principals WHERE name = 'adphilip')
BEGIN
    CREATE LOGIN adphilip WITH PASSWORD = 'Str0ngP@ssw0rd!#2025';
END
GO

-- Create user
IF NOT EXISTS (SELECT * FROM sys.database_principals WHERE name = 'adphilip')
BEGIN
    CREATE USER adphilip FOR LOGIN adphilip;
END
GO

-- Grant permissions
ALTER ROLE db_owner ADD MEMBER adphilip;
GO
```

Copy and execute the script:
```bash
# Copy SQL script to container
docker cp setup-db.sql sqlserver:/tmp/setup-db.sql

# Execute the script
docker exec sqlserver /opt/mssql-tools18/bin/sqlcmd \
  -S localhost -U sa -P 'Str0ngP@ssw0rd!#2025' -C \
  -i /tmp/setup-db.sql
```

### Docker Management Commands

**Check Container Status**
```bash
# List running containers
docker ps | grep sqlserver

# View all containers (including stopped)
docker ps -a | grep sqlserver
```

**Start/Stop/Restart Container**
```bash
# Stop SQL Server
docker stop sqlserver

# Start SQL Server (after stopped)
docker start sqlserver

# Restart SQL Server
docker restart sqlserver
```

**View Logs**
```bash
# View container logs
docker logs sqlserver

# Follow logs in real-time
docker logs -f sqlserver

# View last 50 lines
docker logs --tail 50 sqlserver
```

**Connect to SQL Server**
```bash
# Interactive SQL connection
docker exec -it sqlserver /opt/mssql-tools18/bin/sqlcmd \
  -S localhost -U adphilip -P 'Str0ngP@ssw0rd!#2025' -C -d adphilip_db

# Run a quick query
docker exec sqlserver /opt/mssql-tools18/bin/sqlcmd \
  -S localhost -U adphilip -P 'Str0ngP@ssw0rd!#2025' -C -d adphilip_db \
  -Q "SELECT DB_NAME() AS CurrentDatabase, SYSTEM_USER AS CurrentUser;"
```

**Remove Container**
```bash
# Stop and remove container (to start fresh)
docker rm -f sqlserver

# Remove container and image
docker rm -f sqlserver
docker rmi mcr.microsoft.com/mssql/server:2022-latest
```

### Data Persistence (Optional)

To persist data across container restarts, add a volume mount:
```bash
docker run -e 'ACCEPT_EULA=Y' \
  -e 'MSSQL_SA_PASSWORD=Str0ngP@ssw0rd!#2025' \
  -p 1434:1433 \
  -v ~/sqlserver-data:/var/opt/mssql \
  --name sqlserver \
  -d mcr.microsoft.com/mssql/server:2022-latest
```

### Container Information
- **Edition:** SQL Server 2022 Developer Edition (64-bit)
- **Platform:** Linux (Ubuntu 22.04.5 LTS)
- **Port Mapping:** `1434` (host) → `1433` (container)
- **SA Password:** `Str0ngP@ssw0rd!#2025`
- **Database:** `adphilip_db`
- **User:** `adphilip` (db_owner permissions)

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

