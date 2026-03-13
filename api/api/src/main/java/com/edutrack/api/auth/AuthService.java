package com.edutrack.api.auth;
import com.edutrack.api.config.JwtService;
import com.edutrack.api.teacher.Teacher;
import com.edutrack.api.teacher.TeacherRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final TeacherRepository teacherRepository;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  public AuthResponse login(AuthRequest request) {
    authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(
        request.getEmail(),
        request.getPassword()
      )
    );

    Teacher teacher = teacherRepository.findByEmail(request.getEmail()).orElseThrow(() -> new UsernameNotFoundException("Profesor no encontrado"));
    String token = jwtService.generateToken(teacher);

    return AuthResponse.builder()
    .token(token)
    .build();

  }
}