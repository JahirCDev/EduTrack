package com.edutrack.api.teacher;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TeacherService implements UserDetailsService {
  private final TeacherRepository teacherRepository;

  public TeacherResponse create(TeacherRequest request){
    Teacher teacher = Teacher.builder()
    .firstName(request.getFirstName())
    .lastName(request.getLastName())
    .email(request.getEmail())
    .password(request.getPassword())
    .role(request.getRole())
    .active(true)
    .build();

    Teacher saved = teacherRepository.save(teacher);

    return TeacherResponse.builder()
    .id(saved.getId())
    .firstName(saved.getFirstName())
    .lastName(saved.getLastName())
    .email(saved.getEmail())
    .role(saved.getRole())
    .active(saved.getActive())
    .build();
  }

  public TeacherResponse findById(Long id) {
    Teacher teacher = teacherRepository.findById(id).orElseThrow(() -> new RuntimeException("Profesor no encontrado"));

    TeacherResponse response = TeacherResponse.builder()
    .id(teacher.getId())
    .firstName(teacher.getFirstName())
    .email(teacher.getEmail())
    .role(teacher.getRole())
    .active(teacher.getActive())
    .build();

    return response;
  }

  public List<TeacherResponse> findAll() {
    List<Teacher> teachers = teacherRepository.findAll();
    return teachers.stream()
                   .map(teacher -> TeacherResponse.builder()
                    .id(teacher.getId())
                    .firstName(teacher.getFirstName())
                    .lastName(teacher.getLastName())
                    .email(teacher.getEmail())
                    .role(teacher.getRole())
                    .active(teacher.getActive())
                    .build()
                  )
                   .collect(Collectors.toList());
    
  }  

  public TeacherResponse update(Long id, TeacherRequest request){
    Teacher teacher = teacherRepository.findById(id).orElseThrow(() -> new RuntimeException("Profesor no encontrado"));

    teacher.setFirstName(request.getFirstName());
    teacher.setLastName(request.getLastName());
    teacher.setEmail(request.getEmail());

    Teacher update = teacherRepository.save(teacher);

    return TeacherResponse.builder()
    .id(update.getId())
    .firstName(update.getFirstName())
    .lastName(update.getLastName())
    .email(update.getEmail())
    .role(update.getRole())
    .active(update.getActive())
    .build();
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
