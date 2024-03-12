package com.example.demo.service;

import com.example.demo.enums.SklonenieType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.util.Locale;

@Service
@RequiredArgsConstructor
@Slf4j
public class AffixService {
    private final IlikService ilikService;
    private final BaryshService baryshService;
    private final TabyshService tabyshService;
    private final JatyshService jatyshService;
    private final ChygyshService chygyshService;
    public String addAffix(String text, SklonenieType sklonenieType) {
        text=text.trim();
        text=text.toLowerCase();
        String result = "";
        if (!isOneWord(text)) {
            return "You can put only one word to a gap";
        }
        if (!notStayOneCharacterAfterRemoving(text)) {
            return "The word that contains 2 character should not end with ь or ъ";
        }
        if (text.endsWith("ь") || text.endsWith("ъ")) {
            text = text.substring(0, text.length() - 1);


        }
        if (!isNotOneCharacter(text)) {
            return "The word should at least contain 2 characters";
        }
        if (!isNotAllVowellOrConsonant(text)) {
            return "Yor word contains only consonants or vowels!";
        }
        String ending=getEnding(text);
        String affixedText="";
        System.out.println(ending);
        switch (sklonenieType){
            case ILIK -> affixedText=ilikService.addIlikAffix(text,ending);
            case BARYSH -> affixedText=baryshService.addIlikAffix(text,ending);
            case TABYSH -> affixedText=tabyshService.addIlikAffix(text,ending);
            case JATYSH -> affixedText=jatyshService.addIlikAffix(text,ending);
            case CHYGYSH -> affixedText=chygyshService.addIlikAffix(text,ending);
        }
        //String affixedText=ilikService.addIlikAffix(text,ending);
        return affixedText;


    }

    private boolean isNotOneCharacter(String text) {
        if (text.length() <= 1) {
            return false;
        }
        return true;
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

    private boolean isOneWord(String text) {
        String trimmedText = text.trim();
        String[] arrStr = trimmedText.split("\\s+");
        if (arrStr.length == 1) {
            return true;
        }
        return false;
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
                    text.charAt(i) == 'з' || text.charAt(i) == 'с' || text.charAt(i) == 'ж' || text.charAt(i) == 'ш' || text.charAt(i) == 'ч' || text.charAt(i) == 'м' || text.charAt(i) == 'ь' || text.charAt(i) == 'ъ' ||text.endsWith("щ") ||
                    text.charAt(i) == 'н' || text.charAt(i) == 'ң' || text.charAt(i) == 'л' || text.charAt(i) == 'р' || text.charAt(i) == 'й' || text.charAt(i) == 'т' || text.charAt(i) == 'в' || text.charAt(i) == 'ф'||text.endsWith("ц")
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

    private String getEnding(String text) {
        String ending = "";
        char first = ' ';
        char second = ' ';
        if (text.endsWith("б") || text.endsWith("п") || text.endsWith("г") || text.endsWith("к") || text.endsWith("х") || text.endsWith("д") ||
                text.endsWith("з") || text.endsWith("с") || text.endsWith("ж") || text.endsWith("ш") || text.endsWith("ч") || text.endsWith("м") || text.endsWith("ь") || text.endsWith("ъ") ||text.endsWith("щ") ||text.endsWith("ц")||
        text.endsWith("н") || text.endsWith("ң") || text.endsWith("л") || text.endsWith("р") || text.endsWith("й") || text.endsWith("т") || text.endsWith("в") || text.endsWith("ф")) {
            second = text.charAt(text.length() - 1);
            for (int i = text.length() - 1; i >= 0; i--) {
                if (text.charAt(i) == 'а' || text.charAt(i) == 'э' || text.charAt(i) == 'ө' || text.charAt(i) == 'о' || text.charAt(i) == 'ь' || text.charAt(i) == 'ъ' ||
                        text.charAt(i) == 'ы' || text.charAt(i) == 'и' || text.charAt(i) == 'у' || text.charAt(i) == 'ү' || text.charAt(i) == 'е') {
                    first = text.charAt(i);
                    break;
                }
            }
        }else{
            second= text.charAt(text.length() - 1);
        }
        String result = "" + first + second;
        return result;
    }



}
