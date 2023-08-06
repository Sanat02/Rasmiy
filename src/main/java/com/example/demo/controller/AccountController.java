//package com.example.demo.controller;
//
//
//import com.example.demo.dto.UserDto;
//import com.example.demo.enums.AccountType;
//import com.example.demo.service.UserService;
//import jakarta.validation.Valid;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.ErrorResponse;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//
//
//@RestController
//@RequestMapping("/accounts")
//@RequiredArgsConstructor
//
//
//public class AccountController {
//    private final UserService userService;
//
//    @PostMapping("/register/applicant")
//    public ResponseEntity<String> createAccountApplicant(@RequestBody @Valid UserDto user, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            StringBuilder errorMessage = new StringBuilder();
//            bindingResult.getAllErrors().forEach(error -> {
//                errorMessage.append(error.getDefaultMessage()).append("; ");
//            });
//            return ResponseEntity.badRequest().body(errorMessage.toString());
//        }
//        user.setAccountType(AccountType.JOB_SEEKER);
//        userService.save(user);
//        return ResponseEntity.ok("Job-seeker registered successfully");
//    }
//
//
//
//    @PostMapping("/register/employer")
//    public ResponseEntity<String> createAccountEmployer(@RequestBody @Valid UserDto user, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            StringBuilder errorMessage = new StringBuilder();
//            bindingResult.getAllErrors().forEach(error -> {
//                errorMessage.append(error.getDefaultMessage()).append("; ");
//            });
//            return ResponseEntity.badRequest().body(errorMessage.toString());
//        }
//        user.setAccountType(AccountType.EMPLOYER);
//        userService.save(user);
//        return ResponseEntity.ok("Employer registered successfully");
//    }
//
//
//    @GetMapping
//    public List<UserDto> getAllAccounts() {
//        return userService.getAllUsers();
//    }
//
//    @GetMapping("/jobseekers")
//    public List<UserDto> getAllJobSeekers() {
//        return userService.getAllJobSeekers();
//    }
//
//    @GetMapping("/employers")
//    public List<UserDto> getAllEmployers() {
//        return userService.getAllEmployers();
//    }
//
////    @ExceptionHandler(NoSuchFieldError.class)
////    private ErrorResponse noSuchFieldHandler(NoSuchFieldError exception){
////        return ErrorResponse.builder(exception,HttpStatus.NOT_FOUND,exception.getMessage()).build();
////    }
//
//}
