package com.edutrack.api.enrollment;
import java.time.LocalDate;

import com.edutrack.api.summaries.GroupSummary;
import com.edutrack.api.summaries.StudentSummary;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnrollmentResponse {
  private Long id;
  private StudentSummary student;
  private GroupSummary group;
  private EnrollmentStatus status;
  private LocalDate enrolledAt;
}
