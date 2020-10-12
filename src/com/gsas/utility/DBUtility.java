package com.gsas.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.derby.jdbc.ClientDriver;

public class DBUtility {
	public static Connection getConnection() throws ClassNotFoundException,SQLException{
		Class.forName(ClientDriver.class.getName());
		Connection connection = DriverManager.getConnection("jdbc:derby://localhost:1527/gsasdb","fenil","fenil123");
		/*
		 * Class.forName("com.mysql.jdbc.Driver"); Connection connection =
		 * DriverManager.getConnection("jdbc:mysql://localhost:3306/gsasdb","root",
		 * "root");
		 */
		return connection;
	}
}
