package com.edutrack.api.grading;
import com.edutrack.api.summaries.StudentSummary;
import com.edutrack.api.summaries.ComponentSummary;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GradeEntryResponse {
  private Long id;
  private StudentSummary student;
  private ComponentSummary component;
  private BigDecimal value;
  private boolean active;
}
