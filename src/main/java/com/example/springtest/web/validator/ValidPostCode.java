package com.example.springtest.web.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = PostCodeValidation.class)
@Target({ TYPE, FIELD, CONSTRUCTOR})
@Retention(RUNTIME)
@Documented
public @interface ValidPostCode {
    String message() default "Invalid postcode";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}