package com.example.demo.controllersMvc;

import com.example.demo.dto.EducationExtensionCrWeekStatementDto;
import com.example.demo.dto.EducationRecoveryStatementDto;
import com.example.demo.dto.EducationRestorationDocDto;
import com.example.demo.dto.EducationWithdrawalStatementDto;
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

import java.time.LocalDate;
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

    @GetMapping("/education/st/{id}")
    public String getSt(@PathVariable int id) {
        if (id == 1) {
            return "redirect:/form/education/recovery";
        } else if (id == 2) {
            return "redirect:/form/education/withdrawal";
        } else if (id == 3) {
            return "redirect:/form/education/extension";
        } else {
            return "redirect:/form/education/restoration"; //5
        }
    }


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
        educationRecoveryStatementDto.setFilledDate(LocalDate.now());
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
        Statement statement = statementsService.createEducationRecoveryStatement(educationRecoveryStatementDto);
        return "redirect:/form/success/" + statement.getId();
    }

    @GetMapping("/success/{reportId}")
    public String getReport(Model model, @PathVariable int reportId) {
        model.addAttribute("statement", statementsService.getStatementById(reportId));
        return "document";
    }

    @GetMapping("/education/extension")
    public String getFormPageExtension(Model model) {
        model.addAttribute("universities", universityRepository.findAll());
        model.addAttribute("reasons", reasonRepository.findAll());
        Locale currentLocale = LocaleContextHolder.getLocale();
        String languageCode = currentLocale.getLanguage();
        System.out.println(languageCode);

        model.addAttribute("lang", languageCode);
        return "forms3";
    }

    @PostMapping("/education/extension")
    public String generateExtension(@Valid EducationExtensionCrWeekStatementDto extensionCrWeekStatementDto, BindingResult result, Model model) {
        // If there are validation errors, send error messages for each field back to the form page
        extensionCrWeekStatementDto.setFilledDate(LocalDate.now());
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

            return "forms3";
        }

        // If there are no validation errors, proceed with the service logic
        //add id
        Statement statement = statementsService.createEducationExtensionCrWeek(extensionCrWeekStatementDto);
        return "redirect:/form/success/" + statement.getId();
    }

    @GetMapping("/education/withdrawal")
    public String getFormPageWithdrawal(Model model) {
        model.addAttribute("universities", universityRepository.findAll());
        model.addAttribute("reasons", reasonRepository.findAll());
        Locale currentLocale = LocaleContextHolder.getLocale();
        String languageCode = currentLocale.getLanguage();
        System.out.println(languageCode);

        model.addAttribute("lang", languageCode);
        return "forms2";
    }

    @PostMapping("/education/withdrawal")
    public String generateRecovery(@Valid EducationWithdrawalStatementDto educationWithdrawalStatementDto, BindingResult result, Model model) {
        // If there are validation errors, send error messages for each field back to the form page
        educationWithdrawalStatementDto.setFilledDate(LocalDate.now());
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
            System.out.println(fieldErrors);
            return "forms2";
        }

        // If there are no validation errors, proceed with the service logic
        //add id
        Statement statement = statementsService.createEducationWithdrawalStatement(educationWithdrawalStatementDto);
        return "redirect:/form/success/" + statement.getId();
    }

    @GetMapping("/education/restoration")
    public String getFormPageRestoration(Model model) {
        model.addAttribute("universities", universityRepository.findAll());
        Locale currentLocale = LocaleContextHolder.getLocale();
        String languageCode = currentLocale.getLanguage();
        System.out.println(languageCode);

        model.addAttribute("lang", languageCode);
        return "forms4";
    }

    @PostMapping("/education/restoration")
    public String generateRecovery(@Valid EducationRestorationDocDto educationRestorationDocDto, BindingResult result, Model model) {
        // If there are validation errors, send error messages for each field back to the form page
        educationRestorationDocDto.setFilledDate(LocalDate.now());
        model.addAttribute("universities", universityRepository.findAll());
        model.addAttribute("lang", LocaleContextHolder.getLocale().getLanguage());
        if (result.hasErrors()) {
            // Create a map to hold field error messages
            Map<String, String> fieldErrors = new HashMap<>();
            for (FieldError error : result.getFieldErrors()) {
                fieldErrors.put(error.getField(), error.getDefaultMessage());
            }
            model.addAttribute("fieldErrors", fieldErrors);
            System.out.println(fieldErrors);
            return "forms2";
        }

        // If there are no validation errors, proceed with the service logic
        //add id
        Statement statement = statementsService.createEducationRestorationDocument(educationRestorationDocDto);
        return "redirect:/form/success/" + statement.getId();
    }

}
