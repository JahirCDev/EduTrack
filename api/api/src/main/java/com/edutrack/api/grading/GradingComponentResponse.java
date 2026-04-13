package com.edutrack.api.grading;
import java.math.BigDecimal;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GradingComponentResponse {
  private Long id;
  private Long schemeId;
  private String name;
  private BigDecimal percentage;
  private ComponentType type;
}
