package com.edutrack.api.summaries;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupSummary {
  private Long id;
  private String name;  
  private String teacherName;
}
