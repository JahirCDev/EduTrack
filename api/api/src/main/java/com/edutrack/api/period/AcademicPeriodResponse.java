package com.edutrack.api.period;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AcademicPeriodResponse {
  private Long id;
  private String name;
  private LocalDate startDate;
  private LocalDate endDate; 
  private Boolean active;
}
