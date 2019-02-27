package com.stefanini.pokemon.api;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.stefanini.pokemon.dtos.LogBatalhaDTO;
import com.stefanini.pokemon.dtos.PokemonDTO;
import com.stefanini.pokemon.dtos.TreinadorDTO;
import com.stefanini.pokemon.entities.Treinador;
import com.stefanini.pokemon.entities.Usuario;
import com.stefanini.pokemon.exception.TreinadorException;
import com.stefanini.pokemon.parsers.LogBatalhaParserDTO;
import com.stefanini.pokemon.service.BatalharService;
import com.stefanini.pokemon.service.TreinadorService;

@Path("/batalhar")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BatalharApi extends ApiBase {

	@Inject
	TreinadorService treinadorService;

	@Inject
	BatalharService batalharService;

	@Inject
	LogBatalhaParserDTO logParser;

	@POST
	public Response batalhar(TreinadorDTO treinadorDois) throws Exception {
		Usuario usuario = getUsuario();
		TreinadorDTO treinadorUm = treinadorService.getTreinadorByEmail(usuario.getEmail());
		try {
			LogBatalhaDTO log = batalharService.batalhar(treinadorUm, treinadorDois);
			return Response.ok(log).build();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return Response.ok().build();
	}
	

	@POST
	@Path("/treinar")
	public Response treinar(PokemonDTO pokemon) throws TreinadorException {
		Usuario usuario = getUsuario();
		TreinadorDTO treinador = treinadorService.getTreinadorByEmail(usuario.getEmail());
		LogBatalhaDTO log = batalharService.treinar(treinador,pokemon);
		return Response.ok(log).build();
	}

	@GET
	@Path("/{idTreinador}")
	public Response getLogBatalha(@PathParam("idTreinador") Long idTreinador) throws Exception {
		Treinador treinador = treinadorService.getTreinadorById(idTreinador);
		return Response.ok(treinadorService.obterLogBatalha(treinador)).build();
	}
}
