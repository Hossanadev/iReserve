package com.iReserve.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

        @Bean
        public UserDetailsManager userDetailsManager(DataSource dataSource) {
            return new JdbcUserDetailsManager(dataSource);
        }

        @Bean
        SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            return http.authorizeHttpRequests(auth -> auth
                    .requestMatchers("/", "auth/sign-up", "auth/login", "/sign-up", "/login", "/logout").permitAll()
                    .anyRequest().authenticated()
            )
            .formLogin(form ->
                    form.loginPage("/login")
                        .failureUrl("/login?error")
                        .defaultSuccessUrl("/app/index", true))
            .logout(config -> config.logoutSuccessUrl("/"))
            .build();
        }
}