package com.stefanini.pokemon.validadores;

import java.util.List;

import com.stefanini.pokemon.dtos.PokemonDTO;
import com.stefanini.pokemon.entities.Pokemon;
import com.stefanini.pokemon.entities.TipoPokemon;
import com.stefanini.pokemon.enums.EnumPokemonMensagemException;
import com.stefanini.pokemon.exception.PokemonException;

public class ValidarPokemon extends Validador<PokemonDTO> {

	/** Validação de dados nulos e vazios. */
	@Override
	public void validar(PokemonDTO pokemon) throws Exception {
		if (pokemon == null) {
			throw new PokemonException(EnumPokemonMensagemException.POKEMON_VAZIO);
		}
		if (pokemon.getId() == null || pokemon.getId() < 0) {
			throw new PokemonException(EnumPokemonMensagemException.POKEMON_ID_INVALIDO);
		}
		if (pokemon.getAtaque() == null || pokemon.getAtaque() <= 0) {
			throw new PokemonException(EnumPokemonMensagemException.POKEMON_ATAQUE_INVALIDO);
		}
		if (pokemon.getDefesa() == null || pokemon.getDefesa() <= 0) {
			throw new PokemonException(EnumPokemonMensagemException.POKEMON_DEFESA_INVALIDA);
		}
		if (pokemon.getLevel() == null || pokemon.getLevel() <= 0) {
			throw new PokemonException(EnumPokemonMensagemException.POKEMON_LEVEL_INVALIDO);
		}
		if (pokemon.getNome() == null || pokemon.getNome().trim().isEmpty()) {
			throw new PokemonException(EnumPokemonMensagemException.POKEMON_NOME_INVALIDO);
		}
		if (pokemon.getVida() == null || pokemon.getVida() <= 0) {
			throw new PokemonException(EnumPokemonMensagemException.POKEMON_VIDA_INVALIDA);
		}
		if (pokemon.getTipos() == null) {
			throw new PokemonException(EnumPokemonMensagemException.POKEMON_TIPO_VAZIO);
		}
		for (TipoPokemon tipo : pokemon.getTipos()) {
			if (tipo.getId() == null || tipo.getDescricao() == null) {
				throw new PokemonException(EnumPokemonMensagemException.POKEMON_TIPO_VAZIO);
			}
		}
	}

	/** Verifica se o nome do Pokemon já existe na base de dados. */
	public void validarNomePokemonUnico(Pokemon pokemon, List<Pokemon> pokemonsBaseDados) throws PokemonException {

		for (Pokemon pBaseDados : pokemonsBaseDados) {
			if (pokemon.getNome().equalsIgnoreCase(pBaseDados.getNome()) && !pokemon.getId().equals(pBaseDados.getId())) {
				throw new PokemonException(EnumPokemonMensagemException.POKEMON_EXISTENTE);
			}
		}
	}

	/** Verifica se o Pokemon a ser selecionado é o mesmo que existe na base de dados. */
	public void verificarAlteracaoPokemonCadastrar(List<Pokemon> pokemons, List<Pokemon> pokemonsBaseDados)
			throws PokemonException {

		int count = 0;

		for (Pokemon pTreinador : pokemons) {
			for (Pokemon pBaseDados : pokemonsBaseDados) {
				if (pTreinador.equals(pBaseDados)) {
					count++;
				}
			}
		}
		if (pokemons.size() != count) {
			throw new PokemonException(EnumPokemonMensagemException.POKEMON_DIF_BASE_DADOS);
		}
	}

	/** Verifica se o Pokemon a ser alterado é ele mesmo. */
	public void verificarAlteracaoPokemonAlterar(List<Pokemon> pokemonsTreinador, List<Pokemon> pokemonsBaseDados)	throws PokemonException {
		
		for (Pokemon pTreinador : pokemonsTreinador) {
			for (Pokemon pBaseDados : pokemonsBaseDados) {
				if (pTreinador.getNome().equalsIgnoreCase(pBaseDados.getNome()) && !pTreinador.getId().equals(pBaseDados.getId())) {
					throw new PokemonException(EnumPokemonMensagemException.POKEMON_EXISTENTE);
				}
			}
		}
	}
}
