package com.edutrack.api.auth;
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
public class AuthRequest {

  @Email(message = "Correo inválido")
  @NotBlank(message = "El correo es obligatorio")  
  private String email;

  @Size(min = 8, message = "La contraseña debe tener un mínimo de 8 carácteres")
  @NotBlank(message = "La contraseña es obligatoria")
  private String password;

}

