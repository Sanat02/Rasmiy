package com.example.demo.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    private final DataSource dataSource;

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        String fetchUserQuery = "select email, password ,enabled \n " +
//                "from users \n" +
//                "where email = ?;";
//
//        String fetchRolesQuery = "select email, role  \n " +
//                "from users u, \n" +
//                "roles r \n" +
//                "where u.email = ? \n" +
//                "and u.role_id=r.id;";
//        auth.jdbcAuthentication()
//                .dataSource(dataSource)
//                .usersByUsernameQuery(fetchUserQuery)
//                .authoritiesByUsernameQuery(fetchRolesQuery)
//                .passwordEncoder(new BCryptPasswordEncoder());
//        ;
//
//
//    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
//                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults())
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/vacancies")
                        .permitAll())
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .permitAll())
                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers(AntPathRequestMatcher.antMatcher("/register")).permitAll()
//                        .requestMatchers(AntPathRequestMatcher.antMatcher("/")).permitAll()
//                        .requestMatchers(AntPathRequestMatcher.antMatcher( "/public-resource/**")).permitAll()
//                        .requestMatchers(AntPathRequestMatcher.antMatcher("/token")).permitAll()
//                        .requestMatchers(AntPathRequestMatcher.antMatcher("/reset")).permitAll()
//                        .requestMatchers(AntPathRequestMatcher.antMatcher("/vacancies")).permitAll()
//                        .requestMatchers(AntPathRequestMatcher.antMatcher("/vacancies/{userId}")).permitAll()
                                .requestMatchers(AntPathRequestMatcher.antMatcher("/resumes")).hasAuthority("EMPLOYER")
                                .requestMatchers(AntPathRequestMatcher.antMatcher("/jobresumes")).hasAuthority("JOB_SEEKER")
                                .requestMatchers(AntPathRequestMatcher.antMatcher("/click/{jobResumeId}")).hasAuthority("JOB_SEEKER")
                                .requestMatchers(AntPathRequestMatcher.antMatcher("/vacancies/{userId}/chat")).hasAuthority("JOB_SEEKER")
                                .requestMatchers(AntPathRequestMatcher.antMatcher("/jobresumes/add")).hasAuthority("EMPLOYER")
                                .requestMatchers(AntPathRequestMatcher.antMatcher("/jobresumes/{jobResumeId}/delete")).hasAuthority("EMPLOYER")
                                .requestMatchers(AntPathRequestMatcher.antMatcher("/jobresumes/edit/{jobResumeId}")).hasAuthority("EMPLOYER")
                                .requestMatchers(AntPathRequestMatcher.antMatcher("/resumes/add")).hasAuthority("JOB_SEEKER")
                                .requestMatchers(AntPathRequestMatcher.antMatcher("/resumes/{resumeId}/education")).hasAuthority("JOB_SEEKER")
                                .requestMatchers(AntPathRequestMatcher.antMatcher("/resumes/{resumeId}/experience")).hasAuthority("JOB_SEEKER")
                                .requestMatchers(AntPathRequestMatcher.antMatcher("/resumes/{resumeId}/contacts")).hasAuthority("JOB_SEEKER")
                                .requestMatchers(AntPathRequestMatcher.antMatcher("/resumes/{resumeId}/delete")).hasAuthority("JOB_SEEKER")
                                .requestMatchers(AntPathRequestMatcher.antMatcher("/resumes/edit/{resumeId}")).hasAuthority("JOB_SEEKER")
                                .requestMatchers(AntPathRequestMatcher.antMatcher("/resumes/info/{resumeId}")).hasAuthority("EMPLOYER")
//                        .requestMatchers(AntPathRequestMatcher.antMatcher("/forgot")).permitAll()
                                .requestMatchers(AntPathRequestMatcher.antMatcher("/chat/**")).hasAuthority("JOB_SEEKER")
                                .requestMatchers(AntPathRequestMatcher.antMatcher("/profileimg/**")).authenticated()
                                .requestMatchers(AntPathRequestMatcher.antMatcher("/jobresumes/**")).authenticated()
                                .requestMatchers(AntPathRequestMatcher.antMatcher("/resumes/**")).authenticated()
                                .requestMatchers(AntPathRequestMatcher.antMatcher("/vacancies/{userId}/chat")).authenticated()
                                .requestMatchers(AntPathRequestMatcher.antMatcher("/profile/**")).authenticated()
                                .requestMatchers(AntPathRequestMatcher.antMatcher("/images/**")).authenticated()
                                .requestMatchers(AntPathRequestMatcher.antMatcher("/click/**")).authenticated()
                                .anyRequest().permitAll()

                );
        return http.build();
    }


}
