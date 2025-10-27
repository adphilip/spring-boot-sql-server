# Spring Boot + SQL Server: CRUD Operations & Performance Testing

## Project Information
- **Name:** `spring-boot-sql-server`
- **Group ID:** `com.adphilip`
- **Artifact ID:** `spring-boot-sql-server`
- **Version:** `0.0.1-SNAPSHOT`
- **Description:** Spring Boot REST API with SQL Server and H2, featuring comprehensive performance benchmarking
- **Java Version:** 25
- **Spring Boot Version:** 3.5.7

## Project Structure
```
src/
├── main/
│   ├── java/com/adphilip/spring/mssql/
│   │   ├── SpringBootSqlServerApplication.java
│   │   ├── controller/
│   │   │   └── TutorialController.java          # REST endpoints
│   │   ├── model/
│   │   │   └── Tutorial.java                     # JPA entity
│   │   └── repository/
│   │       └── TutorialRepository.java           # Spring Data JPA repository
│   └── resources/
│       ├── application.properties                 # Main config (active profile)
│       ├── application-h2.properties              # H2 in-memory config
│       └── application-sqlserver.properties       # SQL Server config
│
└── test/
    ├── java/com/adphilip/spring/mssql/
    │   ├── SpringBootSqlServerApplicationTests.java
    │   ├── integration/
    │   │   ├── ApplicationContextH2IntegrationTests.java           # H2 integration tests
    │   │   └── ApplicationContextSqlServerIntegrationTests.java    # SQL integration tests
    │   └── performance/
    │       ├── TutorialRepositoryH2PerformanceTests.java           # H2 benchmarks
    │       └── TutorialRepositorySqlServerPerformanceTests.java    # SQL benchmarks
    └── resources/
        ├── application.properties                 # Test config
        └── logback-test.xml                       # Test logging config
```

## Key Dependencies
- **Spring Boot Starters:**
  - `spring-boot-starter-web` - REST API support
  - `spring-boot-starter-data-jpa` - JPA with Hibernate
  - `spring-boot-starter-test` - JUnit 5 testing framework

- **Databases:**
  - `mssql-jdbc` - Microsoft SQL Server JDBC driver
  - `h2` - H2 in-memory database (for testing)

- **Connection Pooling:**
  - `HikariCP` - High-performance JDBC connection pool (included with Spring Boot)

## Performance Benchmarks (October 26, 2025)

Comprehensive performance comparison between H2 in-memory and SQL Server databases.

### Test Configuration
- **Warm-up iterations:** 10
- **Test iterations:** 20
- **Batch size:** 1,000 records
- **Environment:** macOS, Spring Boot 3.5.7, Java 25, Hibernate 6.6.33.Final

### Results Summary

#### 1. Batch Insert (1,000 records)
| Database | Avg Time | Min | Max | TPS | Winner |
|----------|----------|-----|-----|-----|---------|
| H2 | 242.90 ms | 4 ms | 4000 ms | 4.12 | - |
| SQL Server | **188.00 ms** | 62 ms | 405 ms | **5.32** | ✅ **23% faster** |

#### 2. Find by Title (indexed query)
| Database | Avg Time | Min | Max | TPS | Winner |
|----------|----------|-----|-----|-----|---------|
| H2 | **5.40 ms** | 3 ms | 9 ms | **185.19** | ✅ **79% faster** |
| SQL Server | 26.55 ms | 18 ms | 55 ms | 37.66 | - |

#### 3. Find by Published (boolean query)
| Database | Avg Time | Min | Max | TPS | Winner |
|----------|----------|-----|-----|-----|---------|
| H2 | **5.30 ms** | 1 ms | 50 ms | **188.68** | ✅ **52% faster** |
| SQL Server | 11.10 ms | 7 ms | 17 ms | 90.09 | - |

### Key Insights

**SQL Server Strengths:**
- ✅ Better bulk write performance (batch inserts)
- ✅ More stable timing (smaller min-max variance)
- ✅ Production-ready with ACID guarantees

**H2 Strengths:**
- ⚡ **5x faster** for indexed lookups (in-memory access)
- ⚡ **2x faster** for boolean queries
- 🔧 Zero setup required for testing
- 🧪 Perfect for unit/integration tests

### Recommendations
- **Development/Testing:** Use H2 for fast, zero-config testing
- **Production:** Use SQL Server for reliability, persistence, and enterprise features
- **Optimization Ideas:**
  - Add index on `published` column in SQL Server
  - Tune batch size (test 500, 2000 records)
  - Increase test iterations to reduce JIT noise


## Database Configuration

The application supports dual-database profiles for flexible development and testing.

### Available Profiles
- **`sqlserver`** - Microsoft SQL Server (production)
- **`h2`** - H2 in-memory database (development/testing)

### Switching Between Databases

**Method 1: Edit `application.properties`**
```properties
spring.profiles.active=sqlserver  # or h2
```

**Method 2: Command Line**
```bash
# Run with SQL Server
./mvnw spring-boot:run -Dspring-boot.run.profiles=sqlserver

# Run with H2
./mvnw spring-boot:run -Dspring-boot.run.profiles=h2
```

**Method 3: Environment Variable**
```bash
export SPRING_PROFILES_ACTIVE=h2
./mvnw spring-boot:run
```

### SQL Server Configuration (`application-sqlserver.properties`)
```properties
spring.datasource.url=jdbc:sqlserver://localhost:1434;databaseName=adphilip_db;encrypt=true;trustServerCertificate=true
spring.datasource.username=adphilip
spring.datasource.password=Str0ngP@ssw0rd!#2025
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.SQLServerDialect
spring.jpa.hibernate.ddl-auto=update
```

### H2 Configuration (`application-h2.properties`)
```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=create-drop
```

## SQL Server Setup with Docker

### Quick Start

**1. Pull and Run SQL Server**
```bash
docker run -e 'ACCEPT_EULA=Y' \
  -e 'MSSQL_SA_PASSWORD=Str0ngP@ssw0rd!#2025' \
  -p 1434:1433 \
  --name sqlserver \
  -d mcr.microsoft.com/mssql/server:2022-latest
```

**2. Create Database**
```bash
docker exec sqlserver /opt/mssql-tools18/bin/sqlcmd \
  -S localhost -U sa -P 'Str0ngP@ssw0rd!#2025' -C \
  -Q "CREATE DATABASE adphilip_db; CREATE LOGIN adphilip WITH PASSWORD = 'Str0ngP@ssw0rd!#2025';"
```

**3. Grant Permissions**
```bash
docker exec sqlserver /opt/mssql-tools18/bin/sqlcmd \
  -S localhost -U sa -P 'Str0ngP@ssw0rd!#2025' -C -d adphilip_db \
  -Q "CREATE USER adphilip FOR LOGIN adphilip; ALTER ROLE db_owner ADD MEMBER adphilip;"
```

### Container Management
```bash
# Start/Stop
docker start sqlserver
docker stop sqlserver

# View logs
docker logs sqlserver

# Connect to SQL
docker exec -it sqlserver /opt/mssql-tools18/bin/sqlcmd \
  -S localhost -U adphilip -P 'Str0ngP@ssw0rd!#2025' -C -d adphilip_db

# Remove container
docker rm -f sqlserver
```

### Container Info
- **Edition:** SQL Server 2022 Developer (64-bit)
- **Port:** `1434` (host) → `1433` (container)
- **Database:** `adphilip_db`
- **User:** `adphilip` (db_owner)

## Running the Application

### Build and Run
```bash
# Build the project
./mvnw clean package

# Run with SQL Server profile
./mvnw spring-boot:run -Dspring-boot.run.profiles=sqlserver

# Run with H2 profile
./mvnw spring-boot:run -Dspring-boot.run.profiles=h2
```

### Run Tests

**All Tests**
```bash
./mvnw test
```

**Integration Tests Only**
```bash
# H2 integration tests
./mvnw test -Dtest=ApplicationContextH2IntegrationTests

# SQL Server integration tests
./mvnw test -Dtest=ApplicationContextSqlServerIntegrationTests
```

**Performance Tests**
```bash
# H2 performance benchmarks
./mvnw test -Dtest=TutorialRepositoryH2PerformanceTests

# SQL Server performance benchmarks
./mvnw test -Dtest=TutorialRepositorySqlServerPerformanceTests

# Specific performance test method
./mvnw test -Dtest=TutorialRepositoryH2PerformanceTests#measureBatchInsertPerformance
```

## REST API Endpoints

### Tutorial CRUD Operations
- `GET /api/tutorials` - Get all tutorials
- `GET /api/tutorials/{id}` - Get tutorial by ID
- `GET /api/tutorials?title={title}` - Search by title
- `GET /api/tutorials/published` - Get published tutorials
- `POST /api/tutorials` - Create new tutorial
- `PUT /api/tutorials/{id}` - Update tutorial
- `DELETE /api/tutorials/{id}` - Delete tutorial
- `DELETE /api/tutorials` - Delete all tutorials

### Example Request
```bash
# Create a tutorial
curl -X POST http://localhost:8080/api/tutorials \
  -H "Content-Type: application/json" \
  -d '{"title":"Spring Boot Tutorial","description":"Learn Spring Boot","published":true}'

# Get all tutorials
curl http://localhost:8080/api/tutorials

# Get published tutorials
curl http://localhost:8080/api/tutorials/published
```

## Test Infrastructure

### Test Organization
- **Integration Tests:** Verify application context, bean wiring, and database connectivity
- **Performance Tests:** Benchmark repository operations with detailed metrics

### Test Features
- ✅ **Datasource Verification:** JDBC URL assertions ensure correct database usage
- ✅ **Profile Isolation:** Separate H2 and SQL Server test suites with `@ActiveProfiles`
- ✅ **Clean Logging:** `logback-test.xml` reduces noise while preserving performance metrics
- ✅ **Repeatable Benchmarks:** Warm-up iterations and statistical sampling

### Test Logging Configuration
The `src/test/resources/logback-test.xml` minimizes log verbosity during tests:
- Spring/Hibernate/HikariCP: `WARN` level
- Performance test package: `INFO` level (metrics visible)

## Technologies Used
- **Framework:** Spring Boot 3.5.7
- **Java:** 25
- **ORM:** Hibernate 6.6.33.Final
- **Database:** SQL Server 2022 / H2 (in-memory)
- **Connection Pool:** HikariCP
- **Build Tool:** Maven
- **Testing:** JUnit 5

## License
MIT License


