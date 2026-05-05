package com.edutrack.api.enrollment;
import com.edutrack.api.student.Student;
import com.edutrack.api.group.Group;
import java.time.LocalDate;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
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
@Table(name="enrollments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Enrollment {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name="student_id", nullable = false)
  private Student student;

  @ManyToOne
  @JoinColumn(name="group_id", nullable = false)
  private Group group;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private EnrollmentStatus status;

  private LocalDate enrolledAt;

  @PrePersist
  protected void onCreate(){
    this.enrolledAt = LocalDate.now();
  }
}
