package com.matheusoliveira04.picpaysimplificado.adapter;

import com.matheusoliveira04.picpaysimplificado.client.ExternalAuthorizerClient;
import com.matheusoliveira04.picpaysimplificado.client.dto.AuthorizerClientResponse;
import com.matheusoliveira04.picpaysimplificado.client.dto.AuthorizerDataResponse;
import com.matheusoliveira04.picpaysimplificado.exception.service.IntegrityViolationException;
import com.matheusoliveira04.picpaysimplificado.exception.service.ObjectNotFoundException;
import com.matheusoliveira04.picpaysimplificado.service.AuthorizerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorizerServiceFeignAdapter implements AuthorizerService {

    private final ExternalAuthorizerClient client;

    public AuthorizerServiceFeignAdapter(ExternalAuthorizerClient client) {
        this.client = client;
    }

    @Override
    public boolean authorizeTransaction() {
        ResponseEntity<AuthorizerClientResponse> authorizationResponse = client.authorizeTransaction();
        validateStatusCodeOk(authorizationResponse);
        return extractAuthorization(authorizationResponse);
    }

    private void validateStatusCodeOk(ResponseEntity<AuthorizerClientResponse> authorizationResponse) {
        if (!HttpStatus.OK.equals(authorizationResponse.getStatusCode())) {
            throw new IntegrityViolationException("Failed to authorize transaction");
        }
    }

    private static Boolean extractAuthorization(ResponseEntity<AuthorizerClientResponse> authorizationResponse) {
        return Optional.of(authorizationResponse)
                .map(ResponseEntity::getBody)
                .map(AuthorizerClientResponse::getData)
                .map(AuthorizerDataResponse::isAuthorized)
                .orElseThrow(() -> new ObjectNotFoundException("Authorization data not found"));
    }
}
