package com.cmad.sandboxers.model;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * 
 * @author pkrishne
 *
 */
public class Operator {

	@Id
	private ObjectId _id;
	
	@Field("user_id")
	private String id;
	
	private String first_name;
	private String last_name;
	private String password;
	private String email;
	private boolean isAdmin;
	private List<String> managed_devices_ip_list;

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

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<String> getManaged_devices_ip_list() {
		return managed_devices_ip_list;
	}

	public void setManaged_devices_ip_list(List<String> managed_devices_ip_list) {
		this.managed_devices_ip_list = managed_devices_ip_list;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

}
