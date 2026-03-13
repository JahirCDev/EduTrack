package com.edutrack.api.teacher;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeacherRequest {

  @NotBlank(message = "El nombre es obligatorio")  
  private String firstName;

  @NotBlank(message = "El apellido es obligatorio")  
  private String lastName;

  @Email(message = "Correo inválido")
  @NotBlank(message = "El correo es obligatorio")  
  private String email;

  @Size(min = 8, message = "La contraseña debe tener un mínimo de 8 carácteres")
  private String password;
  private Role role;
}
