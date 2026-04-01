package com.edutrack.api.student;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentRequest {
  @NotBlank(message = "El nombre del estudiante es obligatorio")
  private String firstName;  

  @NotBlank(message = "El apellido del estudiante es obligatorio")
  private String lastName;

  @NotBlank(message = "El código del estudiante es obligatorio")
  private String code;

  @NotBlank(message = "El correo del estudiante es obligatorio")
  private String email;

  private String photoUrl;
}
