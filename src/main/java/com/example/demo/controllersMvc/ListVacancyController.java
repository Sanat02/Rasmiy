package com.example.demo.controllersMvc;

import com.example.demo.dto.JobListDto;
import com.example.demo.service.JobsListService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/vacancies")
public class ListVacancyController {
    private final JobsListService jobsListService;

    private static final int PAGE_SIZE = 5;

    @GetMapping
    public String getVacancies(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
        Page<JobListDto> vacancies = jobsListService.getAllJobs(page, PAGE_SIZE);
        model.addAttribute("vacancies", vacancies);
        return "vacancies";
    }


}
