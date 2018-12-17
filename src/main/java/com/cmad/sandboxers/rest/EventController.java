package com.cmad.sandboxers.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cmad.sandboxers.model.EventCounters;
import com.cmad.sandboxers.model.EventV1;

/**
 * 
 * @author pkrishne
 *
 */
@RestController
@RequestMapping(value="/event/")
public class EventController {

	/**
	 * 
	 * @param hours - it's optional, by default, 24 is the value (i.e 24 hours data will be returned)
	 * @return
	 */
	@RequestMapping(value = "v1/counters/", method = RequestMethod.GET)
	public ResponseEntity<EventCounters> getEventCounters(@RequestParam(value="hours",required=false) Integer hours) {
		
		// TODO -- logic to get event counter here.
		return new ResponseEntity<EventCounters>(HttpStatus.OK);
	}

	/**
	 * 
	 * @param hours - it's optional, by default, 24 is the value (i.e 24 hours data will be returned)
	 * @return
	 */
	@RequestMapping(value = "v1/", method = RequestMethod.GET)
	public ResponseEntity<EventV1> getEvents(@RequestParam(value="hours",required=false) Integer hours) {
		
		// TODO -- logic to get events here.
		return new ResponseEntity<EventV1>(HttpStatus.OK);
	}
}
