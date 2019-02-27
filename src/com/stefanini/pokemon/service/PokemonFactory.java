package com.stefanini.pokemon.service;

import java.util.Arrays;
import java.util.List;

import com.stefanini.pokemon.entities.Pokemon;

public class PokemonFactory extends AbstractFactory<Pokemon> {

	@Override
	public List<Pokemon> gerar() throws Exception {
		Pokemon[] pokemons = new Pokemon[151];
		LerArquivo<Pokemon> lerArquivo = new LerArquivo<>();
		
		pokemons = lerArquivo.jsonToClasse("resources/pokemons.json", Pokemon[].class);
		return Arrays.asList(pokemons);
	}

	@Override
	List<Pokemon> gerar(List<Pokemon> l) {
		return null;
	}
}
