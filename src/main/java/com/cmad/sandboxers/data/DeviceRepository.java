package com.cmad.sandboxers.data;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import org.springframework.stereotype.Repository;

import com.cmad.sandboxers.model.Device;

/**
 * 
 * @author pkrishne
 *
 */
@Repository
public interface DeviceRepository extends MongoRepository<Device, ObjectId> {

	@Query(value = "{'ip_address' : ?0}", delete = true)
	public boolean deleteByDeviceIPAddress(String ip_address);
}
