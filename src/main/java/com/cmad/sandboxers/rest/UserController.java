package com.cmad.sandboxers.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cmad.sandboxers.api.UserAPI;
import com.cmad.sandboxers.model.Operator;

/**
 * 
 * @author pkrishne
 *
 */
@RestController
@CrossOrigin
@RequestMapping("v2/user/")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class UserController {

	@Autowired
	private UserAPI userAPI;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Boolean> addUser(@RequestBody Operator user) {

		boolean result = userAPI.createOperator(user);
		return new ResponseEntity<Boolean>(result, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Operator>> getAllUsers() {

		List<Operator> list = userAPI.getAllOperators();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

}
