package com.ats.model.user;

import com.ats.model.booking.Booking;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
public class Passenger extends User implements Serializable {
    @Column(nullable = false)
    private String passengerPassport;

    @OneToMany(mappedBy = "passenger")
    @JsonIgnore
    private List<Booking> bookings;
}
