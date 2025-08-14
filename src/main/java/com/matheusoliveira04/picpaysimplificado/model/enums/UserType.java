package com.matheusoliveira04.picpaysimplificado.model.enums;

import java.util.Arrays;

public enum UserType {
    MERCHANT("Merchant"),
    COMMON("Common");

    private final String description;

    UserType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static UserType fromDescription(String description) {
        return Arrays.stream(UserType.values())
                .filter(type -> type.description.equals(description))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("User type not found: " + description));
    }
}
