package com.adphilip.spring.mssql.performance;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.TestConstructor;

import com.adphilip.spring.mssql.model.Tutorial;
import com.adphilip.spring.mssql.repository.TutorialRepository;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@DataJpaTest
@ActiveProfiles("h2")
@TestPropertySource(properties = {
    "spring.jpa.hibernate.ddl-auto=create-drop",
    "spring.jpa.show-sql=false"
})
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class TutorialRepositoryH2PerformanceTests {
    private final TutorialRepository tutorialRepository;

    private static final Logger logger = LoggerFactory.getLogger(TutorialRepositoryH2PerformanceTests.class);

    private static final int BATCH_SIZE = 1000;
    private static final int WARMUP_ITERATIONS = 10;
    private static final int TEST_ITERATIONS = 20;

    TutorialRepositoryH2PerformanceTests(TutorialRepository tutorialRepository) {
        this.tutorialRepository = tutorialRepository;
    }

    @BeforeEach
    void setUp() {
        // Prepare test data
        List<Tutorial> tutorials = new ArrayList<>();
        for (int i = 0; i < BATCH_SIZE; i++) {
            tutorials.add(new Tutorial(
                "Tutorial " + i,
                "Description " + i,
                i % 2 == 0
            ));
        }
        tutorialRepository.saveAll(tutorials);
    }

    @Test
    void measureBatchInsertPerformance() {
        // Warmup
        for (int i = 0; i < WARMUP_ITERATIONS; i++) {
            performBatchInsert();
            tutorialRepository.deleteAll();
        }

        // Actual measurement
        List<Long> durations = new ArrayList<>();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            long duration = performBatchInsert();
            durations.add(duration);
            tutorialRepository.deleteAll();
        }

        // Calculate and print statistics
        printStatistics("Batch Insert (ms)", durations);
    }

    @Test
    void measureFindByTitlePerformance() {
        // Warmup
        for (int i = 0; i < WARMUP_ITERATIONS; i++) {
            performFindByTitle();
        }

        // Actual measurement
        List<Long> durations = new ArrayList<>();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            long duration = performFindByTitle();
            durations.add(duration);
        }

        // Calculate and print statistics
        printStatistics("Find by Title (ms)", durations);
    }

    @Test
    void measureFindByPublishedPerformance() {
        // Warmup
        for (int i = 0; i < WARMUP_ITERATIONS; i++) {
            performFindByPublished();
        }

        // Actual measurement
        List<Long> durations = new ArrayList<>();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            long duration = performFindByPublished();
            durations.add(duration);
        }

        // Calculate and print statistics
        printStatistics("Find by Published (ms)", durations);
    }

    private long performBatchInsert() {
        List<Tutorial> tutorials = new ArrayList<>();
        for (int i = 0; i < BATCH_SIZE; i++) {
            tutorials.add(new Tutorial(
                "Performance Test " + i,
                "Performance Description " + i,
                i % 2 == 0
            ));
        }

        Instant start = Instant.now();
        List<Tutorial> saved = tutorialRepository.saveAll(tutorials);
        Instant end = Instant.now();

        assertThat(saved).hasSize(BATCH_SIZE);
        return Duration.between(start, end).toMillis();
    }

    private long performFindByTitle() {
        Instant start = Instant.now();
        List<Tutorial> found = tutorialRepository.findByTitleContaining("Tutorial");
        Instant end = Instant.now();

        assertThat(found).hasSizeGreaterThan(0);
        return Duration.between(start, end).toMillis();
    }

    private long performFindByPublished() {
        Instant start = Instant.now();
        List<Tutorial> found = tutorialRepository.findByPublished(true);
        Instant end = Instant.now();

        assertThat(found).hasSizeGreaterThan(0);
        return Duration.between(start, end).toMillis();
    }

    private void printStatistics(String operation, List<Long> durations) {
        double avg = durations.stream().mapToLong(Long::valueOf).average().orElse(0.0);
        long min = durations.stream().mapToLong(Long::valueOf).min().orElse(0);
        long max = durations.stream().mapToLong(Long::valueOf).max().orElse(0);
        double tps = avg > 0 ? 1000.0 / avg : 0.0; // Convert ms to TPS

    logger.info("");
    logger.info("Performance Statistics for: {} [H2]", operation);
    logger.info("Average time: {} ms", String.format("%.2f", avg));
    logger.info("Min time: {} ms", min);
    logger.info("Max time: {} ms", max);
    logger.info("Transactions per second (TPS): {}", String.format("%.2f", tps));
    logger.info("Sample size: {} iterations", durations.size());
    }
}
