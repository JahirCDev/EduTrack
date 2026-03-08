package com.edutrack.api.period;
import java.time.LocalDate;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name="academic_periods")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AcademicPeriod {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;

  @Column(nullable=false)
  private String name;

  @Column(nullable=false)
  private LocalDate startDate;

  @Column(nullable=false)
  private LocalDate endDate;

  @Column (columnDefinition = "BOOLEAN DEFAULT true")
  private Boolean active;
}
