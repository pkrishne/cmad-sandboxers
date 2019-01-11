package com.cmad.sandboxers.rest;

import com.cmad.sandboxers.model.EventCounters;
import com.cmad.sandboxers.model.EventV1;
import com.cmad.sandboxers.service.EventService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 
 * @author pkrishne
 *
 */

@RestController
public class EventController {

	@Autowired
	private EventService service;
	/**
	 * 
	 * @param hours - it's optional, by default, 24 is the value (i.e 24 hours data will be returned)
	 * @return
	 */
	@RequestMapping(value = "/counters", method = RequestMethod.GET)
	public ResponseEntity<EventCounters> getEventCounters(@RequestParam(value="hours",required=false) Integer hours, Pageable pageable) {
		EventCounters c=new EventCounters();
		c.setError_count(2);
		c.setNotification_count(4);
		c.setWarning_count(6);
		System.out.println("Reached point");
		// TODO -- logic to get event counter here.
		return new ResponseEntity<EventCounters>(c,HttpStatus.OK);
	}

	/**
	 * 
	 * @param hours - it's optional, by default, 24 is the value (i.e 24 hours data will be returned)
	 * @return
	 */
	@RequestMapping(value = "/events", method = RequestMethod.GET)
	public ResponseEntity<String> getEvents(@RequestParam(value="hours",required=false) Integer hours) {
		String ec=service.getEventCounters(hours);
		return new ResponseEntity<String>(ec,HttpStatus.OK);
	}
}
