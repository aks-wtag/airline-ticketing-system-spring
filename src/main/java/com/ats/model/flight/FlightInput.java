package com.ats.model.flight;

import com.ats.model.airline.Airline;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class FlightInput {
    @NotNull(message = "Departure Date must be provided")
    @NotEmpty(message = "Departure Date can not be empty")
    private String departureDate;

    @NotNull(message = "Departure Time must be provided")
    @NotEmpty(message = "Departure Time can not be empty")
    private String departureTime;

    @NotNull(message = "Arrival Date must be provided")
    @NotEmpty(message = "Arrival Date can not be empty")
    private String arrivalDate;

    @NotNull(message = "Arrival Time must be provided")
    @NotEmpty(message = "Arrival Time can not be empty")
    private String arrivalTime;

    @NotNull(message = "Departure Location must be provided")
    @NotEmpty(message = "Departure Location can not be empty")
    private String departureLocation;

    @NotNull(message = "Arrival Location must be provided")
    @NotEmpty(message = "Arrival Location can not be empty")
    private String arrivalLocation;

    @NotNull(message = "Fare must be provided")
    @Min(value = 1, message = "Fare should be greater than zero")
    private double fare;

    @NotNull(message = "Airline ID must be provided")
    @NotEmpty(message = "Airline ID can not be empty")
    private String airlineId;

    @Valid
    private Airline airline;
}
