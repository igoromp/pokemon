package com.stefanini.pokemon.exception;

import javax.ws.rs.core.Response.Status;

import com.stefanini.pokemon.enums.EnumPokemonMensagemException;

public class PokemonException extends GenericException{
	
	private static final long serialVersionUID = 1L;
	
	public PokemonException(EnumPokemonMensagemException pokemonVazio) {
		super(pokemonVazio.getMsg());
	}
	
	public PokemonException(EnumPokemonMensagemException msgException, Exception e) {
		super(msgException.getMsg(), e);
	}

	public Integer getCode() {
		return Status.BAD_REQUEST.getStatusCode();
	}
}