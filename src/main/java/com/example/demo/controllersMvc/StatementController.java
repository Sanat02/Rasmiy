package com.example.demo.controllersMvc;

import com.example.demo.dto.EducationExtensionCrWeekStatementDto;
import com.example.demo.dto.EducationRecoveryStatementDto;
import com.example.demo.dto.EducationRestorationDocDto;
import com.example.demo.dto.EducationWithdrawalStatementDto;
import com.example.demo.model.Statement;
import com.example.demo.service.StatementsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/statement")
@RequiredArgsConstructor
@Slf4j
public class StatementController {
    private final StatementsService statementsService;

    @PostMapping("/educationRecovery")
    public Statement getEducationRecovery(@RequestBody @Valid EducationRecoveryStatementDto educationRecoveryStatementDto){
        educationRecoveryStatementDto.setFilledDate(LocalDate.now());
        return statementsService.createEducationRecoveryStatement(educationRecoveryStatementDto);
    }

    @PostMapping("/educationWithdrawal")
    public Statement getEducationWithdrawal(@RequestBody @Valid EducationWithdrawalStatementDto educationWithdrawalStatementDto){
        educationWithdrawalStatementDto.setFilledDate(LocalDate.now());
        return statementsService.createEducationWithdrawalStatement(educationWithdrawalStatementDto);
    }

    @PostMapping("/educationExtensionCrWeek")
    public Statement getEducationExtensionCrWeek(@RequestBody @Valid EducationExtensionCrWeekStatementDto educationExtensionCrWeekStatementDto){
        educationExtensionCrWeekStatementDto.setFilledDate(LocalDate.now());
        return statementsService.createEducationExtensionCrWeek(educationExtensionCrWeekStatementDto);
    }

    @PostMapping("/educationRestorationDoc")
    public Statement getEducationRestorationDoc(@RequestBody @Valid EducationRestorationDocDto educationRestorationDocDto){
        educationRestorationDocDto.setFilledDate(LocalDate.now());
        return statementsService.createEducationRestorationDocument(educationRestorationDocDto);
    }

}
