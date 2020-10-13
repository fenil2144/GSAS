package com.gsas.service;

import com.gsas.exception.AuthenticationException;
import com.gsas.exception.DatabaseException;
import com.gsas.model.LoginVO;

public interface EmployeeService {
	public LoginVO Authenticate(String userName,String password) throws AuthenticationException, DatabaseException;

}
