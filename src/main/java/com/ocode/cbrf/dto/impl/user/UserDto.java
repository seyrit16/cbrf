package com.ocode.cbrf.dto.impl.user;

import com.ocode.cbrf.dto.Dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto implements Dto {
    @NotNull
    private Long id;

    @NotNull
    private String login;

    @NotNull
    private String password;

    @NotNull
    private String role;

    @NotNull
    private Boolean isActive;

    @NotNull
    private Boolean isDeleted;
}
