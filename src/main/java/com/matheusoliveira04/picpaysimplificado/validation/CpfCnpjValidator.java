package com.matheusoliveira04.picpaysimplificado.validation;

import com.matheusoliveira04.picpaysimplificado.validation.annotation.CpfCnpjFormat;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CNPJValidator;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;
import org.springframework.util.ObjectUtils;

public class CpfCnpjValidator implements ConstraintValidator<CpfCnpjFormat, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (ObjectUtils.isEmpty(value)) return false;

        String digits = value.replaceAll("\\D", "");

        if (digits.length() == 11) {
            CPFValidator cpfValidator = new CPFValidator();
            cpfValidator.initialize(null);
            return cpfValidator.isValid(digits, context);
        }

        if (digits.length() == 14) {
            CNPJValidator cnpjValidator = new CNPJValidator();
            cnpjValidator.initialize(null);
            return cnpjValidator.isValid(digits, context);
        }

        return false;
    }
}
