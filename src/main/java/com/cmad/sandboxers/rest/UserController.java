package com.cmad.sandboxers.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
@RequestMapping("v2/user")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class UserController {

	@Autowired
	private UserAPI userService;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Boolean> addUser(@RequestBody Operator user) {

		boolean result = userService.createOperator(user);
		return new ResponseEntity<Boolean>(result, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Operator>> getAllUsers(@RequestParam(value = "range", required = false) String range) {

		Page<Operator> resultPage;
		HttpHeaders headers = new HttpHeaders();
		headers.add("Access-Control-Expose-Headers", "Content-Range");

		// construct pagination Info here, if provided.
		if (range != null) {
			String start_end_index = range.substring(1, range.length() - 1);

			String[] indexArray = start_end_index.split(",");

			Pageable page = PageRequest.of(Integer.valueOf(indexArray[0]) / 10, 10, Direction.DESC, "id");

			resultPage = userService.getAllOperators(page);

			headers.add("Content-Range",
					"user " + start_end_index + "/" + String.valueOf(resultPage.getTotalElements()));

		}

		else {
			resultPage = userService.getAllOperators(null);
			headers.add("Content-Range", "user /" + String.valueOf(resultPage.getTotalElements()));

		}
		return new ResponseEntity<>(resultPage.getContent(), headers, HttpStatus.OK);
	}

}
