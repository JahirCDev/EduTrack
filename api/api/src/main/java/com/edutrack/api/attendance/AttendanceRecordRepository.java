package com.edutrack.api.attendance;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRecordRepository extends JpaRepository<AttendanceRecord, Long> {
  List<AttendanceRecord> findBySessionId(Long sessionId);
}
 