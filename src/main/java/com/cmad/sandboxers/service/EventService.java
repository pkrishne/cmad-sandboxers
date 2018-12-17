package com.cmad.sandboxers.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmad.sandboxers.api.EventAPI;
import com.cmad.sandboxers.data.EventRepository;
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
	
	public List<EventV1> getEventList(int hours) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getEventCounters(int hours) {
		// TODO Auto-generated method stub
		return null;
	}

}
