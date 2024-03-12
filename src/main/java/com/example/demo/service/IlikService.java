package com.example.demo.service;

import com.example.demo.model.Ilik;
import com.example.demo.repository.IlikGRepository;
import com.example.demo.repository.IlikRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class IlikService {
    private final IlikRepository repository;
    private final IlikGRepository repositoryG;

    public String addIlikAffix(String wholeText, String text) {
        String affix = "";
        text=text.trim();
        String result="";

        if (text.endsWith("а") || text.endsWith("э") || text.endsWith("ө") || text.endsWith("о") ||
                text.endsWith("ы") || text.endsWith("и") || text.endsWith("у") || text.endsWith("ү") || text.endsWith("е")) {
            affix = repositoryG.findIlikGByEnding(text).orElseThrow().getAffix();
            result = wholeText + affix;
            return result;
        }

        affix = repository.findIlikByEnding(text).orElseThrow().getAffix();
         result = wholeText + affix;
        return result;

    }
}
