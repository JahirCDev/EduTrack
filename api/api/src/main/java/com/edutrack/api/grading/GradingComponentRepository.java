package com.edutrack.api.grading;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradingComponentRepository extends JpaRepository<GradingComponent, Long> {
  List<GradingComponent> findByGradingSchemeId(Long gradingSchemeId);
}
