package com.edutrack.api.grading;
import java.util.List;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotEmpty;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GradingSchemeRequest {
  @NotNull(message = "El grupo es obligatorio")
  private Long groupId;  

  @NotEmpty(message = "El registro es obligatorio")
  private List<GradingComponentRequest> components;
}
