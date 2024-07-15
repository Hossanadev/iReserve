package com.iReserve.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class UserRequest {
    @NotEmpty(message = "Username is required!")
    private String username;

    @Length(min = 8, message = "Password must be at least 8 characters!")
    @NotEmpty(message = "Password is required!")
    private String password;
}