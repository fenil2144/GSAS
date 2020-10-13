package com.gsas.exception;

public class AuthenticationException extends Exception {
	private static final long serialVersionUID = 1L;

	public AuthenticationException() {
		super("Authentication Failed.");
	}

	public AuthenticationException(String message) {
		super(message);
	}

}
