package com.example.demo.controllersMvc;

import com.example.demo.dto.EducationRecoveryStatementDto;
import com.example.demo.model.Statement;
import com.example.demo.repository.ReasonRepository;
import com.example.demo.repository.UniversityRepository;
import com.example.demo.service.StatementsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Controller
@RequestMapping("/form")
@RequiredArgsConstructor
@Slf4j
public class FormController {
    private final UniversityRepository universityRepository;
    private final ReasonRepository reasonRepository;
    private final StatementsService statementsService;

    @GetMapping("/education/recovery")
    public String getFormPage(Model model) {
        model.addAttribute("universities", universityRepository.findAll());
        model.addAttribute("reasons", reasonRepository.findAll());
        Locale currentLocale = LocaleContextHolder.getLocale();
        String languageCode = currentLocale.getLanguage();
        System.out.println(languageCode);

        model.addAttribute("lang", languageCode);
        return "forms";
    }

    @PostMapping("/education/recovery")
    public String generateRecovery(@Valid EducationRecoveryStatementDto educationRecoveryStatementDto, BindingResult result, Model model) {
        // If there are validation errors, send error messages for each field back to the form page
        model.addAttribute("universities", universityRepository.findAll());
        model.addAttribute("reasons", reasonRepository.findAll());
        model.addAttribute("lang", LocaleContextHolder.getLocale().getLanguage());
        if (result.hasErrors()) {

            // Create a map to hold field error messages
            Map<String, String> fieldErrors = new HashMap<>();
            for (FieldError error : result.getFieldErrors()) {
                fieldErrors.put(error.getField(), error.getDefaultMessage());
            }
            model.addAttribute("fieldErrors", fieldErrors);

            return "forms";
        }

        // If there are no validation errors, proceed with the service logic
        //add id
        Statement statement=statementsService.createEducationRecoveryStatement(educationRecoveryStatementDto);
        return "redirect:/form/success/"+statement.getId();
    }

    @GetMapping("/success/{reportId}")
    public String getReport(Model model,@PathVariable int reportId){
        model.addAttribute("statement",statementsService.getStatementById(reportId));
        return "document";
    }

}
