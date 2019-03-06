package com.cmad.sandboxers.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cmad.sandboxers.api.UserAPI;
import com.cmad.sandboxers.data.UserRepository;
import com.cmad.sandboxers.model.Operator;

/**
 * 
 * @author pkrishne
 *
 */
@Service
public class UserService implements UserAPI {

	@Autowired
	private UserRepository userRepo;

	@Override
	public List<Operator> getAllOperators() {
		List<Operator> dataList = userRepo.findAll();
		return dataList;
	}

	@Override
	public boolean createOperator(Operator operator) {
		
		//TODO - check for duplicate user
		//userRepo.find
		userRepo.save(operator);
		return true;
	}

	@Override
	public boolean assignDevicesToOperator(String operator_id, String device_ip) {
		// TODO Auto-generated method stub
		return false;
	}

}
