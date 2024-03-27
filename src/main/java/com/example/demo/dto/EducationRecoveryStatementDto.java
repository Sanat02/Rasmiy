package com.example.demo.dto;

import com.example.demo.validation.ValidateConsonantVowel;
import com.example.demo.validation.ValidateIsNotOneCharacterAfterRemoving;
import com.example.demo.validation.ValidateLanguage;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class EducationRecoveryStatementDto {

    @NotNull(message = "University ID shouldn't be null!")
    @Min(value = 1, message = "University ID should be greater than 0")
    private int universityId;

    @NotNull(message = "University ID shouldn't be null!")
    @NotBlank(message = "Rector shouldn't be null or empty!")
    @Pattern(regexp = "^[А-Яа-яӨөҮүҢң\\s]*$", message = "Field should contain only one word")
    @ValidateLanguage(message = "Rector letters should be only kyrgyz/russian!")
    @ValidateConsonantVowel(message = "Your word contains only consonants or vowels")
    @ValidateIsNotOneCharacterAfterRemoving(message = "The word that contains 2 character should not end with ь or ъ")
    private String rectorF;

    @NotNull(message = "University ID shouldn't be null!")
    @NotBlank(message = "Rector shouldn't be null or empty!")
    @Pattern(regexp = "^[А-Яа-яӨөҮүҢң\\s]*$", message = "Field should contain only one word")
    @ValidateLanguage(message = "Rector letters should be only kyrgyz/russian!")
    @ValidateConsonantVowel(message = "Your word contains only consonants or vowels")
    @ValidateIsNotOneCharacterAfterRemoving(message = "The word that contains 2 character should not end with ь or ъ")
    private String rectorI;

    @NotNull(message = "University ID shouldn't be null!")
    @NotBlank(message = "Rector shouldn't be null or empty!")
    @Pattern(regexp = "^[А-Яа-яӨөҮүҢң\\s]*$", message = "Field should contain only one word")
    @ValidateLanguage(message = "Rector letters should be only kyrgyz/russian!")
    @ValidateConsonantVowel(message = "Your word contains only consonants or vowels")
    @ValidateIsNotOneCharacterAfterRemoving(message = "The word that contains 2 character should not end with ь or ъ")
    private String rectorO;

    @NotNull(message = "University ID shouldn't be null!")
    @NotBlank(message = "Course shouldn't be null or empty!")
    @Pattern.List({
            @Pattern(regexp = "[1-5]", message = "Course should contain only numbers from 1 to 5")
    })
    private String course;   // I can input


    @NotBlank(message = "F shouldn't be null or empty!")
    @Pattern(regexp = "^[А-Яа-яӨөҮүҢң\\s]*$", message = "Field should contain only one word")
    @ValidateLanguage(message = "FIO letters should be only kyrgyz/russian!")
    @ValidateConsonantVowel(message = "Your word contains only consonants or vowels")
    @ValidateIsNotOneCharacterAfterRemoving(message = "The word that contains 2 character should not end with ь or ъ")
    private String F;

    @NotNull(message = "University ID shouldn't be null!")
    @Pattern(regexp = "^[А-Яа-яӨөҮүҢң\\s]*$", message = "Field should contain only one word")
    @ValidateLanguage(message = "FIO letters should be only kyrgyz/russian!")
    @ValidateConsonantVowel(message = "Your word contains only consonants or vowels")
    @ValidateIsNotOneCharacterAfterRemoving(message = "The word that contains 2 character should not end with ь or ъ")
    @NotBlank(message = "FIO shouldn't be null or empty!")
    private String I;

    @NotNull(message = "University ID shouldn't be null!")
    @NotBlank(message = "F shouldn't be null or empty!")
    @Pattern(regexp = "^[А-Яа-яЁё]+$", message = "Field should contain only one word")
    @ValidateLanguage(message = "FIO letters should be only kyrgyz/russian!")
    @ValidateConsonantVowel(message = "Your word contains only consonants or vowels")
    @ValidateIsNotOneCharacterAfterRemoving(message = "The word that contains 2 character should not end with ь or ъ")
    private String O;

    @NotNull(message = "University ID shouldn't be null!")
    @NotBlank(message = "Faculty shouldn't be null or empty!")
    @ValidateLanguage(message = "Faculty letters should be only kyrgyz/russian!")
    private String faculty; // I can input

//    @Pattern(regexp = "^[А-Яа-яӨөҮүҢң\\s]*$", message = "Field should contain only one word")
//    @ValidateLanguage(message = "FIO letters should be only kyrgyz/russian!")
//    @ValidateConsonantVowel(message = "Your word contains only consonants or vowels")
//    @ValidateIsNotOneCharacter(message="The word should at least contain 2 characters")
//    @ValidateIsNotOneCharacterAfterRemoving(message = "The word that contains 2 character should not end with ь or ъ")
//    @NotBlank(message = "F shouldn't be null or empty!")
//    private String group;

    @NotNull(message = "University ID shouldn't be null!")
    @NotBlank(message = "Reason shouldn't be null or empty!")
    @ValidateLanguage(message = "Reason letters should be only kyrgyz/russian!")
    private String reason; // I can input


    private LocalDate filledDate;
}
