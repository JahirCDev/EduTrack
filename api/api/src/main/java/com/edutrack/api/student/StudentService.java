package com.edutrack.api.student;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentService{
  private final StudentRepository studentRepository;

  private StudentResponse toResponse(Student student) {
    return StudentResponse.builder()
    .id(student.getId())
    .firstName(student.getFirstName())
    .lastName(student.getLastName())
    .email(student.getEmail())
    .code(student.getCode())
    .photoUrl(student.getPhotoUrl())
    .active(student.getActive())
    .build();
  }

  public StudentResponse create(StudentRequest request){
    Student student = studentRepository.save(Student.builder()
      .firstName(request.getFirstName())
      .lastName(request.getLastName())
      .code(request.getCode())
      .email(request.getEmail())
      .photoUrl(request.getPhotoUrl())
      .active(true)
      .build()
    );

    return toResponse(student);
  }

  public StudentResponse findById(Long id){
    Student student = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
    return toResponse(student);
  }

  public List<StudentResponse> findAll() {
    List<Student> students = studentRepository.findAll();
    return students.stream().map(this::toResponse).collect(Collectors.toList());
  }

  public StudentResponse update(Long id, StudentRequest request) {
    Student student = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

    student.setFirstName(request.getFirstName());
    student.setLastName(request.getLastName());
    student.setEmail(request.getEmail());
    student.setPhotoUrl(request.getPhotoUrl());
    return toResponse(studentRepository.save(student));
  }

  public void delete(Long id) {
    Student student = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

    student.setActive(false);;
    studentRepository.save(student);
  }
}
