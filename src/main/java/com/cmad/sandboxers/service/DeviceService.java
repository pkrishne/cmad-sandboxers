package com.cmad.sandboxers.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmad.sandboxers.api.DeviceAPI;
import com.cmad.sandboxers.data.DeviceRepository;
import com.cmad.sandboxers.model.Device;

/**
 * 
 * @author pkrishne
 *
 */
@Service
public class DeviceService implements DeviceAPI {

	@Autowired
	private DeviceRepository deviceRepo;

	@Override
	public boolean addNewDevice(Device device) {
		
		//TODO -- add check, before inserting, if device is already present.
		deviceRepo.insert(device);
		return true;
	}

	@Override
	public boolean removeDevice(String device_ip) {
		boolean result = deviceRepo.deleteByDeviceIPAddress(device_ip);
		return result;
	}

	@Override
	public List<Device> getDeviceList() {
		
		List<Device> deviceList = deviceRepo.findAll();
		return deviceList;
	}

}
