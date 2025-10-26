package com.adphilip.spring.mssql.controller;

import com.adphilip.spring.mssql.model.Tutorial;
import com.adphilip.spring.mssql.repository.TutorialRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class TutorialController {

  private final TutorialRepository tutorialRepository;

  public TutorialController(TutorialRepository tutorialRepository) {
    this.tutorialRepository = tutorialRepository;
  }

  @GetMapping("/tutorials")
  public ResponseEntity<List<Tutorial>> getAllTutorials(
      @RequestParam(required = false) String title) {
    try {
      List<Tutorial> tutorials = new ArrayList<>();

      if (title == null) {
        tutorialRepository.findAll().forEach(tutorials::add);
      } else {
        tutorialRepository.findByTitleContaining(title)
            .forEach(tutorials::add);
      }

      if (tutorials.isEmpty()) {
        return ResponseEntity.noContent().build();
      }

      return ResponseEntity.ok(tutorials);
    } catch (RuntimeException e) {
      return ResponseEntity.internalServerError().build();
    }
  }

  @GetMapping("/tutorials/{id}")
  public ResponseEntity<Tutorial> getTutorialById(
      @PathVariable("id") long id) {
    Optional<Tutorial> tutorialData = tutorialRepository.findById(id);

    if (tutorialData.isPresent()) {
      return ResponseEntity.ok(tutorialData.get());
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @PostMapping("/tutorials")
  public ResponseEntity<Tutorial> createTutorial(
      @RequestBody Tutorial tutorial) {
    try {
      Tutorial createdTutorial = tutorialRepository.save(
          new Tutorial(tutorial.getTitle(),
              tutorial.getDescription(), false));
      return ResponseEntity.status(HttpStatus.CREATED)
          .body(createdTutorial);
    } catch (RuntimeException e) {
      return ResponseEntity.internalServerError().build();
    }
  }

  @PutMapping("/tutorials/{id}")
  public ResponseEntity<Tutorial> updateTutorial(
      @PathVariable("id") long id, @RequestBody Tutorial tutorial) {
    Optional<Tutorial> tutorialData = tutorialRepository.findById(id);

    if (tutorialData.isPresent()) {
      Tutorial existingTutorial = tutorialData.get();
      existingTutorial.setTitle(tutorial.getTitle());
      existingTutorial.setDescription(tutorial.getDescription());
      existingTutorial.setPublished(tutorial.isPublished());
      return ResponseEntity.ok(
          tutorialRepository.save(existingTutorial));
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/tutorials/{id}")
  public ResponseEntity<HttpStatus> deleteTutorial(
      @PathVariable("id") long id) {
    try {
      tutorialRepository.deleteById(id);
      return ResponseEntity.noContent().build();
    } catch (RuntimeException e) {
      return ResponseEntity.internalServerError().build();
    }
  }

  @DeleteMapping("/tutorials")
  public ResponseEntity<HttpStatus> deleteAllTutorials() {
    try {
      tutorialRepository.deleteAll();
      return ResponseEntity.noContent().build();
    } catch (RuntimeException e) {
      return ResponseEntity.internalServerError().build();
    }
  }

  @GetMapping("/tutorials/published")
  public ResponseEntity<List<Tutorial>> findByPublished() {
    try {
      List<Tutorial> tutorials =
          tutorialRepository.findByPublished(true);

      if (tutorials.isEmpty()) {
        return ResponseEntity.noContent().build();
      }
      return ResponseEntity.ok(tutorials);
    } catch (RuntimeException e) {
      return ResponseEntity.internalServerError().build();
    }
  }

}
