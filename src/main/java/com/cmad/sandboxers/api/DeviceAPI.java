package com.cmad.sandboxers.api;

import java.util.List;

import com.cmad.sandboxers.model.Device;

/**
 * 
 * @author pkrishne
 *
 */
public interface DeviceAPI {

	public boolean addNewDevice(Device device);
	
	public boolean removeDevice(String device_ip);
	
	public List<Device> getDeviceList();
}
