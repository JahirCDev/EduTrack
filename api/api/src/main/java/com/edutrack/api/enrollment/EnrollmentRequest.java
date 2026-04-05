package com.edutrack.api.enrollment;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnrollmentRequest {
  @NotNull(message = "El estudiante es obligatorio")
  private Long studentId;

  @NotNull(message = "El grupo es obligatorio")
  private Long groupId;

  @NotNull(message = "El estado es obligatorio")
  private EnrollmentStatus status;  
}
