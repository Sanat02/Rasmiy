package com.example.demo.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy =IsNotOneCharacterValidator.class)
public @interface ValidateIsNotOneCharacter {
    public String message() default "The word should at least contain 2 characters!";

    Class<?>[] groups() default{};
    Class<? extends Payload>[] payload() default {};
}
