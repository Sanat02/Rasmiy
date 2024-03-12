package com.example.demo.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class IsNotOneCharacterAfterRemovingValidator implements ConstraintValidator <ValidateIsNotOneCharacterAfterRemoving,String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return notStayOneCharacterAfterRemoving(s);
    }
    private boolean notStayOneCharacterAfterRemoving(String text) {
        int flag = 0;
        if (text.endsWith("ь") || text.endsWith("ъ")) {
            flag = 1;
        }

        if (text.length() == 2 && flag == 1) {
            return false;
        }
        return true;
    }

}
