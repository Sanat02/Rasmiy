package com.example.demo.controllersMvc;

import com.example.demo.dto.JobResumeDto;
import com.example.demo.dto.UserDto;
import com.example.demo.dto.WhoInterestedDto;
import com.example.demo.service.JobResumeService;
import com.example.demo.service.UserService;
import com.example.demo.service.WhoInterestedService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/click")
public class WhoInterestedController {
    private final WhoInterestedService whoInterestedService;
    private final UserService userService;
    private final JobResumeService jobResumeService;

    @GetMapping("/{jobResumeId}")
    public String makeClick(@PathVariable int jobResumeId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDto userDto = userService.mapToUserDto(userService.getUserByEmail(auth.getName()).orElse(null));
        WhoInterestedDto whoInterestedDto = WhoInterestedDto.builder()
                .job_resume(JobResumeDto.builder()
                        .id(jobResumeId)
                        .build())
                .applicant(userDto)
                .build();
        if (whoInterestedService.isClicked(whoInterestedDto)) {
            whoInterestedService.deleteClick(whoInterestedDto);
        }
        whoInterestedService.saveClick(whoInterestedDto);

        return "redirect:/jobresumes/" + jobResumeId;
    }

    @GetMapping("/delete/{jobResumeId}")
    public String deleteClick(@PathVariable int jobResumeId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDto userDto = userService.mapToUserDto(userService.getUserByEmail(auth.getName()).orElse(null));
        WhoInterestedDto whoInterestedDto = WhoInterestedDto.builder()
                .job_resume(JobResumeDto.builder()
                        .id(jobResumeId)
                        .build())
                .applicant(userDto)
                .build();
        whoInterestedService.deleteClick(whoInterestedDto);

        return "redirect:/jobresumes/" + jobResumeId;
    }

    @GetMapping("/jobresumes/emp")
    public String getJobResumesByUserId(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDto userDto = userService.mapToUserDto(userService.getUserByEmail(auth.getName()).orElse(null));

        var jobs = jobResumeService.getJobResumesByUserId(userDto.getId());
        model.addAttribute("jobs",jobs);
        model.addAttribute("username",userDto.getAccountName());
        return "whoInterested";


    }

    @GetMapping("/hr/{jobresumeid}")
    public String getJobResumesClickedByUserId(Model model,@PathVariable int jobresumeid) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDto userDto = userService.mapToUserDto(userService.getUserByEmail(auth.getName()).orElse(null));
        var jobs = jobResumeService.getJobResumesByUserId(userDto.getId());

        model.addAttribute("username",userDto.getAccountName());
        model.addAttribute("whoInterested",whoInterestedService.getClickedByJobResumeId(jobresumeid));
        return "clickeds";
    }

}
