package com.edutrack.api.student;
import jakarta.persistence.Id;
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
@Table(name="students")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String firstName;
  
  @Column(nullable = false)
  private String lastName;

  @Column(nullable = false, unique = true)
  private String code;

  @Column(nullable = false, unique = true)
  private String email;

  private String photoUrl;

  @Column (columnDefinition = "BOOLEAN DEFAULT true")
  private Boolean active;
}
