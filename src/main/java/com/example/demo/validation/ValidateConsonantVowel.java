package com.example.demo.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ConsonantVowelValidator.class)
public @interface ValidateConsonantVowel {
    public String message() default "Your word contains only consonants or vowels!";

    Class<?>[] groups() default{};
    Class<? extends Payload>[] payload() default {};
}
