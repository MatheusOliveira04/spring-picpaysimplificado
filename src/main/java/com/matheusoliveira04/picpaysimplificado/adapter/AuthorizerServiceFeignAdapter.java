package com.matheusoliveira04.picpaysimplificado.adapter;

import com.matheusoliveira04.picpaysimplificado.client.ExternalAuthorizerClient;
import com.matheusoliveira04.picpaysimplificado.client.dto.AuthorizerClientResponse;
import com.matheusoliveira04.picpaysimplificado.client.dto.AuthorizerDataResponse;
import com.matheusoliveira04.picpaysimplificado.exception.service.ObjectNotFoundException;
import com.matheusoliveira04.picpaysimplificado.service.AuthorizerService;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class AuthorizerServiceFeignAdapter implements AuthorizerService {

    private final ExternalAuthorizerClient client;

    public AuthorizerServiceFeignAdapter(ExternalAuthorizerClient client) {
        this.client = client;
    }

    @Override
    public boolean authorizeTransaction() {
        try {
            Map<String, Object> authorizationResponse = client.authorizeTransaction();

            return extracted(authorizationResponse);
        }
        catch (Exception e) {
            return !extracted(client.authorizeTransaction());
         }
    }

    private static boolean extracted(Map<String, Object> authorizationResponse) {
        if (authorizationResponse.containsKey("data")) {
            Map<String, Object> data = (Map<String, Object>) authorizationResponse.get("data");

            if (data.containsKey("authorization")) {
                boolean authorization = (boolean) data.get("authorization");
                return true;
            }
        }
        return false;
    }

    private static Boolean extractAuthorization(AuthorizerClientResponse authorizationResponse) {
        return Optional.of(authorizationResponse)
                .map(AuthorizerClientResponse::getData)
                .map(AuthorizerDataResponse::isAuthorized)
                .orElseThrow(() -> new ObjectNotFoundException("Authorization data not found"));
    }
}
