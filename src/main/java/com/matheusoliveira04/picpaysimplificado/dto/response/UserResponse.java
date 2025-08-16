package com.matheusoliveira04.picpaysimplificado.dto.response;

public record UserResponse(
        String id,
        String name,
        String cpfCnpj,
        String email,
        String type
) {
}
