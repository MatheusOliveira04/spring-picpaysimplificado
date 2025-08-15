package com.matheusoliveira04.picpaysimplificado.validation.annotation;

import com.matheusoliveira04.picpaysimplificado.validation.CpfCnpjValidator;
import jakarta.validation.Constraint;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CpfCnpjValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface CpfCnpjFormat {
    String message() default "Invalid CPF or CNPJ format";
    Class<?>[] groups() default {};
    Class<? extends jakarta.validation.Payload>[] payload() default {};
}
