package com.edutrack.api.group;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupRequest {
  @NotBlank(message = "El nombre del grupo es obligatorio")
  private String name;

  @NotBlank(message = "La materia es obligatoria")
  private String subject;

  @NotNull(message = "El período académico del grupo es obligatorio")
  private Long periodId;

  @NotNull(message = "El profesor del grupo es obligatorio")
  private Long teacherId;

}
