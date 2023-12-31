package com.ats.model.user;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PassengerInput extends UserInput{
    @NotNull(message = "Passport Number must be provided")
    @NotEmpty(message = "Please provide a valid Passport Number")
    private String passengerPassport;
}
