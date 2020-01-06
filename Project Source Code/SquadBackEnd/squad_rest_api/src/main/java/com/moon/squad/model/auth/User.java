package com.moon.squad.model.auth;

import org.hibernate.validator.constraints.Length;
import org.jetbrains.annotations.NotNull;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class User {

    @NotNull
    @NotBlank
    @Email
    private String email;
    @Length (min = 6, max = 32)
    private String password;
}
