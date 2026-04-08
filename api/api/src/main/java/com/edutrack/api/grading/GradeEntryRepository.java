package com.edutrack.api.grading;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradeEntryRepository extends JpaRepository<GradeEntry, Long>{
  
}
