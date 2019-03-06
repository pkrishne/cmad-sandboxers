package com.cmad.sandboxers.rest;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
@RequestMapping(value = "v2/device/")
@CrossOrigin
public class DeviceController {

	@Autowired
	private DeviceAPI deviceAPI;

	//@RequestMapping(method = RequestMethod.POST,consumes=org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Boolean> addDevice(@RequestBody Device device) {
	
		boolean result = deviceAPI.addNewDevice(device);
		return new ResponseEntity<Boolean>(result, HttpStatus.OK);
	}
    
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Device>> getAllDevices(@CurrentUser UserPrincipal currentUser){
		
		System.out.println(currentUser.getManagedDeviceList());
		List<Device> list = deviceAPI.getDeviceList();
		return new ResponseEntity<>(list,HttpStatus.OK);
	}

}
