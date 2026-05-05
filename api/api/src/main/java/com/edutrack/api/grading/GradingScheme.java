package com.edutrack.api.grading;
import com.edutrack.api.group.Group;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
@Table(name="grading_schemes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GradingScheme {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne
  @JoinColumn(name = "group_id", nullable = false, unique = true)
  private Group group;

  @Column (columnDefinition = "BOOLEAN DEFAULT true")
  private Boolean active;
}
