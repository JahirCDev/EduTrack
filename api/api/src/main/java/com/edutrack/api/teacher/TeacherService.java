package com.edutrack.api.teacher;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TeacherService implements UserDetailsService {
  private final TeacherRepository teacherRepository;
  private final PasswordEncoder passwordEncoder;

  private TeacherResponse toResponse(Teacher teacher){
    return TeacherResponse.builder()
    .id(teacher.getId())
    .firstName(teacher.getFirstName())
    .lastName(teacher.getLastName())
    .email(teacher.getEmail())
    .role(teacher.getRole())
    .active(teacher.getActive())
    .build();
  }

  public TeacherResponse create(TeacherRequest request){
    Teacher teacher = teacherRepository.save(Teacher.builder()
    .firstName(request.getFirstName())
    .lastName(request.getLastName())
    .email(request.getEmail())
    .password(passwordEncoder.encode(request.getPassword()))
    .role(request.getRole())
    .active(true)
    .build());
    return toResponse(teacher);
  }

  public TeacherResponse findById(Long id) {
    Teacher teacher = teacherRepository.findById(id).orElseThrow(() -> new RuntimeException("Profesor no encontrado"));
    return toResponse(teacher);
  }

  public List<TeacherResponse> findAll() {
    List<Teacher> teachers = teacherRepository.findAll();
    return teachers.stream().map(this::toResponse).collect(Collectors.toList());
  }  

  public TeacherResponse update(Long id, TeacherRequest request){
    Teacher teacher = teacherRepository.findById(id).orElseThrow(() -> new RuntimeException("Profesor no encontrado"));

    teacher.setFirstName(request.getFirstName());
    teacher.setLastName(request.getLastName());
    teacher.setEmail(request.getEmail());
    return toResponse(teacherRepository.save(teacher));
  }

  public void delete(Long id) {
    Teacher teacher = teacherRepository.findById(id).orElseThrow(() -> new RuntimeException("Profesor no encontrado"));
    teacher.setActive(false);
    teacherRepository.save(teacher);
  }

  @Override
  public UserDetails loadUserByUsername(String username) {
    return teacherRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Profesor no encontrado"));
  }
}
