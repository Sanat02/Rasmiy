package com.example.demo.controllersMvc;

import com.example.demo.dto.UserDto;
import com.example.demo.service.JobResumeService;
import com.example.demo.service.ResumeService;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/profile")
public class ProfileController {
    private final UserService userService;
    private final ResumeService resumeService;
    private final JobResumeService jobResumeService;

    @GetMapping()
    public String getProfile(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDto userDto = userService.mapToUserDto(userService.getUserByEmail(auth.getName()).orElse(null));
        if (userDto != null) {
            userDto.setResumes(resumeService.getResumesByUserId(userDto.getId()));
            userDto.setJobResumes(jobResumeService.getJobResumesByUserId(userDto.getId()));
        }
        model.addAttribute("account", userDto);
        return "profile";
    }

    @PostMapping("/{userId}/setimage")
    public String setProfileImage(@PathVariable int userId, Model model) {
        return "profile";
    }
}
