package com.example.springdatajpatips.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class BookingCode extends BaseEntity{

    @OneToMany(mappedBy="bookingCode",fetch= FetchType.LAZY)
    private List<EventBooking> eventBooking;


    public List<EventBooking> getEventBooking() {
        return eventBooking;
    }

    public void setEventBooking(List<EventBooking> eventBooking) {
        this.eventBooking = eventBooking;
    }

   
}
