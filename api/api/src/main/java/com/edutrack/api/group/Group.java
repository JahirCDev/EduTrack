package com.edutrack.api.group;
import com.edutrack.api.teacher.Teacher;
import com.edutrack.api.period.AcademicPeriod;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name="groups")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Group {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String name;

  private String subject;

  @ManyToOne
  @JoinColumn(name="period_id", nullable = false)
  private AcademicPeriod period;

  @ManyToOne
  @JoinColumn(name="teacher_id", nullable = false)
  private Teacher teacher;

  @Column (columnDefinition = "BOOLEAN DEFAULT true")
  private Boolean active;
}
