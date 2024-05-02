package com.example.demo.controllersMvc;

import com.example.demo.service.DocumentService;
import com.example.demo.service.SubDocumentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Locale;

@Controller
@RequestMapping("/dashboard")
@RequiredArgsConstructor
@Slf4j
public class DashBoardController {
    private final DocumentService documentService;
    private final SubDocumentService subDocumentService;
    @GetMapping()
    public String getDocuments(Model model) {
       model.addAttribute("Documents",documentService.getAllDocuments());
        Locale currentLocale = LocaleContextHolder.getLocale();

        // Get language code
        String languageCode = currentLocale.getLanguage();
        System.out.println(languageCode);

        model.addAttribute("lang",languageCode );
        return "dashboard";
    }

    @GetMapping("/docs/{documentId}")
    public String getSubDocuments(Model model, @PathVariable int documentId) {
        model.addAttribute("SubDocs",subDocumentService.getSubDocsByDocumentId(documentId));
        String lung="";
        Locale currentLocale = LocaleContextHolder.getLocale();

        String languageCode = currentLocale.getLanguage();

        model.addAttribute("lang",languageCode );
        model.addAttribute("document",documentService.getDocumentById(documentId));
        return "subDocuments";
    }
}
