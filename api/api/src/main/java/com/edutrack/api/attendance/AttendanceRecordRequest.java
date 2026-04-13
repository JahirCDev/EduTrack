package com.edutrack.api.attendance;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceRecordRequest {
  private Long id;

  @NotNull(message = "El estudiante es obligatorio")
  private Long studentId;

  @NotNull(message = "El estado es obligatorio")
  private AttendanceStatus status;

  private String notes;
}
