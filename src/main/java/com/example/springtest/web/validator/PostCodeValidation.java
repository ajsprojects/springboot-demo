package com.example.springtest.web.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PostCodeValidation
        implements ConstraintValidator<ValidPostCode, String> {

    @Override
    public boolean isValid(String postCode, ConstraintValidatorContext context) {

        if (postCode == null) {
            return false;
        }

        return true;
    }
}