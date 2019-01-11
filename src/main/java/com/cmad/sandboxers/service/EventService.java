package com.cmad.sandboxers.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.cmad.sandboxers.api.EventAPI;
import com.cmad.sandboxers.data.EventRepository;
import com.cmad.sandboxers.model.EventCounters;
import com.cmad.sandboxers.model.EventType;
import com.cmad.sandboxers.model.EventV1;

/**
 * 
 * @author pkrishne
 *
 */
@Service
public class EventService implements EventAPI {

	@Autowired
	private EventRepository eventRepo;
	
	public Page<EventV1> getEventList(int hours) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getEventCounters(int hours) {
		long millis=System.currentTimeMillis();
		long inmilli=hours*3600000;

		Date dt=new Date(millis-inmilli);
		EventCounters ecnt=new EventCounters();

		List<EventV1> eventList=eventRepo.findByDateAfter(dt);
		for (EventV1 e : eventList) {
			EventType et= e.getEvent_type();
			switch(et.getType()) {
				case "ERROR":
					ecnt.incError();
				break;
				case "WARNING":
					ecnt.incWarn();
				break;
				case "NOTIFICATION":
					ecnt.incNotif();
				break;

			}
		}

		return ecnt.toString();
	}

}
