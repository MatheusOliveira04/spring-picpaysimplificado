package com.matheusoliveira04.picpaysimplificado.adapter;

import com.matheusoliveira04.picpaysimplificado.client.ExternalAuthorizerClient;
import com.matheusoliveira04.picpaysimplificado.service.AuthorizerService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AuthorizerServiceFeignAdapter implements AuthorizerService {

    private final ExternalAuthorizerClient client;

    public AuthorizerServiceFeignAdapter(ExternalAuthorizerClient client) {
        this.client = client;
    }

    @Override
    public boolean authorizeTransaction() {
        Map<String, Object> response = client.authorizeTransaction();
        if (response != null && response.containsKey("data")) {
            Map<String, Object> data = (Map<String, Object>) response.get("data");
            if (data.containsKey("authorization")) {
                return (boolean) data.get("authorization");
            }
        }
        throw new RuntimeException("Failed to authorize transaction");
    }
}
