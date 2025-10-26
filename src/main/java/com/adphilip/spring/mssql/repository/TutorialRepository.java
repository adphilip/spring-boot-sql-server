package com.adphilip.spring.mssql.repository;

import com.adphilip.spring.mssql.model.Tutorial;
import java.util.List;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TutorialRepository extends JpaRepository<Tutorial, Long> {

  @Cacheable("tutorials-by-published")
  List<Tutorial> findByPublished(boolean published);

  List<Tutorial> findByTitleContaining(String title);
}
