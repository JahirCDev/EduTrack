package com.edutrack.api.attendance;
import com.edutrack.api.summaries.StudentSummary;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttendanceRecordResponse {
  private Long id;
  private Long sessionId;
  private StudentSummary student;
  private AttendanceStatus status;
  private String notes;
}
