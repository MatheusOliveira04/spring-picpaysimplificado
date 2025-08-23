package com.matheusoliveira04.picpaysimplificado.client.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthorizerClientResponse {
    private AuthorizerDataResponse data;
}


