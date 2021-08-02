package com.jwt.study.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Builder
@Data
public class LoginDto {

    @NotNull
    @Size(min = 3, max = 50)
    private String username;

    @NotNull
    @Size(min = 3, max =100)
    private String password;
}
