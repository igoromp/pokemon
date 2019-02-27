package com.stefanini.pokemon.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response.Status;

import com.stefanini.pokemon.entities.Usuario;


public class ApiBase {

	@Context
	private HttpServletRequest request;
	
	@Context
	private HttpServletResponse response;
	
	
	protected HttpSession getSession() {
		return request.getSession();
	}
	
	public HttpServletRequest getRequest() {
		return request;
	}
	
	protected Usuario getUsuario() throws NotAuthorizedException {
		Object user = request.getSession().getAttribute(EnumAttribute.USER.name());
		
		if (user == null) {
			getSession().invalidate();
			throw new NotAuthorizedException("Autorização negada", Status.UNAUTHORIZED);
		}
		
		return (Usuario) user;
	}
}
