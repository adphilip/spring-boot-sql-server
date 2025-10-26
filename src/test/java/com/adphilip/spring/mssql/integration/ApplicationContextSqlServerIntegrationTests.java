package com.adphilip.spring.mssql.integration;

import com.adphilip.spring.mssql.repository.TutorialRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("sqlserver")
class ApplicationContextSqlServerIntegrationTests {

    private final ApplicationContext context;
    private final TutorialRepository tutorialRepository;
    private final DataSource dataSource;

    @Autowired
    ApplicationContextSqlServerIntegrationTests(ApplicationContext context,
                                                TutorialRepository tutorialRepository,
                                                DataSource dataSource) {
        this.context = context;
        this.tutorialRepository = tutorialRepository;
        this.dataSource = dataSource;
    }

    @Test
    void contextLoads() {
        assertNotNull(context, "Application context should load successfully");
    }

    @Test
    void tutorialRepositoryBeanExists() {
        assertNotNull(tutorialRepository, "TutorialRepository bean should exist in the context");
    }

    @Test
    void dataSourceBeanExists() {
        assertNotNull(dataSource, "DataSource bean should exist in the context");
    }

    @Test
    void repositoryCanQueryDatabase() {
        assertDoesNotThrow(() -> {
            long count = tutorialRepository.count();
            assertTrue(count >= 0, "Repository should be able to query the database");
        });
    }

    @Test
    void databaseConnectionWorks() {
        assertDoesNotThrow(() -> {
            try (var connection = dataSource.getConnection()) {
                assertTrue(connection.isValid(5), "Database connection should be valid");
            }
        });
    }
}
