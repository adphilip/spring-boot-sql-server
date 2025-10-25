package com.adphilip.spring.mssql.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.adphilip.spring.mssql.model.Tutorial;

@DataJpaTest
class TutorialRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private TutorialRepository tutorialRepository;

    private Tutorial testTutorial;

    @BeforeEach
    void setUp() {
        testTutorial = new Tutorial("Spring Boot Test", "Testing with H2", false);
        entityManager.persist(testTutorial);
        entityManager.flush();
    }

    @Test
    void shouldSaveTutorial() {
        Tutorial tutorial = new Tutorial("JPA Test", "Testing JPA with H2", true);
        Tutorial savedTutorial = tutorialRepository.save(tutorial);
        
        assertThat(savedTutorial).isNotNull();
        assertThat(savedTutorial.getId()).isGreaterThan(0);
    }

    @Test
    void shouldFindTutorialById() {
        Optional<Tutorial> found = tutorialRepository.findById(testTutorial.getId());
        
        assertThat(found).isPresent();
        assertThat(found.get().getTitle()).isEqualTo(testTutorial.getTitle());
    }

    @Test
    void shouldFindByPublished() {
        Tutorial publishedTutorial = new Tutorial("Published Tutorial", "This is published", true);
        entityManager.persist(publishedTutorial);
        entityManager.flush();

        List<Tutorial> publishedTutorials = tutorialRepository.findByPublished(true);
        List<Tutorial> unpublishedTutorials = tutorialRepository.findByPublished(false);

        assertThat(publishedTutorials).hasSize(1);
        assertThat(unpublishedTutorials).hasSize(1);
        assertThat(publishedTutorials.get(0).getTitle()).isEqualTo("Published Tutorial");
    }

    @Test
    void shouldFindByTitleContaining() {
        Tutorial springTutorial1 = new Tutorial("Spring Data JPA", "JPA Tutorial", false);
        Tutorial springTutorial2 = new Tutorial("Spring Security", "Security Tutorial", false);
        Tutorial hibernateTutorial = new Tutorial("Hibernate Basics", "ORM Tutorial", false);

        entityManager.persist(springTutorial1);
        entityManager.persist(springTutorial2);
        entityManager.persist(hibernateTutorial);
        entityManager.flush();

        List<Tutorial> springTutorials = tutorialRepository.findByTitleContaining("Spring");
        List<Tutorial> hibernateTutorials = tutorialRepository.findByTitleContaining("Hibernate");

        assertThat(springTutorials).hasSize(3); // Including the one from setUp
        assertThat(hibernateTutorials).hasSize(1);
    }

    @Test
    void shouldUpdateTutorial() {
        Tutorial tutorial = tutorialRepository.findById(testTutorial.getId()).get();
        tutorial.setTitle("Updated Title");
        tutorial.setPublished(true);

        Tutorial updatedTutorial = tutorialRepository.save(tutorial);

        assertThat(updatedTutorial.getTitle()).isEqualTo("Updated Title");
        assertThat(updatedTutorial.isPublished()).isTrue();
    }

    @Test
    void shouldDeleteTutorial() {
        tutorialRepository.deleteById(testTutorial.getId());
        Optional<Tutorial> deletedTutorial = tutorialRepository.findById(testTutorial.getId());

        assertThat(deletedTutorial).isEmpty();
    }
}