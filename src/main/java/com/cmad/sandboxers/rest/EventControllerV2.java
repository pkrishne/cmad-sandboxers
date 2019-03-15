package com.cmad.sandboxers.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpHeaders;
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
	@RequestMapping(value = "syslog", method = RequestMethod.GET)
	public ResponseEntity<List<EventV1>> getEvents(@RequestParam(value = "hours", required = false) Integer hours,
			@RequestParam(value="filter",required=false) String filter, @RequestParam(value="range",required=false) String range, @CurrentUser UserPrincipal currentUser) {

		Page<EventV1> resultPage;
		
		String search_str="";
		
	
		if(filter!= null && filter.length() > 0) {
			
			
			String[] search_str_array = filter.split(":");
			
			if(search_str_array.length >1) {
				search_str = search_str_array[1].substring(1, search_str_array[1].length()-2);
			}
			
		}

		List<String> device_list = currentUser.getManagedDeviceList();

		HttpHeaders headers = new HttpHeaders();
		headers.add("Access-Control-Expose-Headers", "Content-Range");

		// construct pagination Info here, if provided.
		if (range != null) {
			String start_end_index = range.substring(1, range.length() - 1);

			String[] indexArray = start_end_index.split(",");

			Pageable page = PageRequest.of(Integer.valueOf(indexArray[0]) / 10, 10, Direction.DESC, "timestamp");

			System.out.println("Filter is:"+search_str);
			resultPage = service.getEventsOfDevices(device_list, search_str,page);

			headers.add("Content-Range", "syslog "+start_end_index+"/" + String.valueOf(resultPage.getTotalElements()));

		}

		else {
			resultPage = service.getEventsOfDevices(device_list, "",null);
			headers.add("Content-Range", "syslog /" + String.valueOf(resultPage.getTotalElements()));

		}

		return new ResponseEntity<List<EventV1>>(resultPage.getContent(), headers, HttpStatus.OK);
	}
}
