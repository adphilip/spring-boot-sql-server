package com.adphilip.spring.mssql;

import com.adphilip.spring.mssql.repository.TutorialRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("h2")
class SpringBootSqlServerApplicationTests {

    private final ApplicationContext context;
    private final TutorialRepository tutorialRepository;
    private final DataSource dataSource;

    @Autowired
    SpringBootSqlServerApplicationTests(ApplicationContext context,
                                        TutorialRepository tutorialRepository,
                                        DataSource dataSource) {
        this.context = context;
        this.tutorialRepository = tutorialRepository;
        this.dataSource = dataSource;
    }	@Test
	void contextLoads() {
		assertNotNull(context, "Application context should be loaded");
	}

	@Test
	void tutorialRepositoryBeanExists() {
		assertNotNull(tutorialRepository, "TutorialRepository bean should be available");
	}

	@Test
	void dataSourceBeanExists() {
		assertNotNull(dataSource, "DataSource bean should be available");
	}

	@Test
	void repositoryCanQueryDatabase() {
		// Should work regardless of which database profile is active
		assertTrue(tutorialRepository.count() >= 0, "Should be able to query the database");
	}

	@Test
	void databaseConnectionWorks() {
		assertDoesNotThrow(() -> {
			dataSource.getConnection().close();
		}, "Should be able to get a database connection");
	}

}
