package com.cmad.sandboxers.security;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmad.sandboxers.data.UserRepository;
import com.cmad.sandboxers.model.Operator;

/**
 * 
 * @author pkrishne
 *
 */

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		/*
		 * Let people login with either username or email User user =
		 * userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
		 * .orElseThrow(() -> new
		 * UsernameNotFoundException("User not found with username or email : " +
		 * usernameOrEmail) );
		 */
		Operator user = userRepository.getUserById(username);
		return UserPrincipal.create(user);
	}

	@Transactional
	public UserDetails loadUserById(ObjectId id) {
		Operator user = userRepository.findById(id).get();

		return UserPrincipal.create(user);
	}
}