package com.cmad.sandboxers.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

/**
 * 
 * @author pkrishne
 *
 */
public class Device {

	@Id
	private ObjectId _id;
	private String id;
	private String device_name;
	private String device_type;

	public ObjectId get_id() {
		return _id;
	}

	public void set_id(ObjectId _id) {
		this._id = _id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDevice_name() {
		return device_name;
	}

	public void setDevice_name(String device_name) {
		this.device_name = device_name;
	}

	public String getDevice_type() {
		return device_type;
	}

	public void setDevice_type(String device_type) {
		this.device_type = device_type;
	}

}
