package com.gsas.utility;

import com.gsas.dao.CitizenDaoImpl;
import com.gsas.dao.CommonDaoImpl;
import com.gsas.dao.EmployeeDaoImpl;
import com.gsas.dao.SchemeDaoImpl;
import com.gsas.service.CitizenServiceImpl;
import com.gsas.service.CommonServiceImpl;
import com.gsas.service.EmployeeServiceImpl;
import com.gsas.service.SchemeServiceImpl;

public class ObjectFactory {
	
public static Object getInstance(LayerType type) {
		
		switch(type) {
		//Factory for Citizen
		case CITIZEN_DAO : return new CitizenDaoImpl();
		case CITIZEN_SERVICE : return new CitizenServiceImpl();
		
		//Factory for Scheme
		case SCHEME_DAO : return new SchemeDaoImpl();
		case SCHEME_SERVICE : return new SchemeServiceImpl();
		
		//Factory for Employee
		case EMPLOYEE_DAO : return new EmployeeDaoImpl();
		case EMPLOYEE_SERVICE : return new EmployeeServiceImpl();
		
		//Factory for Common Methods
		case COMMON_DAO : return new CommonDaoImpl();
		case COMMON_SERVICE : return new CommonServiceImpl();
		}
		return null;
	}

}
