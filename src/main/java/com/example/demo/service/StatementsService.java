package com.example.demo.service;

import com.example.demo.dto.*;
import com.example.demo.enums.SklonenieType;
import com.example.demo.model.Statement;
import com.example.demo.model.User;
import com.example.demo.repository.DocTypeRepository;
import com.example.demo.repository.ReasonRepository;
import com.example.demo.repository.StatementRepository;
import com.example.demo.repository.UniversityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StatementsService {
    private final AffixService affixService;
    private final UniversityRepository universityRepository;
    private final StatementRepository statementRepository;
    private final DocTypeRepository docTypeRepository;
    private final ReasonRepository reasonRepository;
    private final UserService userService;

    public Statement createEducationRecoveryStatement(EducationRecoveryStatementDto educationRecoveryStatementDto) {
        String title = "Арыз";
        String header = "";
        String mainText = "";
        String universityIlikText = universityRepository.findById(educationRecoveryStatementDto.getUniversityId()).orElseThrow().getAffixedUniversityI();
        String rectorFioB = "";
        if (educationRecoveryStatementDto.getRectorO().isEmpty()) {
            String result = affixService.addAffix(educationRecoveryStatementDto.getRectorI(), SklonenieType.BARYSH);
            if (!result.isEmpty()) {
                result = Character.toUpperCase(result.charAt(0)) + result.substring(1);
            }

            rectorFioB = rectorFioB + educationRecoveryStatementDto.getRectorF() +
                    " " + result;
        } else {
            String result = affixService.addAffix(educationRecoveryStatementDto.getRectorO(), SklonenieType.BARYSH);
            if (!result.isEmpty()) {
                result = Character.toUpperCase(result.charAt(0)) + result.substring(1);
            }
            rectorFioB = rectorFioB + educationRecoveryStatementDto.getRectorF() + " " + educationRecoveryStatementDto.getRectorI()
                    + " " + result;
        }
        String course = educationRecoveryStatementDto.getCourse();
        String studentFioCh = "";
        if (educationRecoveryStatementDto.getO().isEmpty()) {
            String result = affixService.addAffix(educationRecoveryStatementDto.getRectorI(), SklonenieType.CHYGYSH);
            if (!result.isEmpty()) {
                result = Character.toUpperCase(result.charAt(0)) + result.substring(1);
            }
            studentFioCh = studentFioCh + educationRecoveryStatementDto.getF() +
                    " " + result;
        } else {
            String result = affixService.addAffix(educationRecoveryStatementDto.getO(), SklonenieType.CHYGYSH);
            if (!result.isEmpty()) {
                result = Character.toUpperCase(result.charAt(0)) + result.substring(1);
            }
            studentFioCh = studentFioCh + educationRecoveryStatementDto.getF() + " " + educationRecoveryStatementDto.getI()
                    + " " + result;
        }
        String faculty = educationRecoveryStatementDto.getFaculty() + " факультетинин";
        String desiredCourse = course + "-курсуна";
        String reason = educationRecoveryStatementDto.getReason();
        String date = "Толтурулган күнү:" + educationRecoveryStatementDto.getFilledDate();

        String editedReason = Character.toUpperCase(reason.charAt(0)) + reason.substring(1);

        header = universityIlikText + " ректору " + rectorFioB + " ушул эле ЖОЖдун " + course + "-курсунан четтилген студенти " + studentFioCh;
        mainText = "Мени " + faculty + " " + desiredCourse + " студенттердин катарына кошуп коюңузду өтүнөм." + editedReason + " окуудан четтетилгем.";
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUserByEmail(auth.getName()).orElseThrow();
        return statementRepository.save(Statement.builder()
                .header(header)
                .title(title)
                .mainText(mainText)
                .filledDate(date)
                .user(user)
                .build());

    }

    public Statement createEducationWithdrawalStatement(EducationWithdrawalStatementDto educationWithdrawalStatementDto) {
        String title = "Арыз";
        String header = "";
        String mainText = "";
        String universityIlikText = universityRepository.findById(educationWithdrawalStatementDto.getUniversityId()).orElseThrow().getAffixedUniversityI();
        String rectorFioB = "";
        if (educationWithdrawalStatementDto.getRectorO().isEmpty()) {
            String result = affixService.addAffix(educationWithdrawalStatementDto.getRectorI(), SklonenieType.BARYSH);
            if (!result.isEmpty()) {
                result = Character.toUpperCase(result.charAt(0)) + result.substring(1);
            }
            rectorFioB = rectorFioB + educationWithdrawalStatementDto.getRectorF() +
                    " " + result;
        } else {
            String result = affixService.addAffix(educationWithdrawalStatementDto.getRectorO(), SklonenieType.BARYSH);
            if (!result.isEmpty()) {
                result = Character.toUpperCase(result.charAt(0)) + result.substring(1);
            }
            rectorFioB = rectorFioB + educationWithdrawalStatementDto.getRectorF() + " " + educationWithdrawalStatementDto.getRectorI()
                    + " " + result;
        }
        String course = educationWithdrawalStatementDto.getCourse();
        String studentFioCh = "";
        if (educationWithdrawalStatementDto.getO().isEmpty()) {
            String result = affixService.addAffix(educationWithdrawalStatementDto.getRectorI(), SklonenieType.CHYGYSH);
            if (!result.isEmpty()) {
                result = Character.toUpperCase(result.charAt(0)) + result.substring(1);
            }
            studentFioCh = studentFioCh + educationWithdrawalStatementDto.getF() +
                    " " + result;
        } else {
            String result = affixService.addAffix(educationWithdrawalStatementDto.getO(), SklonenieType.CHYGYSH);
            if (!result.isEmpty()) {
                result = Character.toUpperCase(result.charAt(0)) + result.substring(1);
            }
            studentFioCh = studentFioCh + educationWithdrawalStatementDto.getF() + " " + educationWithdrawalStatementDto.getI()
                    + " " + result;
        }
        String faculty = educationWithdrawalStatementDto.getFaculty() + " факультетинин";
        String desiredCourse = course + "-курсунун";
        String reason = educationWithdrawalStatementDto.getReason();
        String date = "Толтурулган күнү:" + educationWithdrawalStatementDto.getFilledDate();
        String group = educationWithdrawalStatementDto.getGroup() + " группасынын студенти";
        String groupCh = educationWithdrawalStatementDto.getGroup() + " группасынан";

        header = universityIlikText + " ректору " + rectorFioB + " ушул эле ЖОЖдун " + course + "-курсунун " + faculty + " " + group + " " + studentFioCh;
        mainText = "Мени " + universityIlikText + " " + desiredCourse + " " + faculty + " " + groupCh + " " + reason + " өз каалоом менен чыгырап жиберүүңүздү өтүнөм.";
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUserByEmail(auth.getName()).orElseThrow();
        return statementRepository.save(Statement.builder()
                .header(header)
                .title(title)
                .mainText(mainText)
                .user(user)
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
            String result = affixService.addAffix(educationExtensionCrWeekStatementDto.getRectorI(), SklonenieType.BARYSH);
            if (!result.isEmpty()) {
                result = Character.toUpperCase(result.charAt(0)) + result.substring(1);
            }
            rectorFioB = rectorFioB + educationExtensionCrWeekStatementDto.getRectorF() +
                    " " + result;
        } else {
            String result = affixService.addAffix(educationExtensionCrWeekStatementDto.getRectorO(), SklonenieType.BARYSH);
            if (!result.isEmpty()) {
                result = Character.toUpperCase(result.charAt(0)) + result.substring(1);
            }
            rectorFioB = rectorFioB + educationExtensionCrWeekStatementDto.getRectorF() + " " + educationExtensionCrWeekStatementDto.getRectorI()
                    + " " + result;
        }
        String course = educationExtensionCrWeekStatementDto.getCourse();
        String studentFioCh = "";
        if (educationExtensionCrWeekStatementDto.getO().isEmpty()) {
            String result = affixService.addAffix(educationExtensionCrWeekStatementDto.getRectorI(), SklonenieType.CHYGYSH);
            if (!result.isEmpty()) {
                result = Character.toUpperCase(result.charAt(0)) + result.substring(1);
            }
            studentFioCh = studentFioCh + educationExtensionCrWeekStatementDto.getF() +
                    " " + result;
        } else {
            String result = affixService.addAffix(educationExtensionCrWeekStatementDto.getO(), SklonenieType.CHYGYSH);
            if (!result.isEmpty()) {
                result = Character.toUpperCase(result.charAt(0)) + result.substring(1);
            }
            studentFioCh = studentFioCh + educationExtensionCrWeekStatementDto.getF() + " " + educationExtensionCrWeekStatementDto.getI()
                    + " " + result;
        }
        String faculty = educationExtensionCrWeekStatementDto.getFaculty() + " факультетинин";
        String date = "Толтурулган күнү:" + educationExtensionCrWeekStatementDto.getFilledDate();
        String group = educationExtensionCrWeekStatementDto.getGroup() + " группасынын студенти";


        header = universityIlikText + " ректору " + rectorFioB + " ушул эле ЖОЖдун " + course + "-курсунун " + faculty + " " + group + " " + studentFioCh;
        mainText = educationExtensionCrWeekStatementDto.getLesson() + " сабагынан зачет албай калганыма байланыштуу зачеттук жумамды " +
                educationExtensionCrWeekStatementDto.getExtensionDate() + " чейин узартууну суранамын.Эгерде мен көрсөтүлгөн убакытка чейин тапшырып бүтпөсөм окуудан четтетилүүгө макулмун.";
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUserByEmail(auth.getName()).orElseThrow();
        return statementRepository.save(Statement.builder()
                .header(header)
                .title(title)
                .mainText(mainText)
                .user(user)
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
            String result = affixService.addAffix(educationRestorationDocDto.getRectorI(), SklonenieType.BARYSH);
            if (!result.isEmpty()) {
                result = Character.toUpperCase(result.charAt(0)) + result.substring(1);
            }
            rectorFioB = rectorFioB + educationRestorationDocDto.getRectorF() +
                    " " + result;
        } else {
            String result = affixService.addAffix(educationRestorationDocDto.getRectorO(), SklonenieType.BARYSH);
            if (!result.isEmpty()) {
                result = Character.toUpperCase(result.charAt(0)) + result.substring(1);
            }
            rectorFioB = rectorFioB + educationRestorationDocDto.getRectorF() + " " + educationRestorationDocDto.getRectorI()
                    + " " + result;
        }
        String course = educationRestorationDocDto.getCourse();
        String studentFioCh = "";
        if (educationRestorationDocDto.getO().isEmpty()) {
            String result = affixService.addAffix(educationRestorationDocDto.getRectorI(), SklonenieType.CHYGYSH);
            if (!result.isEmpty()) {
                result = Character.toUpperCase(result.charAt(0)) + result.substring(1);
            }
            studentFioCh = studentFioCh + educationRestorationDocDto.getF() +
                    " " + result;
        } else {
            String result = affixService.addAffix(educationRestorationDocDto.getO(), SklonenieType.CHYGYSH);
            if (!result.isEmpty()) {
                result = Character.toUpperCase(result.charAt(0)) + result.substring(1);
            }
            studentFioCh = studentFioCh + educationRestorationDocDto.getF() + " " + educationRestorationDocDto.getI()
                    + " " + result;
        }
        String faculty = educationRestorationDocDto.getFaculty() + " факультетинин";
        String date = "Толтурулган күнү:" + educationRestorationDocDto.getFilledDate();
        String group = educationRestorationDocDto.getGroup() + " группасынын студенти";
        String affixedDocType = educationRestorationDocDto.getDocumentType();

        header = universityIlikText + " ректору " + rectorFioB + " ушул эле ЖОЖдун " + course + "-курсунун " + faculty + " " + group + " " + studentFioCh;
        mainText = affixedDocType + " жоготуп алганыма байланыштуу мага  калыбына келтирүүнү өтүнөм.";
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUserByEmail(auth.getName()).orElseThrow();
        return statementRepository.save(Statement.builder()
                .header(header)
                .title(title)
                .mainText(mainText)
                .user(user)
                .filledDate(date)
                .build());

    }

    public Statement getStatementById(int id) {
        return statementRepository.findById(id).orElseThrow();
    }

    public Statement createEducationFioChangedStatement(EducationFIOChangedStatementDto educationFIOChangedStatementDto) {
        String title = "Арыз";
        String header = "";
        String mainText = "";
        String universityIlikText = universityRepository.findById(educationFIOChangedStatementDto.getUniversityId()).orElseThrow().getAffixedUniversityI();
        String rectorFioB = "";
        if (educationFIOChangedStatementDto.getRectorO().isEmpty()) {
            String result = affixService.addAffix(educationFIOChangedStatementDto.getRectorI(), SklonenieType.BARYSH);
            if (!result.isEmpty()) {
                result = Character.toUpperCase(result.charAt(0)) + result.substring(1);
            }
            rectorFioB = rectorFioB + educationFIOChangedStatementDto.getRectorF() +
                    " " + result;
        } else {
            String result = affixService.addAffix(educationFIOChangedStatementDto.getRectorO(), SklonenieType.BARYSH);
            if (!result.isEmpty()) {
                result = Character.toUpperCase(result.charAt(0)) + result.substring(1);
            }
            rectorFioB = rectorFioB + educationFIOChangedStatementDto.getRectorF() + " " + educationFIOChangedStatementDto.getRectorI()
                    + " " + result;
        }
        String course = educationFIOChangedStatementDto.getCourse();
        String studentFioCh = "";
        if (educationFIOChangedStatementDto.getO().isEmpty()) {
            String result = affixService.addAffix(educationFIOChangedStatementDto.getRectorI(), SklonenieType.CHYGYSH);
            if (!result.isEmpty()) {
                result = Character.toUpperCase(result.charAt(0)) + result.substring(1);
            }
            studentFioCh = studentFioCh + educationFIOChangedStatementDto.getF() +
                    " " + result;
        } else {
            String result = affixService.addAffix(educationFIOChangedStatementDto.getO(), SklonenieType.CHYGYSH);
            if (!result.isEmpty()) {
                result = Character.toUpperCase(result.charAt(0)) + result.substring(1);
            }
            studentFioCh = studentFioCh + educationFIOChangedStatementDto.getF() + " " + educationFIOChangedStatementDto.getI()
                    + " " + result;
        }
        String studentFioT = "";
        if (educationFIOChangedStatementDto.getO().isEmpty()) {
            String result = affixService.addAffix(educationFIOChangedStatementDto.getI(), SklonenieType.TABYSH);
            if (!result.isEmpty()) {
                result = Character.toUpperCase(result.charAt(0)) + result.substring(1);
            }
            studentFioT = studentFioT + educationFIOChangedStatementDto.getF() +
                    " " + result;
        } else {
            String result = affixService.addAffix(educationFIOChangedStatementDto.getO(), SklonenieType.TABYSH);
            if (!result.isEmpty()) {
                result = Character.toUpperCase(result.charAt(0)) + result.substring(1);
            }
            studentFioT = studentFioT + educationFIOChangedStatementDto.getF() + " " + educationFIOChangedStatementDto.getI()
                    + " " + result;
        }

        String newFioB = "";
        if (educationFIOChangedStatementDto.getNewO().isEmpty()) {
            String result = affixService.addAffix(educationFIOChangedStatementDto.getNewI(), SklonenieType.BARYSH);
            if (!result.isEmpty()) {
                result = Character.toUpperCase(result.charAt(0)) + result.substring(1);
            }
            newFioB = newFioB + educationFIOChangedStatementDto.getNewF() +
                    " " + result;
        } else {
            String result = affixService.addAffix(educationFIOChangedStatementDto.getNewO(), SklonenieType.BARYSH);
            if (!result.isEmpty()) {
                result = Character.toUpperCase(result.charAt(0)) + result.substring(1);
            }
            newFioB = newFioB + educationFIOChangedStatementDto.getNewF() + " " + educationFIOChangedStatementDto.getNewI()
                    + " " + result;
        }

        String faculty = educationFIOChangedStatementDto.getFaculty() + " факультетинин";
        String date = "Толтурулган күнү:" + educationFIOChangedStatementDto.getFilledDate();
        String group = educationFIOChangedStatementDto.getGroup() + " группасынын студенти";


        header = universityIlikText + " ректору " + rectorFioB + " ушул эле ЖОЖдун " + course + "-курсунун " + faculty + " " + group + " " + studentFioCh;
        mainText = "Паспортумду алмаштырганыма байланыштуу буга чейинки аты-жөнүм " + studentFioT + " жаңы аты-жөнүм " + newFioB + " өзгөртүүнү суранам.Паспорттун жаңы көчүрмөсү тиркелди.";
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUserByEmail(auth.getName()).orElseThrow();
        return statementRepository.save(Statement.builder()
                .header(header)
                .title(title)
                .mainText(mainText)
                .user(user)
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
            String result = affixService.addAffix(educationAcademicLeaveStatementDto.getRectorI(), SklonenieType.BARYSH);
            if (!result.isEmpty()) {
                result = Character.toUpperCase(result.charAt(0)) + result.substring(1);
            }
            rectorFioB = rectorFioB + educationAcademicLeaveStatementDto.getRectorF() +
                    " " + result;
        } else {
            String result = affixService.addAffix(educationAcademicLeaveStatementDto.getRectorO(), SklonenieType.BARYSH);
            if (!result.isEmpty()) {
                result = Character.toUpperCase(result.charAt(0)) + result.substring(1);
            }
            rectorFioB = rectorFioB + educationAcademicLeaveStatementDto.getRectorF() + " " + educationAcademicLeaveStatementDto.getRectorI()
                    + " " + result;
        }
        String course = educationAcademicLeaveStatementDto.getCourse();
        String studentFioCh = "";
        if (educationAcademicLeaveStatementDto.getO().isEmpty()) {
            String result = affixService.addAffix(educationAcademicLeaveStatementDto.getRectorI(), SklonenieType.CHYGYSH);
            if (!result.isEmpty()) {
                result = Character.toUpperCase(result.charAt(0)) + result.substring(1);
            }
            studentFioCh = studentFioCh + educationAcademicLeaveStatementDto.getF() +
                    " " + result;
        } else {
            String result = affixService.addAffix(educationAcademicLeaveStatementDto.getO(), SklonenieType.CHYGYSH);
            if (!result.isEmpty()) {
                result = Character.toUpperCase(result.charAt(0)) + result.substring(1);
            }
            studentFioCh = studentFioCh + educationAcademicLeaveStatementDto.getF() + " " + educationAcademicLeaveStatementDto.getI()
                    + " " + result;
        }
        String faculty = educationAcademicLeaveStatementDto.getFaculty() + " факультетинин";
        String date = "Толтурулган күнү:" + educationAcademicLeaveStatementDto.getFilledDate();
        String group = educationAcademicLeaveStatementDto.getGroup() + " группасынын студенти";


        header = universityIlikText + " ректору " + rectorFioB + " ушул эле ЖОЖдун " + course + "-курсунун " + faculty + " " + group + " " + studentFioCh;
        mainText = educationAcademicLeaveStatementDto.getReason() + " лекцияларга эркин катышууга уруксат берүүңүздү өтүнөм.\n" +
                "Керектүү документтердин көчүрмөсү тиркелди.";
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUserByEmail(auth.getName()).orElseThrow();
        return statementRepository.save(Statement.builder()
                .header(header)
                .title(title)
                .mainText(mainText)
                .user(user)
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
            String result = affixService.addAffix(educationTransferFacultyStatement.getRectorI(), SklonenieType.BARYSH);
            if (!result.isEmpty()) {
                result = Character.toUpperCase(result.charAt(0)) + result.substring(1);
            }
            rectorFioB = rectorFioB + educationTransferFacultyStatement.getRectorF() +
                    " " + result;
        } else {
            String result = affixService.addAffix(educationTransferFacultyStatement.getRectorO(), SklonenieType.BARYSH);
            if (!result.isEmpty()) {
                result = Character.toUpperCase(result.charAt(0)) + result.substring(1);
            }
            rectorFioB = rectorFioB + educationTransferFacultyStatement.getRectorF() + " " + educationTransferFacultyStatement.getRectorI()
                    + " " + result;
        }
        String course = educationTransferFacultyStatement.getCourse();
        String studentFioCh = "";
        if (educationTransferFacultyStatement.getO().isEmpty()) {
            String result = affixService.addAffix(educationTransferFacultyStatement.getRectorI(), SklonenieType.CHYGYSH);
            if (!result.isEmpty()) {
                result = Character.toUpperCase(result.charAt(0)) + result.substring(1);
            }
            studentFioCh = studentFioCh + educationTransferFacultyStatement.getF() +
                    " " + result;
        } else {
            String result = affixService.addAffix(educationTransferFacultyStatement.getO(), SklonenieType.CHYGYSH);
            if (!result.isEmpty()) {
                result = Character.toUpperCase(result.charAt(0)) + result.substring(1);
            }
            studentFioCh = studentFioCh + educationTransferFacultyStatement.getF() + " " + educationTransferFacultyStatement.getI()
                    + " " + result;
        }
        String faculty = educationTransferFacultyStatement.getFaculty() + " факультетинин";
        String date = "Толтурулган күнү:" + educationTransferFacultyStatement.getFilledDate();
        String group = educationTransferFacultyStatement.getGroup() + " группасынын студенти";


        header = universityIlikText + " ректору " + rectorFioB + " ушул эле ЖОЖдун " + course + "-курсунун " + faculty + " " + group + " " + studentFioCh;
        mainText = "Мени " + educationTransferFacultyStatement.getTransferFaculty() + " факультетине " + course + " -курска " +
                educationTransferFacultyStatement.getTransferDepartment() + " багыттамасына которууну өтүнөм.";
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUserByEmail(auth.getName()).orElseThrow();
        return statementRepository.save(Statement.builder()
                .header(header)
                .title(title)
                .mainText(mainText)
                .user(user)
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
            String result = affixService.addAffix(educationFromTransferStatementDto.getRectorI(), SklonenieType.BARYSH);
            if (!result.isEmpty()) {
                result = Character.toUpperCase(result.charAt(0)) + result.substring(1);
            }
            rectorFioB = rectorFioB + educationFromTransferStatementDto.getRectorF() +
                    " " + result;
        } else {
            String result = affixService.addAffix(educationFromTransferStatementDto.getRectorO(), SklonenieType.BARYSH);
            if (!result.isEmpty()) {
                result = Character.toUpperCase(result.charAt(0)) + result.substring(1);
            }
            rectorFioB = rectorFioB + educationFromTransferStatementDto.getRectorF() + " " + educationFromTransferStatementDto.getRectorI()
                    + " " + result;
        }
        String course = educationFromTransferStatementDto.getCourse();
        String studentFioCh = "";
        if (educationFromTransferStatementDto.getO().isEmpty()) {
            String result = affixService.addAffix(educationFromTransferStatementDto.getRectorI(), SklonenieType.CHYGYSH);
            if (!result.isEmpty()) {
                result = Character.toUpperCase(result.charAt(0)) + result.substring(1);
            }
            studentFioCh = studentFioCh + educationFromTransferStatementDto.getF() +
                    " " + result;
        } else {
            String result = affixService.addAffix(educationFromTransferStatementDto.getO(), SklonenieType.CHYGYSH);
            if (!result.isEmpty()) {
                result = Character.toUpperCase(result.charAt(0)) + result.substring(1);
            }
            studentFioCh = studentFioCh + educationFromTransferStatementDto.getF() + " " + educationFromTransferStatementDto.getI()
                    + " " + result;
        }
        String faculty = educationFromTransferStatementDto.getFacultyFrom() + " факультетинин";
        String date = "Толтурулган күнү:" + educationFromTransferStatementDto.getFilledDate();
        String group = educationFromTransferStatementDto.getGroupFrom() + " группасынын студенти";

        String fromUniversity = universityRepository.findById(educationFromTransferStatementDto.getUniversityFrom()).orElseThrow().getAffixedUniversityI();
        //String toFaculty

        header = universityIlikText + " ректору " + rectorFioB + " " + fromUniversity + " " + course + "-курсунун " + faculty + " " + group + " " + studentFioCh;
        mainText = "Мени " + universityIlikText + " " + educationFromTransferStatementDto.getFacultyTo() + " факультетинин " +
                educationFromTransferStatementDto.getGroupTo() + " бөлүмүнө которуу тартибинде кабыл алууңузду өтүнөм.";
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUserByEmail(auth.getName()).orElseThrow();
        return statementRepository.save(Statement.builder()
                .header(header)
                .title(title)
                .mainText(mainText)
                .user(user)
                .filledDate(date)
                .build());

    }

    public List<Statement> getStatementsByUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUserByEmail(auth.getName()).orElseThrow();
        List<Statement> statements = statementRepository.getStatementsByUserId(user.getId());
        return statements;
    }


}