package com.example.demo.controllersMvc;

import com.example.demo.dto.EducationRecoveryStatementDto;
import com.example.demo.service.StatementService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statement")
@RequiredArgsConstructor
@Slf4j
public class StatementController {
    private final StatementService statementService;

    @PostMapping("/educationRecovery")
    public String getEducationRecovery(@RequestBody @Valid EducationRecoveryStatementDto educationRecoveryStatementDto){
        return statementService.getEducationRecoveryStatement(educationRecoveryStatementDto);
    }

}
