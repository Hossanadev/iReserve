package com.iReserve.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UserDto {
    @NotEmpty(message = "*Required!")
    private String username;

    @NotEmpty(message = "*Required!")
    private String password;
}