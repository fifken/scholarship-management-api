package com.example.scholarship_management_api.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = OpenDateBeforeCloseDateValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface OpennDateBeforeCloseDate {
    String message() default "openDate must be before closeDate";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

//explore penggunaannya gmn