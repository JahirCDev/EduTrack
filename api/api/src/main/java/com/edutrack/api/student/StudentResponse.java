package com.edutrack.api.student;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentResponse {
  private Long id;
  private String firstName;
  private String lastName;
  private String code;
  private String email; 
  private String photoUrl;
  private boolean active;
}
