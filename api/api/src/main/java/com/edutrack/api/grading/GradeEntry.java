package com.edutrack.api.grading;
import com.edutrack.api.student.Student;
import java.math.BigDecimal;
import java.time.LocalDateTime;
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
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name="grade_entries")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GradeEntry {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "student_id", nullable = false)
  private Student student;

  @ManyToOne
  @JoinColumn(name = "component_id", nullable = false)
  private GradingComponent component;

  @Column(precision = 5, scale = 2, nullable = false)
  private BigDecimal value;

  @CreationTimestamp
  @Column(name = "recorded_at", nullable = false, updatable = false)
  private LocalDateTime recordedAt;

  @Column (columnDefinition = "BOOLEAN DEFAULT true")
  private Boolean active;
}
