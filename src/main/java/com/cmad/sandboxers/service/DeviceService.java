package com.cmad.sandboxers.service;

import java.util.List;

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
		deviceRepo.deleteByDeviceIPAddress(device_ip);
		return true;
	}

	@Override
	public Page<Device> getDeviceList(Pageable pageinfo,boolean isAdmin,List<String> userDeviceList) {
		
		Page<Device> deviceList;
		if(isAdmin) {
			deviceList = deviceRepo.findAll(pageinfo);
			
		}
		else {
			deviceList = deviceRepo.findByIPAddressIn(userDeviceList,pageinfo);
		}
		
		return deviceList;
	}

}
