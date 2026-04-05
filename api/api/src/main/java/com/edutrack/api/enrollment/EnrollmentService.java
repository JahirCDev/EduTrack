package com.edutrack.api.enrollment;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.edutrack.api.group.Group;
import com.edutrack.api.group.GroupRepository;
import com.edutrack.api.student.Student;
import com.edutrack.api.student.StudentRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EnrollmentService {
  private final EnrollmentRepository enrollmentRepository;
  private final StudentRepository studentRepository;
  private final GroupRepository groupRepository;

  private EnrollmentResponse toResponse(Enrollment enrollment){
    return EnrollmentResponse.builder()
    .id(enrollment.getId())
    .student(StudentSummary.builder()
      .id(enrollment.getStudent().getId())
      .firstName(enrollment.getStudent().getFirstName())
      .lastName(enrollment.getStudent().getLastName())
      .email(enrollment.getStudent().getEmail())
      .build()
    )
    .group(GroupSummary.builder()
      .id(enrollment.getGroup().getId())
      .name(enrollment.getGroup().getName())
      .teacherName(enrollment.getGroup().getTeacher().getFirstName() + " " + enrollment.getGroup().getTeacher().getLastName())
      .build()
    )
    .status(enrollment.getStatus())
    .enrolledAt(enrollment.getEnrolledAt())
    .build();
  }

  public EnrollmentResponse create(EnrollmentRequest request) {
    Student student = studentRepository.findById(request.getStudentId()).orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

    Group group = groupRepository.findById(request.getGroupId()).orElseThrow(() -> new RuntimeException("Grupo no encontrado"));

    Enrollment enrollment = Enrollment.builder()
    .student(student)
    .group(group)
    .status(request.getStatus())
    .build();

    Enrollment saved = enrollmentRepository.save(enrollment);
    return toResponse(saved);
  }

  public EnrollmentResponse findById(Long id) {
    Enrollment enrollment = enrollmentRepository.findById(id).orElseThrow(() -> new RuntimeException("Registro no encontrado"));
    return toResponse(enrollment);
  }

  public List<EnrollmentResponse> findAll(){
    List<Enrollment> enrollments = enrollmentRepository.findAll();
    return enrollments.stream().map(this::toResponse).collect(Collectors.toList());
  }

  public EnrollmentResponse update(Long id, EnrollmentRequest request) {
    Enrollment enrollment = enrollmentRepository.findById(id).orElseThrow(() -> new RuntimeException("Registro no encontrado"));

    Student student = studentRepository.findById(request.getStudentId()).orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

    Group group = groupRepository.findById(request.getGroupId()).orElseThrow(() -> new RuntimeException("Grupo no encontrado"));

    enrollment.setGroup(group);
    enrollment.setStudent(student);
    enrollment.setStatus(request.getStatus());

    Enrollment update = enrollmentRepository.save(enrollment);
    return toResponse(update);
  }

  public void delete(Long id){
    Enrollment enrollment = enrollmentRepository.findById(id).orElseThrow(() -> new RuntimeException("Registro no encontrado"));
    enrollment.setStatus(EnrollmentStatus.DROPPED);
    enrollmentRepository.save(enrollment);
  }
}