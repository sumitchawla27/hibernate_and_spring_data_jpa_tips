package com.example.springdatajpatips.service;


import com.example.springdatajpatips.constants.AppConstants;
import com.example.springdatajpatips.response.EventBookingsGraphData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import ma.glasnost.orika.MapperFacade;


@Service
public class EventBookingService {


	@Autowired
	MapperFacade mapperFacade;

	@Autowired
	EntityManager entityManager;


	public List<EventBookingsGraphData> fetchEventBookingsStats() {
		String query = "select UNIX_TIMESTAMP(DATE (eb.created_ts))*1000 as booking_date,count(DISTINCT eb.booking_code) as booking_count ,sum(eb.booked_guest_count) as seats_booked from event_booking as eb where date(eb.created_ts) >= CURDATE() - INTERVAL ?1 DAY GROUP BY YEAR(eb.created_ts),MONTH(eb.created_ts),DAY(eb.created_ts),booking_date";
		Query emquery = entityManager.createNativeQuery(query, "eventBookingGraphMapping");
		emquery.setParameter(1,AppConstants.LAST_N_DAYS);
		Map<Long,List<Long>> map = new TreeMap();
		Calendar calInstance = Calendar.getInstance();
		calInstance.set(Calendar.MILLISECOND, 0);
		calInstance.set(Calendar.SECOND, 0);
		calInstance.set(Calendar.MINUTE, 0);
		calInstance.set(Calendar.HOUR_OF_DAY, 0);
		Date todayBeginningTime = calInstance.getTime();

		List<Long> rawData = new ArrayList<>();
		rawData.add(0L);
		rawData.add(0L);

		//populating map with timestamps of last N days
		for (int i = 1; i<= AppConstants.LAST_N_DAYS; i++){
			Calendar cal = new GregorianCalendar();
			cal.setTime(todayBeginningTime);
			cal.add(Calendar.DATE, -i);
			map.put(cal.getTimeInMillis(),rawData);
		}
		List<EventBookingsGraphData> eventBookingsGraphDataList = emquery.getResultList();

		//if map contains the bookingTimeinMillis then update the values with real data
		for(EventBookingsGraphData eventBookingsGraphData: eventBookingsGraphDataList){
			Long bookingTimeInMillis = eventBookingsGraphData.getBookingDate();
			if(map.containsKey(bookingTimeInMillis)){
				List<Long> data = new ArrayList<>();
				data.add(eventBookingsGraphData.getBookingCount());
				data.add(eventBookingsGraphData.getSeatsBooked());
				map.put(bookingTimeInMillis,data);
			}
		}
		List<EventBookingsGraphData> eventBookingsGraphDataDTOList = new ArrayList<>();
		Iterator it = map.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Long,List<Long>> pair = (Map.Entry)it.next();
			EventBookingsGraphData eventBookingsGraphData = new EventBookingsGraphData();
			eventBookingsGraphData.setBookingDate(pair.getKey());
			eventBookingsGraphData.setBookingCount(pair.getValue().get(0));
			eventBookingsGraphData.setSeatsBooked(pair.getValue().get(1));
			eventBookingsGraphDataDTOList.add(eventBookingsGraphData);
			it.remove(); // avoids a ConcurrentModificationException
		}

		return eventBookingsGraphDataDTOList;
	}

}