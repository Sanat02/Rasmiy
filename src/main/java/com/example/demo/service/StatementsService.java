package com.example.demo.service;

import com.example.demo.dto.EducationExtensionCrWeekStatementDto;
import com.example.demo.dto.EducationRecoveryStatementDto;
import com.example.demo.dto.EducationRestorationDocDto;
import com.example.demo.dto.EducationWithdrawalStatementDto;
import com.example.demo.enums.SklonenieType;
import com.example.demo.model.Statement;
import com.example.demo.repository.DocTypeRepository;
import com.example.demo.repository.ReasonRepository;
import com.example.demo.repository.StatementRepository;
import com.example.demo.repository.UniversityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatementsService {
    private final AffixService affixService;
    private final UniversityRepository universityRepository;
    private final StatementRepository statementRepository;
    private final DocTypeRepository docTypeRepository;
    private final ReasonRepository reasonRepository;

    public Statement createEducationRecoveryStatement(EducationRecoveryStatementDto educationRecoveryStatementDto) {
        String title = "Арыз";
        String header = "";
        String mainText = "";
        String universityIlikText = universityRepository.findById(educationRecoveryStatementDto.getUniversityId()).orElseThrow().getAffixedUniversityI();
        String rectorFioB = "";
        if (educationRecoveryStatementDto.getRectorO().isEmpty()) {
            rectorFioB = rectorFioB + educationRecoveryStatementDto.getRectorF() +
                    " " + affixService.addAffix(educationRecoveryStatementDto.getRectorI(), SklonenieType.BARYSH);
        } else {
            rectorFioB = rectorFioB + educationRecoveryStatementDto.getRectorF() + " " + educationRecoveryStatementDto.getRectorI()
                    + " " + affixService.addAffix(educationRecoveryStatementDto.getRectorO(), SklonenieType.BARYSH);
        }
        String course = educationRecoveryStatementDto.getCourse();
        String studentFioCh = "";
        if (educationRecoveryStatementDto.getO().isEmpty()) {
            studentFioCh = studentFioCh + educationRecoveryStatementDto.getF() +
                    " " + affixService.addAffix(educationRecoveryStatementDto.getRectorI(), SklonenieType.CHYGYSH);
        } else {
            studentFioCh = studentFioCh + educationRecoveryStatementDto.getF() + " " + educationRecoveryStatementDto.getI()
                    + " " + affixService.addAffix(educationRecoveryStatementDto.getO(), SklonenieType.CHYGYSH);
        }
        String faculty = educationRecoveryStatementDto.getFaculty() + " факультетинин";
        String desiredCourse = course + "-курсуна";
        String reason = educationRecoveryStatementDto.getReason();
        String date = "Толтурулган күнү:" + educationRecoveryStatementDto.getFilledDate();

        header = universityIlikText + " ректору " + rectorFioB + " ушул эле ЖОЖдун " + course + "-курсунан четтилген студенти " + studentFioCh;
        mainText = "Мени " + faculty + " " + desiredCourse + " студенттердин катарына кошуп коюңузду өтүнөм." + reason + " окуудан четтетилгем.";
        return statementRepository.save(Statement.builder()
                .header(header)
                .title(title)
                .mainText(mainText)
                .filledDate(date)
                .build());

    }

    public Statement createEducationWithdrawalStatement(EducationWithdrawalStatementDto educationWithdrawalStatementDto) {
        String title = "Арыз";
        String header = "";
        String mainText = "";
        String universityIlikText = universityRepository.findById(educationWithdrawalStatementDto.getUniversityId()).orElseThrow().getAffixedUniversityI();
        String rectorFioB = "";
        if (educationWithdrawalStatementDto.getRectorO().isEmpty()) {
            rectorFioB = rectorFioB + educationWithdrawalStatementDto.getRectorF() +
                    " " + affixService.addAffix(educationWithdrawalStatementDto.getRectorI(), SklonenieType.BARYSH);
        } else {
            rectorFioB = rectorFioB + educationWithdrawalStatementDto.getRectorF() + " " + educationWithdrawalStatementDto.getRectorI()
                    + " " + affixService.addAffix(educationWithdrawalStatementDto.getRectorO(), SklonenieType.BARYSH);
        }
        String course = educationWithdrawalStatementDto.getCourse();
        String studentFioCh = "";
        if (educationWithdrawalStatementDto.getO().isEmpty()) {
            studentFioCh = studentFioCh + educationWithdrawalStatementDto.getF() +
                    " " + affixService.addAffix(educationWithdrawalStatementDto.getRectorI(), SklonenieType.CHYGYSH);
        } else {
            studentFioCh = studentFioCh + educationWithdrawalStatementDto.getF() + " " + educationWithdrawalStatementDto.getI()
                    + " " + affixService.addAffix(educationWithdrawalStatementDto.getO(), SklonenieType.CHYGYSH);
        }
        String faculty = educationWithdrawalStatementDto.getFaculty() + " факультетинин";
        String desiredCourse = course + "-курсунун";
        String reason = educationWithdrawalStatementDto.getReason();
        String date = "Толтурулган күнү:" + educationWithdrawalStatementDto.getFilledDate();
        String group = educationWithdrawalStatementDto.getGroup() + " группасынын студенти";
        String groupCh = educationWithdrawalStatementDto.getGroup() + " группасынан";

        header = universityIlikText + " ректору " + rectorFioB + " ушул эле ЖОЖдун " + course + "-курсунун " + faculty + " " + group + " " + studentFioCh;
        mainText = "Мени " + universityIlikText + " " + desiredCourse + " " + faculty + " " + groupCh + " " + reason + " өз каалоом менен чыгырап жиберүүңүздү өтүнөм.";
        return statementRepository.save(Statement.builder()
                .header(header)
                .title(title)
                .mainText(mainText)
                .filledDate(date)
                .build());

    }

    public Statement createEducationExtensionCrWeek(EducationExtensionCrWeekStatementDto educationExtensionCrWeekStatementDto) {
        String title = "Арыз";
        String header = "";
        String mainText = "";
        String universityIlikText = universityRepository.findById(educationExtensionCrWeekStatementDto.getUniversityId()).orElseThrow().getAffixedUniversityI();
        String rectorFioB = "";
        if (educationExtensionCrWeekStatementDto.getRectorO().isEmpty()) {
            rectorFioB = rectorFioB + educationExtensionCrWeekStatementDto.getRectorF() +
                    " " + affixService.addAffix(educationExtensionCrWeekStatementDto.getRectorI(), SklonenieType.BARYSH);
        } else {
            rectorFioB = rectorFioB + educationExtensionCrWeekStatementDto.getRectorF() + " " + educationExtensionCrWeekStatementDto.getRectorI()
                    + " " + affixService.addAffix(educationExtensionCrWeekStatementDto.getRectorO(), SklonenieType.BARYSH);
        }
        String course = educationExtensionCrWeekStatementDto.getCourse();
        String studentFioCh = "";
        if (educationExtensionCrWeekStatementDto.getO().isEmpty()) {
            studentFioCh = studentFioCh + educationExtensionCrWeekStatementDto.getF() +
                    " " + affixService.addAffix(educationExtensionCrWeekStatementDto.getRectorI(), SklonenieType.CHYGYSH);
        } else {
            studentFioCh = studentFioCh + educationExtensionCrWeekStatementDto.getF() + " " + educationExtensionCrWeekStatementDto.getI()
                    + " " + affixService.addAffix(educationExtensionCrWeekStatementDto.getO(), SklonenieType.CHYGYSH);
        }
        String faculty = educationExtensionCrWeekStatementDto.getFaculty() + " факультетинин";
        String date = "Толтурулган күнү:" + educationExtensionCrWeekStatementDto.getFilledDate();
        String group = educationExtensionCrWeekStatementDto.getGroup() + " группасынын студенти";


        header = universityIlikText + " ректору " + rectorFioB + " ушул эле ЖОЖдун " + course + "-курсунун " + faculty + " " + group + " " + studentFioCh;
        mainText = educationExtensionCrWeekStatementDto.getLesson() + " сабагынан зачет албай калганыма байланыштуу зачеттук жумамды " +
                educationExtensionCrWeekStatementDto.getExtensionDate() + " чейин узартууну суранамын.Эгерде мен көрсөтүлгөн убакытка чейин тапшырып бүтпөсөм окуудан четтетилүүгө макулмун.";
        return statementRepository.save(Statement.builder()
                .header(header)
                .title(title)
                .mainText(mainText)
                .filledDate(date)
                .build());

    }

    public Statement createEducationRestorationDocument(EducationRestorationDocDto educationRestorationDocDto) {
        String title = "Арыз";
        String header = "";
        String mainText = "";
        String universityIlikText = universityRepository.findById(educationRestorationDocDto.getUniversityId()).orElseThrow().getAffixedUniversityI();
        String rectorFioB = "";
        if (educationRestorationDocDto.getRectorO().isEmpty()) {
            rectorFioB = rectorFioB + educationRestorationDocDto.getRectorF() +
                    " " + affixService.addAffix(educationRestorationDocDto.getRectorI(), SklonenieType.BARYSH);
        } else {
            rectorFioB = rectorFioB + educationRestorationDocDto.getRectorF() + " " + educationRestorationDocDto.getRectorI()
                    + " " + affixService.addAffix(educationRestorationDocDto.getRectorO(), SklonenieType.BARYSH);
        }
        String course = educationRestorationDocDto.getCourse();
        String studentFioCh = "";
        if (educationRestorationDocDto.getO().isEmpty()) {
            studentFioCh = studentFioCh + educationRestorationDocDto.getF() +
                    " " + affixService.addAffix(educationRestorationDocDto.getRectorI(), SklonenieType.CHYGYSH);
        } else {
            studentFioCh = studentFioCh + educationRestorationDocDto.getF() + " " + educationRestorationDocDto.getRectorI()
                    + " " + affixService.addAffix(educationRestorationDocDto.getO(), SklonenieType.CHYGYSH);
        }
        String faculty = educationRestorationDocDto.getFaculty() + " факультетинин";
        String date = "Толтурулган күнү:" + educationRestorationDocDto.getFilledDate();
        String group = educationRestorationDocDto.getGroup() + " группасынын студенти";
        String affixedDocType=educationRestorationDocDto.getDocumentType();

        header = universityIlikText + " ректору " + rectorFioB + " ушул эле ЖОЖдун " + course + "-курсунун " + faculty + " " + group + " " + studentFioCh;
        mainText = affixedDocType+" жоготуп алганыма байланыштуу мага  калыбына келтирүүнү өтүнөм.";
        return statementRepository.save(Statement.builder()
                .header(header)
                .title(title)
                .mainText(mainText)
                .filledDate(date)
                .build());

    }
    public Statement getStatementById(int id){
        return statementRepository.findById(id).orElseThrow();
    }


}
