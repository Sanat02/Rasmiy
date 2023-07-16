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

    private JobResumeDao jobResumeDao;
    private UserService userService;
    @Autowired
    public JobResumeService(JobResumeDao jobResumeDao,UserService userService) {
        this.jobResumeDao = jobResumeDao;
        this.userService = userService;
    }


    public List<JobResume> getResumeByCategory(String category){
        return jobResumeDao.getResumeByCategory(category);
    }

    public List<JobResumeDto> gettAllJobResumes(){
        List<JobResume> jobResumes=jobResumeDao.getAllJobResumes();
       return jobResumes.stream()
                .map(e->JobResumeDto.builder()
                        .id(e.getId())
                        .job_description(e.getJob_description())
                        .job_tile(e.getJob_title())
                        .salary(e.getSalary())
                        .experience(e.getExperience())
                        .category(e.getCategory())
                        .job_tile(e.getJob_title())
                        .employer(userService.getUserById(e.getUser_id()))
                        .build()
                ).toList();

    }
    public JobResumeDto getJobResumeById(int id){
        List<JobResumeDto> jobResumeDtos=gettAllJobResumes();
        JobResumeDto jobResumeDto=jobResumeDtos.stream().filter(e->e.getId()==id)
                .findFirst().orElse(null);
        return jobResumeDto;
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
