package com.cmad.sandboxers.api;

import java.util.List;

import com.cmad.sandboxers.model.Operator;

/**
 * 
 * @author pkrishne
 *
 */
public interface UserAPI {

	public List<Operator> getAllOperators();
	
	public boolean createOperator(Operator operator);
	
	public boolean assignDevicesToOperator(String operator_id, String device_ip);
}
