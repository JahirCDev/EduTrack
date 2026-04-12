package com.edutrack.api.attendance;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List;
import java.time.LocalDate;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotEmpty;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceSessionRequest {
  @NotNull(message = "El grupo es obligatorio")
  private Long groupId;

  @NotNull(message = "La fecha de sesión es obligatoria")
  private LocalDate sessionDate;

  @NotEmpty(message = "El registro de asistencia es obligatoria")
  private List<AttendanceRecordRequest> records;

  private String notes;
}

