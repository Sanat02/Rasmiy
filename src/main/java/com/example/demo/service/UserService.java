package com.example.demo.service;


import com.example.demo.dto.UserDto;
import com.example.demo.enums.AccountType;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final RoleService roleService;
    private final EmailService emailService;


    public Optional<User> getUserByEmail(String email) {
        log.info("Gol user by email:" + email);
        Optional<User> mayBeUser = userRepository.findUserByEmail(email);
        return mayBeUser;
    }

    public String isUserExist(String email) {
        try {
            Optional<User> user = userRepository.findUserByEmail(email);
            if (user.isPresent()) {
                log.error("User:" + email + "  exists!");
                return "1";
            } else {
                log.info("User:" + email + " does not exist!");
                return "0";
            }
        } catch (EmptyResultDataAccessException e) {
            log.error("Empty Result!");
            return "error";
        }
    }


    public Optional<User> getUserById(int id) {
        log.info("Got user by id:" + id);
        return userRepository.findById(id);
    }


    public int save(UserDto userDto) {
        // int roleId = userDto.getAccountType().equals(AccountType.JOB_SEEKER) ? 2 : 1;
        log.info("The user:" + userDto.getEmail() + " is saved!");
        return userRepository.save(User.builder()
                .accountName(userDto.getAccountName())
                .email(userDto.getEmail())
                .password(encoder.encode(userDto.getPassword()))
                .phoneNumber(userDto.getPhoneNumber())
                .enabled(true)
                .role(roleService.getRoleById(1))

                .build()).getId();

    }

    public void update(UserDto userDto) {
        log.info("The user:" + userDto.getEmail() + " is updated!");
        userRepository.updateUser(userDto.getId(), userDto.getAccountName(),
                userDto.getEmail(), userDto.getPassword(), userDto.getPhoneNumber());
    }

    public UserDto mapToUserDto(User user) {
        if (user != null) {
            return UserDto.builder()
                    .id(user.getId())
                    .accountName(user.getAccountName())
                    .email(user.getEmail())
                    .password(user.getPassword())
                    .phoneNumber(user.getPhoneNumber())
                    .resetPasswordToken(user.getResetPasswordToken())
                    .build();
        } else {
            return null;
        }
    }


}
