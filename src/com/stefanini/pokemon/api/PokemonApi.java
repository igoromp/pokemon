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
import com.stefanini.pokemon.exception.PokemonException;
import com.stefanini.pokemon.service.PokemonService;

@Path("/pokemon")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PokemonApi {

	@Inject
	private PokemonService pokemonService;

	@GET
	public Response listar() {
		return Response.ok(pokemonService.listar()).build();
	}

	@GET
	@Path("/tipos")
	public Response listarTipos() throws PokemonException {
		return Response.ok(pokemonService.listarTipos()).build();
	}
	
	@POST
	@Path("/afinidade")
	public Response listarAfinidades(PokemonDTO pokemon){
		return Response.ok(pokemonService.listarAfinidade()).build();
	}

	@PUT
	public Response alterar(PokemonDTO pokemon) throws Exception {
		PokemonDTO pokemonDTO = pokemonService.alterar(pokemon);
		return Response.ok(pokemonDTO).build();
	}

	@POST
	public Response salvar(PokemonDTO pokemon) throws Exception {
		PokemonDTO pokemonDTO = pokemonService.incluir(pokemon);
		return Response.ok(pokemonDTO).build();
	}

	@DELETE
	@Path("/{id}")
	public Response deletar(@PathParam("id") Long id) throws Exception {
		pokemonService.excluir(id);
		return Response.ok().build();
	}

}
