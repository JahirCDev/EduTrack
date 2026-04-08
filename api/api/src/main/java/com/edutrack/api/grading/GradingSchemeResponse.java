package com.edutrack.api.grading;
import com.edutrack.api.summaries.GroupSummary;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GradingSchemeResponse {
  private Long id;
  private GroupSummary group;
  private List<GradingComponentResponse> components;
  private boolean active;
}
