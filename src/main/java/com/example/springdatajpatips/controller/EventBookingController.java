package com.example.springdatajpatips.controller;

import com.example.springdatajpatips.constants.CommonApiUrl;
import com.example.springdatajpatips.response.EventBookingsGraphData;
import com.example.springdatajpatips.response.Response;
import com.example.springdatajpatips.service.EventBookingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(CommonApiUrl.API_VERSION_1)
public class EventBookingController {

	@Autowired
	EventBookingService eventBookingService;

	@GetMapping("/eventbooking")
	public Response<List<EventBookingsGraphData>> getEventBookingData() {
		return Response.getSuccessResponse(eventBookingService.fetchEventBookingsStats());
	}
}
