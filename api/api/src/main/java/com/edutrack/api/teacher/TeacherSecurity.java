package com.edutrack.api.teacher;
import java.util.Optional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component("teacherSecurity")
public class TeacherSecurity {
  private final TeacherRepository teacherRepository;

  public boolean isSelf(Long id) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (auth == null || auth.getName() == null) {
      return false;
    }

    String email = auth.getName(); 
    Optional<Teacher> teacher = teacherRepository.findById(id);
    return teacher.isPresent() && email.equals(teacher.get().getEmail());
  }
}