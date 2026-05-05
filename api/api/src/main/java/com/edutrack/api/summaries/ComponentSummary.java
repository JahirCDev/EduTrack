package com.edutrack.api.summaries;
import com.edutrack.api.grading.ComponentType;
import java.math.BigDecimal;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ComponentSummary {
  private Long id;
  private String name;
  private BigDecimal percentage;
  private ComponentType type;
}
