package com.example.demo.controller;

import com.example.demo.dto.JobResumeDto;
import com.example.demo.dto.UserDto;
import com.example.demo.model.JobResume;
import com.example.demo.model.Resume;
import com.example.demo.model.WhoInterested;
import com.example.demo.service.JobResumeService;
import com.example.demo.service.ResumeService;
import com.example.demo.service.UserService;
import com.example.demo.service.WhoInterestedService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/jobseeker")
@RequiredArgsConstructor
public class JobSeekerController {
    private final ResumeService resumeService;
    private final JobResumeService jobResumeService;
    private final WhoInterestedService whoInterestedService;
    private final UserService userService;

    //Создание резюме для соискателя с user_id={user_id}
    @PostMapping("{userId}/resume")
    public void createJobResume(@PathVariable int userId, @RequestBody Resume resume) {
        log.info("User resume:" + resume.getId() + " created!");
        resume.setUserId(userId);
        resumeService.createResume(resume);
    }


    //Редактирование резюме для соискателя с user_id={user_id}
    @PutMapping("{user_id}/resume/{resume_id}")
    public void updateResume(@PathVariable int resume_id, @PathVariable int user_id, @RequestBody Resume resume) {
        log.info("Resume:" + resume.getId() + " updated!");
        resume.setUserId(user_id);
        resume.setId(resume_id);
        resumeService.updateResume(resume);
    }

    //Удаление резюме
    @DeleteMapping("/resume/{resume_id}")
    public void deleteResume(@PathVariable int resume_id) {
        log.info("Resume id:" + resume_id + " is deleted! ");
        resumeService.deleteResume(resume_id);
    }

    //Поиск всех вакансий
    @GetMapping("/jobresumes")
    public List<JobResumeDto> getAllJobResumes() {
        return jobResumeService.gettAllJobResumes();
    }

    //Поиск вакансии по категории
    @GetMapping("jobresumes/category/{category_name}")
    public List<JobResumeDto> getJobResumesByCategoryName(@PathVariable String category_name) {
        return jobResumeService.getJobResumeByCategoryName(category_name);
    }

    //Отклик на вакансию (внутри requestBody нужно указать id отклика так как нет автоинкремента
    //TODO сделать автоинкремент
    @PostMapping("/{user_id}/apply/{job_resume_id}")
    public void applyForJobResume(@PathVariable int user_id, @PathVariable int job_resume_id,
                                  @RequestBody WhoInterested whoInterested) {
        whoInterested.setApplicant_id(user_id);
        whoInterested.setJob_resume_id(job_resume_id);
        whoInterested.setDate(LocalDateTime.now());
        whoInterestedService.createInterested(whoInterested);
    }

    //Поиск работодателя
    @GetMapping("/employers")
    public List<UserDto> getAllEmployers(){
        return userService.getAllEmployers();
    }
}
