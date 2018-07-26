package com.example.springdatajpatips.model;


import com.example.springdatajpatips.response.EventBookingsGraphData;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@SqlResultSetMapping(name="eventBookingGraphMapping",
		classes = {
				@ConstructorResult(
						targetClass = EventBookingsGraphData.class,
						columns = {
								@ColumnResult(name = "booking_date", type = Long.class),
								@ColumnResult(name = "booking_count", type = Long.class),
								@ColumnResult(name = "seats_booked", type = Long.class)
						})
		})
public class EventBooking extends AuditEntity{

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name= "event_id")
	private Event event;

	@NotNull
	private String subEventIdentifier;

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="booking_code")
	private BookingCode bookingCode;

	@NotNull
	private Long bookedGuestCount;

	//at the time of booking it will be null, will be updated while marking attendance
	private Long checkedInGuestCount;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "attendance_marked_by")
	private User attendanceMarkedBy;

	//will be updated at the time of marking the attendance
	@Temporal(TemporalType.TIMESTAMP)
	private Date checkedInTime;

	public User getAttendanceMarkedBy() {
		return attendanceMarkedBy;
	}

	public void setAttendanceMarkedBy(User attendanceMarkedBy) {
		this.attendanceMarkedBy = attendanceMarkedBy;
	}

	public BookingCode getBookingCode() {
		return bookingCode;
	}

	public void setBookingCode(BookingCode bookingCode) {
		this.bookingCode = bookingCode;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public Date getCheckedInTime() {
		return checkedInTime;
	}

	public void setCheckedInTime(Date checkedInTime) {
		this.checkedInTime = checkedInTime;
	}

	public Long getBookedGuestCount() {
		return bookedGuestCount;
	}

	public void setBookedGuestCount(Long bookedGuestCount) {
		this.bookedGuestCount = bookedGuestCount;
	}

	public Long getCheckedInGuestCount() {
		return checkedInGuestCount;
	}

	public void setCheckedInGuestCount(Long checkedInGuestCount) {
		this.checkedInGuestCount = checkedInGuestCount;
	}

	public String getSubEventIdentifier() {
		return subEventIdentifier;
	}

	public void setSubEventIdentifier(String subEventIdentifier) {
		this.subEventIdentifier = subEventIdentifier;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


}
