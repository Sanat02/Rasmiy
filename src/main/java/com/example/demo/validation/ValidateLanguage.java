package com.example.demo.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = LanguageValidator.class)
public @interface ValidateLanguage {

    public String message() default "Input should be only in kyrgyz/russian letters!";

    Class<?>[] groups() default{};
    Class<? extends Payload>[] payload() default {};
}
