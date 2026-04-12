package com.edutrack.api.summaries;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentSummary {
  private Long id;
  private String firstName;
  private String lastName;
  private String email;
}
