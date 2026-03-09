package com.edutrack.api.attendance;
import java.time.LocalDate;
import com.edutrack.api.group.Group;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name="attendace_sessions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttendanceSession {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "group_id", nullable = false, unique = true)
  private Group group;

  private LocalDate sessionDate;
  @PrePersist
  protected void onCreate() {
    this.sessionDate = LocalDate.now();
  }

  private String notes;
}
