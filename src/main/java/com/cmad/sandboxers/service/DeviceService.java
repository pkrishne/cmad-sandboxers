package com.cmad.sandboxers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
		deviceRepo.save(device);
		return true;
	}

	@Override
	public boolean removeDevice(String device_ip) {
		boolean result = deviceRepo.deleteByDeviceIPAddress(device_ip);
		return result;
	}

	@Override
	public Page<Device> getDeviceList(Pageable pageinfo) {
		
		Page<Device> deviceList = deviceRepo.findAll(pageinfo);
		return deviceList;
	}

}
