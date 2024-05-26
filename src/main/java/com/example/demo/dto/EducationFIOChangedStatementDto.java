package com.example.demo.dto;

import com.example.demo.validation.ValidateConsonantVowel;
import com.example.demo.validation.ValidateIsNotOneCharacterAfterRemoving;
import com.example.demo.validation.ValidateLanguage;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class EducationFIOChangedStatementDto {
    @NotNull(message = "University ID shouldn't be null!")
    @Min(value = 1, message = "University ID should be greater than 0")
    private int universityId;  // I can input

    @Size(min = 2, message = "{validate.size}")
    @ValidateLanguage(message = "{validate.language}")
    @ValidateConsonantVowel(message = "{validate.consonant}")
    @ValidateIsNotOneCharacterAfterRemoving(message = "{validate.afterRem}")
    private String rectorF;

    @Size(min = 2, message = "{validate.size}")
    @ValidateLanguage(message = "{validate.language}")
    @ValidateConsonantVowel(message = "{validate.consonant}")
    @ValidateIsNotOneCharacterAfterRemoving(message = "{validate.afterRem}")
    private String rectorI;

    @ValidateLanguage(message = "{validate.language}")
    @ValidateConsonantVowel(message = "{validate.consonant}")
    @ValidateIsNotOneCharacterAfterRemoving(message = "{validate.afterRem}")
    private String rectorO;

    @NotNull(message = "{validate.notNull}")
    @NotBlank(message = "{validate.consonant}")
    @Pattern.List({
            @Pattern(regexp = "[1-5]", message = "Course should contain only numbers from 1 to 5")
    })
    private String course;   // I can input


    @Size(min = 2, message = "{validate.size}")
    @ValidateLanguage(message = "{validate.language}")
    @ValidateConsonantVowel(message = "{validate.consonant}")
    @ValidateIsNotOneCharacterAfterRemoving(message = "{validate.afterRem}")
    private String F;

    @Size(min = 2, message = "{validate.size}")
    @ValidateLanguage(message = "{validate.language}")
    @ValidateConsonantVowel(message = "{validate.consonant}")
    @ValidateIsNotOneCharacterAfterRemoving(message = "{validate.afterRem}")
    private String I;


    @ValidateLanguage(message = "{validate.language}")
    @ValidateConsonantVowel(message = "{validate.consonant}")
    @ValidateIsNotOneCharacterAfterRemoving(message = "{validate.afterRem}")
    private String O;

    @Size(min = 2, message = "{validate.size}")
    @ValidateLanguage(message = "{validate.language}")
    @ValidateConsonantVowel(message = "{validate.consonant}")
    @ValidateIsNotOneCharacterAfterRemoving(message = "{validate.afterRem}")
    private String newF;

    @Size(min = 2, message = "{validate.size}")
    @ValidateLanguage(message = "{validate.language}")
    @ValidateConsonantVowel(message = "{validate.consonant}")
    @ValidateIsNotOneCharacterAfterRemoving(message = "{validate.afterRem}")
    private String newI;


    @ValidateLanguage(message = "{validate.language}")
    @ValidateConsonantVowel(message = "{validate.consonant}")
    @ValidateIsNotOneCharacterAfterRemoving(message = "{validate.afterRem}")
    private String newO;


    @NotNull(message = "{validate.notNull}")
    @NotBlank(message = "{validate.notNull}")
    @ValidateLanguage(message = "{validate.language}")
    private String faculty; // I can input

    @NotNull(message = "{validate.notNull}")
    @NotBlank(message = "{validate.notNull}")
    @ValidateLanguage(message = "{validate.language}")
    private String group;


    private LocalDate filledDate;
}
