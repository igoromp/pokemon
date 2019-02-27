package com.stefanini.pokemon.api;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.stefanini.pokemon.dtos.PokemonDTO;
import com.stefanini.pokemon.dtos.TreinadorDTO;
import com.stefanini.pokemon.entities.Usuario;
import com.stefanini.pokemon.exception.TreinadorException;
import com.stefanini.pokemon.service.TreinadorService;

@Path("/treinador")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TreinadorApi extends ApiBase {

	@Inject
	private TreinadorService treinadorService;

	@POST
	public Response incluir(TreinadorDTO treinador) throws Exception {
		TreinadorDTO treinadorDTO = treinadorService.incluir(treinador);
		return Response.ok(treinadorDTO).build();
	}

	@POST
	@Path("/{id}/{idTreinador}")
	public Response incluirPokemonTreinador(@PathParam("id") Long id, @PathParam("idTreinador") Long idTreinador)
			throws Exception {
		treinadorService.incluirPokemonNoTreinador(id, idTreinador);
		return Response.ok().build();
	}

	@PUT
	public Response alterar(TreinadorDTO treinador) throws Exception {
		treinadorService.alterar(treinador);
		return Response.ok().build();
	}

	@PUT
	@Path("/{idTreinador}")
	public Response alterarPokemonDoTreinador(@PathParam("idTreinador") Long idTreinador, PokemonDTO pokemon)
			throws Exception {
		treinadorService.alterarPokemon(idTreinador, pokemon);
		return Response.ok().build();
	}

	@DELETE
	@Path("/{id}")
	public Response deletar(@PathParam("id") Long id) throws Exception {
		Usuario usuario = getUsuario();
		treinadorService.excluir(id);
		if(usuario.getTipoAdmin() != 1) {
			getSession().setAttribute(EnumAttribute.USER.name(), null);
			getSession().invalidate();
		}
		return Response.ok().build();
	}

	@DELETE
	@Path("/pokemons/{id}/{idTreinador}")
	public Response deletarPokemon(@PathParam("id") Long id, @PathParam("idTreinador") Long idTreinador)
			throws Exception {
		treinadorService.excluirPokemon(id, idTreinador);
		return Response.ok().build();
	}

	@GET
	public Response listar() throws TreinadorException {
		return Response.ok(this.treinadorService.listar()).build();
	}

	@GET
	@Path("/pokemonsTreinador/{id}")
	public Response listarPokemonsTreinador(@PathParam("id") Long id) throws Exception {
		return Response.ok(treinadorService.listarPokemonsTreinador(id)).build();
	}
}
