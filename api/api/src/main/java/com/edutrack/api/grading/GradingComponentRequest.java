package com.edutrack.api.grading;
import java.math.BigDecimal;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GradingComponentRequest {
  private Long id;

  @NotBlank(message = "El nombre es obligatorio")
  private String name;

  @NotNull(message = "El porcentaje de calificaciones es obligatorio")
  private BigDecimal percentage;

  @NotNull(message = "El tipo de calificacion es obligatorio")
  private ComponentType type;  
}
