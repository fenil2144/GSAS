package com.gsas.exception;

public class DuplicateUserException extends Exception{

	private static final long serialVersionUID = 1L;

	public DuplicateUserException() {
		super("Username, Adhar Or Pan already registered.");
	}

	public DuplicateUserException(String message) {
		super(message);
	}

}
