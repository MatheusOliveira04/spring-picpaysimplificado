package com.matheusoliveira04.picpaysimplificado.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@FeignClient(
        name = "externalAuthorizer",
        url = "https://util.devi.tools/api/v2"
)
public interface ExternalAuthorizerClient {

    @GetMapping("/authorize")
    Map<String, Object> authorizeTransaction();
}
