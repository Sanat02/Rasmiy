package com.example.demo.service;

import com.example.demo.dto.*;
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

    public Statement createEducationFioChangedStatement(EducationFIOChangedStatementDto educationFIOChangedStatementDto) {
        String title = "Арыз";
        String header = "";
        String mainText = "";
        String universityIlikText = universityRepository.findById(educationFIOChangedStatementDto.getUniversityId()).orElseThrow().getAffixedUniversityI();
        String rectorFioB = "";
        if (educationFIOChangedStatementDto.getRectorO().isEmpty()) {
            rectorFioB = rectorFioB + educationFIOChangedStatementDto.getRectorF() +
                    " " + affixService.addAffix(educationFIOChangedStatementDto.getRectorI(), SklonenieType.BARYSH);
        } else {
            rectorFioB = rectorFioB + educationFIOChangedStatementDto.getRectorF() + " " + educationFIOChangedStatementDto.getRectorI()
                    + " " + affixService.addAffix(educationFIOChangedStatementDto.getRectorO(), SklonenieType.BARYSH);
        }
        String course = educationFIOChangedStatementDto.getCourse();
        String studentFioCh = "";
        if (educationFIOChangedStatementDto.getO().isEmpty()) {
            studentFioCh = studentFioCh + educationFIOChangedStatementDto.getF() +
                    " " + affixService.addAffix(educationFIOChangedStatementDto.getRectorI(), SklonenieType.CHYGYSH);
        } else {
            studentFioCh = studentFioCh + educationFIOChangedStatementDto.getF() + " " + educationFIOChangedStatementDto.getI()
                    + " " + affixService.addAffix(educationFIOChangedStatementDto.getO(), SklonenieType.CHYGYSH);
        }
        String studentFioT="";
        if (educationFIOChangedStatementDto.getO().isEmpty()) {
            studentFioT = studentFioT + educationFIOChangedStatementDto.getF() +
                    " " + affixService.addAffix(educationFIOChangedStatementDto.getI(), SklonenieType.TABYSH);
        } else {
            studentFioT = studentFioT + educationFIOChangedStatementDto.getF() + " " + educationFIOChangedStatementDto.getI()
                    + " " + affixService.addAffix(educationFIOChangedStatementDto.getO(), SklonenieType.TABYSH);
        }

        String newFioB="";
        if (educationFIOChangedStatementDto.getNewO().isEmpty()) {
            newFioB = newFioB+ educationFIOChangedStatementDto.getNewF() +
                    " " + affixService.addAffix(educationFIOChangedStatementDto.getNewI(), SklonenieType.BARYSH);
        } else {
            newFioB = newFioB + educationFIOChangedStatementDto.getNewF() + " " + educationFIOChangedStatementDto.getNewI()
                    + " " + affixService.addAffix(educationFIOChangedStatementDto.getNewO(), SklonenieType.BARYSH);
        }

        String faculty = educationFIOChangedStatementDto.getFaculty() + " факультетинин";
        String date = "Толтурулган күнү:" + educationFIOChangedStatementDto.getFilledDate();
        String group = educationFIOChangedStatementDto.getGroup() + " группасынын студенти";


        header = universityIlikText + " ректору " + rectorFioB + " ушул эле ЖОЖдун " + course + "-курсунун " + faculty + " " + group + " " + studentFioCh;
        mainText = "Паспортумду алмаштырганыма байланыштуу буга чейинки аты-жөнүм "+studentFioT+" жаңы аты-жөнүм "+newFioB+" өзгөртүүнү суранам.Паспорттун жаңы көчүрмөсү тиркелди.";
        return statementRepository.save(Statement.builder()
                .header(header)
                .title(title)
                .mainText(mainText)
                .filledDate(date)
                .build());

    }

    public Statement createEducationAcademicStatetment(EducationAcademicLeaveStatementDto educationAcademicLeaveStatementDto) {
        String title = "Арыз";
        String header = "";
        String mainText = "";
        String universityIlikText = universityRepository.findById(educationAcademicLeaveStatementDto.getUniversityId()).orElseThrow().getAffixedUniversityI();
        String rectorFioB = "";
        if (educationAcademicLeaveStatementDto.getRectorO().isEmpty()) {
            rectorFioB = rectorFioB + educationAcademicLeaveStatementDto.getRectorF() +
                    " " + affixService.addAffix(educationAcademicLeaveStatementDto.getRectorI(), SklonenieType.BARYSH);
        } else {
            rectorFioB = rectorFioB + educationAcademicLeaveStatementDto.getRectorF() + " " + educationAcademicLeaveStatementDto.getRectorI()
                    + " " + affixService.addAffix(educationAcademicLeaveStatementDto.getRectorO(), SklonenieType.BARYSH);
        }
        String course =educationAcademicLeaveStatementDto.getCourse();
        String studentFioCh = "";
        if (educationAcademicLeaveStatementDto.getO().isEmpty()) {
            studentFioCh = studentFioCh + educationAcademicLeaveStatementDto.getF() +
                    " " + affixService.addAffix(educationAcademicLeaveStatementDto.getRectorI(), SklonenieType.CHYGYSH);
        } else {
            studentFioCh = studentFioCh + educationAcademicLeaveStatementDto.getF() + " " + educationAcademicLeaveStatementDto.getI()
                    + " " + affixService.addAffix(educationAcademicLeaveStatementDto.getO(), SklonenieType.CHYGYSH);
        }
        String faculty = educationAcademicLeaveStatementDto.getFaculty() + " факультетинин";
        String date = "Толтурулган күнү:" + educationAcademicLeaveStatementDto.getFilledDate();
        String group = educationAcademicLeaveStatementDto.getGroup() + " группасынын студенти";


        header = universityIlikText + " ректору " + rectorFioB + " ушул эле ЖОЖдун " + course + "-курсунун " + faculty + " " + group + " " + studentFioCh;
        mainText = educationAcademicLeaveStatementDto.getReason()+" лекцияларга эркин катышууга уруксат берүүңүздү өтүнөм.\n" +
                "Керектүү документтердин көчүрмөсү тиркелди.";
        return statementRepository.save(Statement.builder()
                .header(header)
                .title(title)
                .mainText(mainText)
                .filledDate(date)
                .build());

    }

    public Statement createTransferFacultyStatement(EducationTransferFacultyStatement educationTransferFacultyStatement) {
        String title = "Арыз";
        String header = "";
        String mainText = "";
        String universityIlikText = universityRepository.findById(educationTransferFacultyStatement.getUniversityId()).orElseThrow().getAffixedUniversityI();
        String rectorFioB = "";
        if (educationTransferFacultyStatement.getRectorO().isEmpty()) {
            rectorFioB = rectorFioB + educationTransferFacultyStatement.getRectorF() +
                    " " + affixService.addAffix(educationTransferFacultyStatement.getRectorI(), SklonenieType.BARYSH);
        } else {
            rectorFioB = rectorFioB + educationTransferFacultyStatement.getRectorF() + " " + educationTransferFacultyStatement.getRectorI()
                    + " " + affixService.addAffix(educationTransferFacultyStatement.getRectorO(), SklonenieType.BARYSH);
        }
        String course =educationTransferFacultyStatement.getCourse();
        String studentFioCh = "";
        if (educationTransferFacultyStatement.getO().isEmpty()) {
            studentFioCh = studentFioCh + educationTransferFacultyStatement.getF() +
                    " " + affixService.addAffix(educationTransferFacultyStatement.getRectorI(), SklonenieType.CHYGYSH);
        } else {
            studentFioCh = studentFioCh + educationTransferFacultyStatement.getF() + " " + educationTransferFacultyStatement.getI()
                    + " " + affixService.addAffix(educationTransferFacultyStatement.getO(), SklonenieType.CHYGYSH);
        }
        String faculty = educationTransferFacultyStatement.getFaculty() + " факультетинин";
        String date = "Толтурулган күнү:" +educationTransferFacultyStatement.getFilledDate();
        String group = educationTransferFacultyStatement.getGroup() + " группасынын студенти";


        header = universityIlikText + " ректору " + rectorFioB + " ушул эле ЖОЖдун " + course + "-курсунун " + faculty + " " + group + " " + studentFioCh;
        mainText = "Мени "+educationTransferFacultyStatement.getTransferFaculty()+" факультетине "+course+" -курска "+
                educationTransferFacultyStatement.getTransferDepartment()+" багыттамасына которууну өтүнөм.";
        return statementRepository.save(Statement.builder()
                .header(header)
                .title(title)
                .mainText(mainText)
                .filledDate(date)
                .build());

    }
    public Statement createTransferUniversityStatement(EducationFromTransferStatementDto educationFromTransferStatementDto) {
        String title = "Арыз";
        String header = "";
        String mainText = "";
        String universityIlikText = universityRepository.findById(educationFromTransferStatementDto.getUniversityTo()).orElseThrow().getAffixedUniversityI();
        String rectorFioB = "";
        if (educationFromTransferStatementDto.getRectorO().isEmpty()) {
            rectorFioB = rectorFioB + educationFromTransferStatementDto.getRectorF() +
                    " " + affixService.addAffix(educationFromTransferStatementDto.getRectorI(), SklonenieType.BARYSH);
        } else {
            rectorFioB = rectorFioB + educationFromTransferStatementDto.getRectorF() + " " + educationFromTransferStatementDto.getRectorI()
                    + " " + affixService.addAffix(educationFromTransferStatementDto.getRectorO(), SklonenieType.BARYSH);
        }
        String course =educationFromTransferStatementDto.getCourse();
        String studentFioCh = "";
        if (educationFromTransferStatementDto.getO().isEmpty()) {
            studentFioCh = studentFioCh + educationFromTransferStatementDto.getF() +
                    " " + affixService.addAffix(educationFromTransferStatementDto.getRectorI(), SklonenieType.CHYGYSH);
        } else {
            studentFioCh = studentFioCh + educationFromTransferStatementDto.getF() + " " + educationFromTransferStatementDto.getI()
                    + " " + affixService.addAffix(educationFromTransferStatementDto.getO(), SklonenieType.CHYGYSH);
        }
        String faculty = educationFromTransferStatementDto.getFacultyFrom() + " факультетинин";
        String date = "Толтурулган күнү:" +educationFromTransferStatementDto.getFilledDate();
        String group = educationFromTransferStatementDto.getGroupFrom() + " группасынын студенти";

        String fromUniversity=universityRepository.findById(educationFromTransferStatementDto.getUniversityFrom()).orElseThrow().getAffixedUniversityI();
       //String toFaculty

        header = universityIlikText + " ректору " + rectorFioB + " "+fromUniversity+" " + course + "-курсунун " + faculty + " " + group + " " + studentFioCh;
        mainText = "Мени "+universityIlikText+" "+educationFromTransferStatementDto.getFacultyTo()+" факультетинин "+
                educationFromTransferStatementDto.getGroupTo()+" бөлүмүнө которуу тартибинде кабыл алууңузду өтүнөм.";
        return statementRepository.save(Statement.builder()
                .header(header)
                .title(title)
                .mainText(mainText)
                .filledDate(date)
                .build());

    }


}
