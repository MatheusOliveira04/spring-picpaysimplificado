package com.matheusoliveira04.picpaysimplificado.dto.request;

import com.matheusoliveira04.picpaysimplificado.validation.annotation.CpfCnpjFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRequest(
        @NotBlank String name,
        @NotBlank @CpfCnpjFormat String cpfCnpj,
        @NotBlank @Email String email,
        @NotBlank String password,
        @NotBlank String type
) {
}
