package com.edutrack.api.attendance;
import com.edutrack.api.group.Group;
import java.time.LocalDate;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name="attendance_sessions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttendanceSession {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "group_id", nullable = false)
  private Group group;

  @Column(nullable = false)
  private LocalDate sessionDate;

  private String notes;

  @Column (columnDefinition = "BOOLEAN DEFAULT true")
  private Boolean active;
}
