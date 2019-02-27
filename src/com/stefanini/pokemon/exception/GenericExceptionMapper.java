package com.stefanini.pokemon.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;

public class GenericExceptionMapper implements ExceptionMapper<Exception> {

	@Override
	public Response toResponse(Exception exception) {
		Mensagem mensagem = new Mensagem();

		if (exception instanceof GenericException) {
			GenericException e = (GenericException) exception;
			mensagem.setStatus(e.getCode());
		}

		if (exception instanceof NotAuthorizedException) {
			mensagem.setStatus(Status.UNAUTHORIZED.getStatusCode());
		}

		mensagem.setMensagem(exception.getMessage());
		mensagem.setTipo("ERROR");

		StringWriter stringWriter = new StringWriter();
		exception.printStackTrace(new PrintWriter(stringWriter));
		mensagem.setStackTrace(stringWriter.toString());

		return Response.ok(mensagem).type(MediaType.APPLICATION_JSON).status(mensagem.getStatus()).build();
	}

}

class Mensagem {
	private Integer status = 500;
	private String mensagem;
	private String stackTrace;
	private String tipo;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getStackTrace() {
		return stackTrace;
	}

	public void setStackTrace(String stackTrace) {
		this.stackTrace = stackTrace;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}