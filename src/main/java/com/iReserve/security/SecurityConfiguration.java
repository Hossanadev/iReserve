package com.iReserve.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
        SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            return http.authorizeHttpRequests(auth -> auth
                    .requestMatchers("/", "/sign-up", "/login", "/logout").permitAll()
                    .anyRequest().authenticated()
            )
            .formLogin(form -> form.defaultSuccessUrl("/app/index", true))
            .logout(config -> config.logoutSuccessUrl("/"))
            .build();
        }
}