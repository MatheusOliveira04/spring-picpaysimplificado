package com.matheusoliveira04.picpaysimplificado.client;

import com.matheusoliveira04.picpaysimplificado.client.dto.AuthorizerClientResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "externalAuthorizer", url = "https://util.devi.tools/api/v2")
public interface ExternalAuthorizerClient {

    @GetMapping("/authorize")
    ResponseEntity<AuthorizerClientResponse> authorizeTransaction();
}
