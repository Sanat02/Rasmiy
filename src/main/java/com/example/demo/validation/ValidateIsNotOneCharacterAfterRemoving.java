package com.example.demo.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy =IsNotOneCharacterAfterRemovingValidator.class)
public @interface ValidateIsNotOneCharacterAfterRemoving {
    public String message() default "The word that contains 2 character should not end with ь or ъ";

    Class<?>[] groups() default{};
    Class<? extends Payload>[] payload() default {};
}
