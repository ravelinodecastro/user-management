package pt.uevora.user_management.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRequestDTO {

    @Email(message = "O email deve ser válido")
    @NotBlank
    private String email;

    @NotBlank
    private String name;
}