package com.example.springdatajpatips.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;


@Entity
@Table(name="event",uniqueConstraints={@UniqueConstraint(columnNames={"event_name"})},
indexes={
@Index(name = "event_index_1", columnList = "event_start_date"),
@Index(name = "event_index_2", columnList = "event_end_date"),
@Index(name = "event_index_3", columnList = "enabled")
})
public class Event extends AuditEntity {

    @NotNull
    @Column(name="event_name",length=200)
    private String eventName;
    
    @Column(name="event_description",length=1024)
    private String eventDescription;

    @NotNull
    @Column(name="event_start_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    
    @NotNull
    @Column(name="event_end_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;
    
    @NotNull
    @Column(name="booking_start_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date bookingStartDate;
    
    @NotNull
    @Column(name="booking_end_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date bookingEndDate;
    
    private boolean enabled;
    
    @Lob
    @Column(name = "notes",length = 5000)
    @NotNull
    private String notes;
    
    
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getBookingStartDate() {
        return bookingStartDate;
    }

    public void setBookingStartDate(Date bookingStartDate) {
        this.bookingStartDate = bookingStartDate;
    }

    public Date getBookingEndDate() {
        return bookingEndDate;
    }

    public void setBookingEndDate(Date bookingEndDate) {
        this.bookingEndDate = bookingEndDate;
    }
    
    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }
   
}

