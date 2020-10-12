package com.gsas.exception;

public class CitizenNotFoundException extends Exception{

	private static final long serialVersionUID = 1L;

	public CitizenNotFoundException() {
		super("Sorry, Citizen Not Found.");
	}

	public CitizenNotFoundException(String message) {
		super(message);
	}
	

}
