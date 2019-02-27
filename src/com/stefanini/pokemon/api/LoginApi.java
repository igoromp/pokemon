package com.stefanini.pokemon.api;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.stefanini.pokemon.dtos.TreinadorDTO;
import com.stefanini.pokemon.dtos.UsuarioDTO;
import com.stefanini.pokemon.entities.Usuario;
import com.stefanini.pokemon.enums.EnumTreinadorMensagemException;
import com.stefanini.pokemon.exception.TreinadorException;
import com.stefanini.pokemon.parsers.UsuarioParserDTO;
import com.stefanini.pokemon.service.LoginService;
import com.stefanini.pokemon.service.TreinadorService;
import com.stefanini.pokemon.utils.SessionMapper;

@Path("/login")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LoginApi extends ApiBase {
	
	@Inject
	UsuarioParserDTO usuarioParser;
	
	@Inject
	TreinadorService treinadorService;
	
	@Inject
	LoginService loginService;

	@GET
	@Path("/obter")	
	public Response obterUsuario() throws Exception {
		Usuario usuario = getUsuario();
		TreinadorDTO treinadorDTO = treinadorService.getTreinadorByEmail(usuario.getEmail());
		treinadorDTO.getUsuario().setSenha(null);
		treinadorDTO.getUsuario().setId(null);
		return Response.ok(treinadorDTO).build();
	}
	
	@POST
	@Path("/verify-login")	
	public Response verifylogin(UsuarioDTO usuarioDTO) {
		
		return Response.ok(true).build();
	}
	
	@POST
	public Response login(UsuarioDTO usuarioDTO) throws Exception {
		
		if (getRequest().getSession().getAttribute(EnumAttribute.USER.name()) == null) {
			Usuario usuario = usuarioParser.toEntity(loginService.validarLogin(usuarioDTO));
			SessionMapper mapper = new SessionMapper();
			getSession().setAttribute(EnumAttribute.USER.name(), usuario);
			TreinadorDTO treinadorDTO = treinadorService.getTreinadorByEmail(usuario.getEmail());
			treinadorDTO.getUsuario().setSenha(null);
			treinadorDTO.getUsuario().setId(null);
			
			//if(!mapper.get(usuario.getEmail())) mapper.set(usuario.getEmail(), true);
				
			return Response.ok(treinadorDTO).build();
		}
		
		System.out.print(getRequest().getSession().getAttribute(EnumAttribute.USER.name()).toString());
		throw new TreinadorException(EnumTreinadorMensagemException.TREINADOR_NAO_ENCONTRADO);
	}
	
	@GET
	public Response deslogar() {
		SessionMapper mapper  = new SessionMapper();
		
		getSession().invalidate();
		return Response.ok().build();
	}
	
}
