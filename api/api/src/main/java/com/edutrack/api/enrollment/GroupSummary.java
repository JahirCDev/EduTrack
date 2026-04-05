package com.edutrack.api.enrollment;
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
