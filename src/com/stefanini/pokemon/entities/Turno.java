package com.stefanini.pokemon.entities;

public class Turno extends EntityBase{

	private static final long serialVersionUID = 1L;

	private Pokemon pokemonUm;
	private Pokemon pokemonDois;
	private Double danoCausadoPokemonUm;
	private Double danoCausadoPokemonDois;

	public Turno(Pokemon pokemonUm, Pokemon pokemonDois, Double danoCausadoPokemonUm, Double danoCausadoPokemonDois) {
		this.pokemonUm = pokemonUm;
		this.pokemonDois = pokemonDois;
		this.danoCausadoPokemonUm = danoCausadoPokemonUm;
		this.danoCausadoPokemonDois = danoCausadoPokemonDois;
	}

	public Turno() {
	}

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
