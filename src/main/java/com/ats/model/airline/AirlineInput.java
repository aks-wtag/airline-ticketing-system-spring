package com.ats.model.airline;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AirlineInput {
    @NotNull(message = "Airline ID name must be provided")
    @NotEmpty(message = "Airline ID can not be empty")
    private String airlineId;

    @NotNull(message = "Airline name must be provided")
    @NotEmpty(message = "Airline name can not be empty")
    private String airlineName;

    @NotNull(message = "Airline seats must be provided")
    @Min(value = 1, message = "Number of seats should be greater than zero")
    private int numberOfSeats;
}
