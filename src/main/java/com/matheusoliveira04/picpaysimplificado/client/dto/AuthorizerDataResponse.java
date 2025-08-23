package com.matheusoliveira04.picpaysimplificado.client.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorizerDataResponse {
    private boolean authorization;

    public boolean isAuthorized() {
        return authorization;
    }
}
