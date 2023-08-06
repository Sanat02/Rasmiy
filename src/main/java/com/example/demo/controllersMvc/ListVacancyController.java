//package com.example.demo.controllersMvc;
//
//import com.example.demo.service.JobsListService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@Controller
//@RequiredArgsConstructor
//@RequestMapping("/vacancies")
//public class ListVacancyController {
//    private final JobsListService jobsListService;
//
//    @GetMapping
//    public String getVacancies(Model model){
//        var vacancies=jobsListService.getAllJobs();
//        model.addAttribute("vacancies",vacancies);
//        return "vacancies";
//    }
//
//}
