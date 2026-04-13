package com.edutrack.api.group;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeacherSummary {
  private Long id;
  private String firstName;
  private String lastName;
}
