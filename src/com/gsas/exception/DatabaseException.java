package com.gsas.exception;

public class DatabaseException extends Exception{
	private static final long serialVersionUID = 1L;

	public DatabaseException() {
		super("Oops! Something Went Wrong!");
	}

	public DatabaseException(String message) {
		super(message);
	}
}
