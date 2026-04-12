package com.edutrack.api.grading;
import java.math.BigDecimal;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GradeEntryRequest {
  @NotNull(message = "El estudiante es obligatorio")
  private Long studentId;

  @NotNull(message = "El registro es obligatorio")
  private Long gradingComponentId;

  @NotNull(message = "El porcentaje de calificaciones es obligatorio")
  @DecimalMin(value = "0.0", message = "La nota mínima es 0")
  @DecimalMax(value = "100.0", message = "La nota máxima es 100")
  private BigDecimal value;
}
