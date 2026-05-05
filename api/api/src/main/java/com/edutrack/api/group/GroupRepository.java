package com.edutrack.api.group;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository <Group, Long> {
  List<Group> findByPeriodId(Long periodId);
}
