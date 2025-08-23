package com.matheusoliveira04.picpaysimplificado.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransferRequest {
    @PositiveOrZero @NotNull
    private BigDecimal value;
    @NotBlank @NotNull
    private String payerId;
    @NotBlank @NotNull
    private String payeeId;
}
