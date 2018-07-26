package com.example.springdatajpatips.response;

public class EventBookingsGraphData {

	private Long bookingDate;
	private Long bookingCount;
	private Long seatsBooked;

	public EventBookingsGraphData(Long bookingDate, Long bookingCount, Long seatsBooked) {
		this.bookingDate = bookingDate;
		this.bookingCount = bookingCount;
		this.seatsBooked = seatsBooked;
	}

	public EventBookingsGraphData() {
	}

	public Long getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(Long bookingDate) {
		this.bookingDate = bookingDate;
	}

	public Long getBookingCount() {
		return bookingCount;
	}

	public void setBookingCount(Long bookingCount) {
		this.bookingCount = bookingCount;
	}

	public Long getSeatsBooked() {
		return seatsBooked;
	}

	public void setSeatsBooked(Long seatsBooked) {
		this.seatsBooked = seatsBooked;
	}
}
