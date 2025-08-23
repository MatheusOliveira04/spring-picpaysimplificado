package com.matheusoliveira04.picpaysimplificado.exception.controller;

import java.time.LocalDateTime;
import java.util.List;

public record StandardError(
        LocalDateTime timestamp,
        Integer status,
        String uri,
        List<String> messages
) {
}
