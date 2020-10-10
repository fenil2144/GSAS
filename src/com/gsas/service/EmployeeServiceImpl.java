package com.gsas.service;

import com.gsas.dao.EmployeeDao;
import com.gsas.exception.AuthenticationException;
import com.gsas.exception.DatabaseException;
import com.gsas.model.LoginVO;
import com.gsas.utility.LayerType;
import com.gsas.utility.ObjectFactory;

public class EmployeeServiceImpl implements EmployeeService {
	
	private EmployeeDao employeeDao = null;
	

	public EmployeeServiceImpl() {
		employeeDao = (EmployeeDao) ObjectFactory.getInstance(LayerType.EMPLOYEE_DAO);
	}


	@Override
	public LoginVO Authenticate(String userName, String password) throws AuthenticationException, DatabaseException {
		LoginVO loginVO = employeeDao.Authenticate(userName, password);
		if(loginVO == null) {
			throw new AuthenticationException("Sorry something went wrong");
		}
		return loginVO;
	}
	
}
