package com.ftn.sbnz.service.dto.creation;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserCreationDTO {
    @NotBlank
    @Size(min = 3, max = 50)
    private String username;

    @NotBlank
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank
    @Size(min = 8, message = "Password must have 8 characters or longer.")
    @Size(max = 256, message = "Password must not be longer than 256 characters.")
    private String password;
}
