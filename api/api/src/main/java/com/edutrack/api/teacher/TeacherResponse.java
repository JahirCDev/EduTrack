package com.edutrack.api.teacher;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeacherResponse {
  private Long id;
  private String firstName;
  private String lastName;
  private String email;
  private Role role;
  private boolean active;
}
