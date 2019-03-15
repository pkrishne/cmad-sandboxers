package com.cmad.sandboxers.data;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

	@Query(value = "{'id' : ?0}", delete = true)
	public void deleteByDeviceIPAddress(String ip_address);
	
	@Query(value = "{ 'id': { $all: ?0 } }")
	public Page<Device> findByIPAddressIn(List<String> ip_address_list,Pageable pageinfo);
}
