package com.edutrack.api.grading;
import java.math.BigDecimal;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name="grading_components")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GradingComponent {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "grading_scheme_id", nullable = false, unique = true)
  private GradingScheme gradingScheme;

  @Column(nullable = false)
  private String name;

  @Column(precision = 5, scale = 2, nullable = false)
  private BigDecimal percentage;

  private Integer sortOrder;
}
