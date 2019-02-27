package com.stefanini.pokemon.dtos;

import java.io.Serializable;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import com.stefanini.pokemon.entities.Pokemon;

@JsonSerialize(include = Inclusion.NON_NULL)
public class TurnoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Pokemon pokemonUm;
	private Pokemon pokemonDois;
	private Double danoCausadoPokemonUm;
	private Double danoCausadoPokemonDois;

	public Pokemon getPokemonUm() {
		return pokemonUm;
	}

	public void setPokemonUm(Pokemon pokemonUm) {
		this.pokemonUm = pokemonUm;
	}

	public Pokemon getPokemonDois() {
		return pokemonDois;
	}

	public void setPokemonDois(Pokemon pokemonDois) {
		this.pokemonDois = pokemonDois;
	}

	public Double getDanoCausadoPokemonUm() {
		return danoCausadoPokemonUm;
	}

	public void setDanoCausadoPokemonUm(Double danoCausadoPokemonUm) {
		this.danoCausadoPokemonUm = danoCausadoPokemonUm;
	}

	public Double getDanoCausadoPokemonDois() {
		return danoCausadoPokemonDois;
	}

	public void setDanoCausadoPokemonDois(Double danoCausadoPokemonDois) {
		this.danoCausadoPokemonDois = danoCausadoPokemonDois;
	}
}
