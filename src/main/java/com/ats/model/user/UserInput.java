package com.ats.model.user;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public abstract class UserInput {
    @NotNull(message = "User name must be provided")
    @NotEmpty(message = "User name can't be left blank")
    @NotBlank
    private String userFullName;

    @NotNull(message = "User password must be provided")
    @NotEmpty(message = "User password can't be left blank")
    private String userPassword;

    @NotNull(message = "User email must be provided")
    @NotEmpty(message = "User email can't be left blank")
    private String userEmail;

    @NotNull(message = "User contact must be provided")
    @NotEmpty(message = "User contact can't be left blank")
    private String userContact;
}
