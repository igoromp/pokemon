package com.stefanini.pokemon.validadores;

public abstract class Validador<DTO> {

	public abstract void validar(DTO dto) throws Exception;
}
