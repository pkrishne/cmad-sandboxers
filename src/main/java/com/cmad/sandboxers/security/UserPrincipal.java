package com.cmad.sandboxers.security;

import com.cmad.sandboxers.model.Operator;
import com.fasterxml.jackson.annotation.JsonIgnore;

import org.bson.types.ObjectId;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class UserPrincipal implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ObjectId id;

	private String name;

	private String username;

	private List<String> managedDeviceList;
	
	private boolean admin;

	@JsonIgnore
	private String email;

	@JsonIgnore
	private String password;

	private Collection<? extends GrantedAuthority> authorities;

	public UserPrincipal(ObjectId id, String name, String username, String email, String password,
			List<String> managedDeviceList, Collection<? extends GrantedAuthority> authorities,boolean admin) {

		this.id = id;
		this.name = name;
		this.username = username;
		this.email = email;
		this.password = password;
		this.managedDeviceList = managedDeviceList;
		this.authorities = authorities;
		this.admin = admin;
	}

	public static UserPrincipal create(Operator user) {
		List<GrantedAuthority> authorities = new ArrayList<>();

		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		
		//If user is admin then add, ROLE_ADMIN
		if(user.isAdmin()) {
			authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}

		List<String> managedDeviceList = user.getManaged_devices_ip_list();

		return new UserPrincipal(user.get_id(), user.getFirst_name(), user.getId(), user.getEmail(), user.getPassword(),

				managedDeviceList, authorities,user.isAdmin());
	}

	public ObjectId getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public String getUsername() {
		return username;
	}

	public List<String> getManagedDeviceList() {
		return managedDeviceList;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public boolean isAdmin() {
		return admin;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserPrincipal that = (UserPrincipal) o;
		return Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {

		return Objects.hash(id);
	}
}