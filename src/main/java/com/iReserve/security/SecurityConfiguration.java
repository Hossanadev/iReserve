package com.iReserve.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

        @Bean
        public UserDetailsManager userDetailsManager(DataSource dataSource) {
            JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
            jdbcUserDetailsManager.setUsersByUsernameQuery("SELECT username, password, enabled FROM users WHERE username=?");
            jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("SELECT u.username, r.name FROM users u INNER JOIN roles r ON u.role_id = r.id WHERE u.username=?");
            return jdbcUserDetailsManager;
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

        @Bean
        SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            return http.authorizeHttpRequests(auth -> auth
                    .requestMatchers("/", "auth/sign-up", "auth/login", "/sign-up", "/login", "/logout").permitAll()
                    .requestMatchers("/admin/**").hasRole("ADMIN")
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