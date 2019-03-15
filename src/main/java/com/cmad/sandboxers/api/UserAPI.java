package com.cmad.sandboxers.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cmad.sandboxers.model.Operator;

/**
 * 
 * @author pkrishne
 *
 */
public interface UserAPI {

	public Page<Operator> getAllOperators(Pageable page);
	
	public boolean createOperator(Operator operator);
	
	public boolean assignDevicesToOperator(String operator_id, String device_ip);

	public boolean removeOperator(String user_id);
}
