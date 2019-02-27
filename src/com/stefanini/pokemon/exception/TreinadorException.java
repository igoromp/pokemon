package com.stefanini.pokemon.exception;

import javax.ws.rs.core.Response.Status;

import com.stefanini.pokemon.enums.EnumTreinadorMensagemException;

public class TreinadorException extends GenericException{

	private static final long serialVersionUID = 1L;

	public TreinadorException(EnumTreinadorMensagemException msg) {
		super(msg.getMsg());
	}
	
	public TreinadorException(String msg, Exception e) {
		super(msg, e);
	}
	
	@Override
	public Integer getCode() {
		return Status.BAD_REQUEST.getStatusCode();
	}
}
