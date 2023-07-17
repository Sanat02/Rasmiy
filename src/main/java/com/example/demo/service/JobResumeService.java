package com.example.demo.service;

import com.example.demo.dao.JobResumeDao;
import com.example.demo.dto.JobResumeDto;
import com.example.demo.model.JobResume;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class JobResumeService {

    private final JobResumeDao jobResumeDao;
    private final  UserService userService;
    private final CategoryService categoryService;




    public List<JobResumeDto> gettAllJobResumes(){
        List<JobResume> jobResumes=jobResumeDao.getAllJobResumes();
       return jobResumes.stream()
                .map(e->JobResumeDto.builder()
                        .id(e.getId())
                        .jobDescription(e.getJobDescription())
                        .jobTitle(e.getJobTitle())
                        .salary(e.getSalary())
                        .employer(userService.getUserById(e.getUserId()))
                        .category(categoryService.getCategoryById(e.getCategoryId()))
                        .experience(e.getExperience())
                        .build()
                ).toList();

    }
    public JobResumeDto getJobResumeById(int id){
        List<JobResumeDto> jobResumeDtos=gettAllJobResumes();
        JobResumeDto jobResumeDto=jobResumeDtos.stream().filter(e->e.getId()==id)
                .findFirst().orElse(null);
        return jobResumeDto;
    }

    public List<JobResumeDto> getJobResumeByCategoryName(String categoryName){
        List<JobResumeDto> jobResumeDtos=gettAllJobResumes().
                stream().
                filter(e->e.getCategory().getName().equalsIgnoreCase(categoryName)).
                collect(toList());
        return jobResumeDtos;

    }

    public void createJobResume(JobResume jobResume){
        jobResumeDao.createJobResume(jobResume);
    }
    public void updateJobResume(JobResume jobResume){
        jobResumeDao.updateJobResume(jobResume);
    }
    public void deleteJobResume(int id){
        jobResumeDao.deleteJobResume(id);
    }


}
