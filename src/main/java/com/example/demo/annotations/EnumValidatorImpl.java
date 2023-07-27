package com.example.demo.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EnumValidatorImpl implements ConstraintValidator<EnumValidator, String> {

    private Class<? extends Enum<?>> enumClass;

    @Override
    public void initialize(EnumValidator constraintAnnotation) {
        enumClass = constraintAnnotation.enumClass();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        try {
            String uppercaseValue = value.toUpperCase();
            Enum<?>[] enumValues = enumClass.getEnumConstants();
            for (Enum<?> enumValue : enumValues) {
                if (enumValue.name().equals(uppercaseValue)) {
                    return true;
                }
            }
        } catch (Exception e) {

        }

        return false;
    }
}
