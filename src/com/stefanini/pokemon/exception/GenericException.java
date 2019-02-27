package com.stefanini.pokemon.exception;

import javax.ws.rs.core.Response.Status;

public class GenericException extends Exception{

	private static final long serialVersionUID = 1L;

	
	public GenericException() {
	}
	
	public GenericException(String msg) {
		super(msg);
	}

	public GenericException(String msg, Exception e) {
		super(msg, e);
	}

	public Integer getCode() {
		return Status.BAD_REQUEST.getStatusCode();
	}
}
