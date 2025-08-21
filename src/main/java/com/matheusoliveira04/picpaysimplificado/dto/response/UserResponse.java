package com.matheusoliveira04.picpaysimplificado.dto.response;

import java.math.BigDecimal;

public record UserResponse(
        String id,
        String name,
        String cpfCnpj,
        String email,
        String type,
        BigDecimal walletBalance
) {
}
