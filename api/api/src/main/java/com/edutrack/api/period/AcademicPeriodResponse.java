package com.edutrack.api.period;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import lombok.AllArgsConstructor;

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
