package com.edutrack.api.grading;
import java.math.BigDecimal;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name="grade_components")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GradingComponent {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "grading_scheme_id", nullable = false)
  private GradingScheme gradingScheme;

  @Column(nullable = false)
  private String name;

  @Column(precision = 5, scale = 2, nullable = false)
  private BigDecimal percentage;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private ComponentType type;

  private Integer sortOrder;
}
