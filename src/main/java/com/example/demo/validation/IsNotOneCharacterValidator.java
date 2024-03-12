package com.example.demo.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class IsNotOneCharacterValidator implements ConstraintValidator<ValidateIsNotOneCharacter,String>  {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return isNotOneCharacter(s);
    }
    private boolean isNotOneCharacter(String text) {
        if (text.length() <= 1) {
            return false;
        }
        return true;
    }
}
