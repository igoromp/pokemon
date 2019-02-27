package com.stefanini.pokemon.service;

import java.util.List;

import com.stefanini.pokemon.entities.Pokemon;

public abstract class AbstractFactory<Entity> {

	abstract List<Entity> gerar() throws Exception;
	abstract List<Entity> gerar(List<Pokemon> l);
	
}
