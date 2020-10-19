package com.gsas.exception;

public class InvalidSequenceException extends Exception{


	private static final long serialVersionUID = 1L;

	public InvalidSequenceException() {
		super("Invalid Sequence");
	}

	public InvalidSequenceException(String message) {
		super(message);
	}
	
}
