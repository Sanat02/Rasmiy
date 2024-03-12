package com.example.demo.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Locale;

public class ConsonantVowelValidator implements ConstraintValidator<ValidateConsonantVowel, String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return isNotAllVowellOrConsonant(s);
    }

    private boolean isNotAllVowellOrConsonant(String text) {
        int vowelsCount = 0;
        int consonantCount = 0;
        int iodCount = 0;
        text = text.toLowerCase(Locale.ROOT);
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == 'а' || text.charAt(i) == 'э' || text.charAt(i) == 'ө' || text.charAt(i) == 'о' || text.charAt(i) == 'ь' || text.charAt(i) == 'ъ' ||
                    text.charAt(i) == 'ы' || text.charAt(i) == 'и' || text.charAt(i) == 'у' || text.charAt(i) == 'ү' || text.charAt(i) == 'е') {
                vowelsCount++;
            }
            if (text.charAt(i) == 'б' || text.charAt(i) == 'п' || text.charAt(i) == 'г' || text.charAt(i) == 'к' || text.charAt(i) == 'х' || text.charAt(i) == 'д' ||
                    text.charAt(i) == 'з' || text.charAt(i) == 'с' || text.charAt(i) == 'ж' || text.charAt(i) == 'ш' || text.charAt(i) == 'ч' || text.charAt(i) == 'м' || text.charAt(i) == 'ь' || text.charAt(i) == 'ъ' || text.endsWith("щ") ||
                    text.charAt(i) == 'н' || text.charAt(i) == 'ң' || text.charAt(i) == 'л' || text.charAt(i) == 'р' || text.charAt(i) == 'й' || text.charAt(i) == 'т' || text.charAt(i) == 'в' || text.charAt(i) == 'ф' || text.endsWith("ц")
            ) {
                consonantCount++;
            }
            if (text.charAt(i) == 'ю' || text.charAt(i) == 'я' || text.charAt(i) == 'ь' || text.charAt(i) == 'ъ') {
                iodCount++;
            }
        }
        if (vowelsCount == text.length() || consonantCount == text.length() || iodCount == text.length()) {
            return false;
        }
        return true;

    }
}
