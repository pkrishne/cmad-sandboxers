package com.cmad.sandboxers.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cmad.sandboxers.model.Device;

/**
 * 
 * @author pkrishne
 *
 */
public interface DeviceAPI {

	boolean addNewDevice(Device device);
	
	boolean removeDevice(String device_ip);

	Page<Device> getDeviceList(Pageable pageinfo);
}
