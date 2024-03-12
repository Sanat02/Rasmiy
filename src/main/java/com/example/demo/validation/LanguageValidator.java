package com.example.demo.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class LanguageValidator implements ConstraintValidator<ValidateLanguage,String> {
    @Override
    public boolean isValid(String language, ConstraintValidatorContext constraintValidatorContext) {
        return language.matches("^[А-Яа-яӨөҮүҢң\\s]*$");

    }
}
