package com.gsas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.gsas.exception.AuthenticationException;
import com.gsas.exception.DatabaseException;
import com.gsas.model.LoginVO;
import com.gsas.utility.DBUtility;

public class EmployeeDaoImpl implements EmployeeDao {

	@Override
	public LoginVO Authenticate(String userName, String password) throws AuthenticationException, DatabaseException {
		LoginVO loginVO = null;
		try {
			Connection connection = DBUtility.getConnection();
			PreparedStatement fetchStatement = connection.prepareStatement("select * from login_credential where user_name = ? and password = ? and is_employee=true");
			fetchStatement.setString(1, userName);
			fetchStatement.setString(2, password);
			
			
			ResultSet resultSet = fetchStatement.executeQuery();
			if(resultSet.next()) {
				loginVO = new LoginVO();
				loginVO.setLoginId(resultSet.getLong("login_id"));
				loginVO.setUserName(resultSet.getString("user_name"));
				loginVO.setPassword(resultSet.getString("password"));
				loginVO.setEmployee(resultSet.getBoolean("is_employee"));
			}
			resultSet.close();
			fetchStatement.close();
			connection.close();
			if(loginVO == null) {
				throw new AuthenticationException("Sorry username or Password is Incorrect");
			} 
		} catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			throw new DatabaseException("Something Went Wrong");
		}
		return loginVO;
	}
	
	
}
