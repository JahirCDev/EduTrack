package com.edutrack.api.grading;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.math.BigDecimal;

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
