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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cmad.sandboxers.api.DeviceAPI;
import com.cmad.sandboxers.model.Device;
import com.cmad.sandboxers.security.CurrentUser;
import com.cmad.sandboxers.security.UserPrincipal;

/**
 * 
 * @author pkrishne
 *
 */
@RestController
@RequestMapping(value = "v2/device")
@PreAuthorize("hasRole('ROLE_ADMIN')")
@CrossOrigin
public class DeviceController {

	@Autowired
	private DeviceAPI deviceService;

	// @RequestMapping(method =
	// RequestMethod.POST,consumes=org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Boolean> addDevice(@RequestBody Device device) {

		boolean result = deviceService.addNewDevice(device);
		return new ResponseEntity<Boolean>(result, HttpStatus.OK);
	}

	@RequestMapping(value="/{device_id}",method=RequestMethod.DELETE)
	public ResponseEntity<Boolean> deleteDevice(@PathVariable("device_id") String device_id){
		
		boolean result = deviceService.removeDevice(device_id);
		return new ResponseEntity<Boolean>(result, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Device>> getAllDevices(@CurrentUser UserPrincipal currentUser,
			@RequestParam(value = "range", required = false) String range) {

		Page<Device> resultPage;
		HttpHeaders headers = new HttpHeaders();
		headers.add("Access-Control-Expose-Headers", "Content-Range");

		// see if user is admin.
		boolean admin = currentUser.isAdmin();
		List<String> userDevList = currentUser.getManagedDeviceList();
		// construct pagination Info here, if provided.
		if (range != null) {
			String start_end_index = range.substring(1, range.length() - 1);

			String[] indexArray = start_end_index.split(",");

			Pageable page = PageRequest.of(Integer.valueOf(indexArray[0]) / 10, 10, Direction.DESC, "id");

			resultPage = deviceService.getDeviceList(page, admin, userDevList);

			headers.add("Content-Range",
					"device " + start_end_index + "/" + String.valueOf(resultPage.getTotalElements()));

		}

		else {
			resultPage = deviceService.getDeviceList(null,false,null);
			headers.add("Content-Range", "syslog /" + String.valueOf(resultPage.getTotalElements()));

		}
		return new ResponseEntity<>(resultPage.getContent(), headers, HttpStatus.OK);
	}

}
