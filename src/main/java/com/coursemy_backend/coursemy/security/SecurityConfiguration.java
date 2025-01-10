package com.coursemy_backend.coursemy.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth ->
                            auth
                                    //allow public access to these endpoints with POST to allow the creation of students/teachers
                                    .requestMatchers(HttpMethod.POST,"/api/teachers", "/api/students").permitAll()

                                    //Requests to create, update and delete a course - Teacher role
                                    .requestMatchers(HttpMethod.POST, "/api/courses").hasRole("TEACHER")
                                    .requestMatchers(HttpMethod.PUT, "/api/courses/*").hasRole("TEACHER")
                                    .requestMatchers(HttpMethod.DELETE, "/api/courses/*").hasRole("TEACHER")

                                    //requests to enroll and drop out of a course - Student
                                    .requestMatchers(HttpMethod.POST, "/api/courses/*/enroll").hasRole("STUDENT")
                                    .requestMatchers(HttpMethod.DELETE, "/api/courses/*/enroll").hasRole("STUDENT")

                                    .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws  Exception{
        return configuration.getAuthenticationManager();
    }
}
