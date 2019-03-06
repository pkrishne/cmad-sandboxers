package com.cmad.sandboxers.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cmad.sandboxers.model.EventCounters;
import com.cmad.sandboxers.model.EventV1;
import com.cmad.sandboxers.security.CurrentUser;
import com.cmad.sandboxers.security.UserPrincipal;
import com.cmad.sandboxers.service.EventService;

/**
 * 
 * @author pkrishne
 *
 */
@RestController
@RequestMapping(value = "v2/")
@CrossOrigin
public class EventControllerV2 {

	@Autowired
	private EventService service;

	/**
	 * 
	 * @param hours - it's optional, by default, 24 is the value (i.e 24 hours data
	 *              will be returned)
	 * @return
	 */
	@RequestMapping(value = "counter/", method = RequestMethod.GET)
	public ResponseEntity<EventCounters> getEventCounters(
			@RequestParam(value = "hours", required = false) Integer hours, Pageable pageable,
			@CurrentUser UserPrincipal currentUser) {

		List<String> device_list = currentUser.getManagedDeviceList();

		EventCounters ec = service.getEventCountersOfDevices(device_list);
		return new ResponseEntity<EventCounters>(ec, HttpStatus.OK);
	}

	/**
	 * 
	 * @param hours - it's optional, by default, 24 is the value (i.e 24 hours data
	 *              will be returned)
	 * @return
	 */
	@RequestMapping(value = "syslog/", method = RequestMethod.GET)
	public ResponseEntity<List<EventV1>> getEvents(@RequestParam(value = "hours", required = false) Integer hours,
			Pageable pageinfo, @CurrentUser UserPrincipal currentUser) {

		List<EventV1> e;

		List<String> device_list = currentUser.getManagedDeviceList();

		System.out.println("Roles are:" + currentUser.getAuthorities());

		e = service.getEventsOfDevices(device_list);

		return new ResponseEntity<List<EventV1>>(e, HttpStatus.OK);
	}
}
