package com.example.springtest.web.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = CustomerDetailsValidator.class)
@Target({FIELD, METHOD, CONSTRUCTOR})
@Retention(RUNTIME)
@Documented
public @interface CustomerDetailsMatch {
    String message() default "Details do not match";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}