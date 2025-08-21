package com.matheusoliveira04.picpaysimplificado.dto.request;

import com.matheusoliveira04.picpaysimplificado.validation.annotation.CpfCnpjFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    @NotBlank
    private String name;
    @NotBlank
    @CpfCnpjFormat
    private String cpfCnpj;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private String type;
    @NotNull
    @PositiveOrZero
    private BigDecimal walletBalance;

}
