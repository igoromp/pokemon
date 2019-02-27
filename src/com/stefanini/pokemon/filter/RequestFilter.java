package com.stefanini.pokemon.filter;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

import com.stefanini.pokemon.api.EnumAttribute;
import com.stefanini.pokemon.entities.Usuario;

@Provider
public class RequestFilter implements ContainerRequestFilter {

	@Context
	HttpServletRequest request;

	@Override
	public void filter(ContainerRequestContext requestContext) {
		HttpSession session = request.getSession();
		Usuario usuario = (Usuario) session.getAttribute(EnumAttribute.USER.name());
		String resource = requestContext.getUriInfo().getPath();
		
		List<String> headers = requestContext.getHeaders().get("Authorization");
		
	 	if (usuario == null && resource != null && !(resource.contains("login") || (resource.contains("treinador") && requestContext.getMethod().equalsIgnoreCase("POST"))|| (resource.contains("pokemon") && requestContext.getMethod().equalsIgnoreCase("GET"))) || !(headers != null && headers.get(0) != null && headers.get(0).equals("pode passar"))) {
			session.invalidate();
			throw new NotAuthorizedException("Autorização negada", Status.UNAUTHORIZED);
		} 

	}
}
