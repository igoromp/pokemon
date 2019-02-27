package com.stefanini.pokemon.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import com.stefanini.pokemon.dtos.AfinidadeCardDTO;
import com.stefanini.pokemon.dtos.PokemonDTO;
import com.stefanini.pokemon.dtos.TipoPokemonDTO;
import com.stefanini.pokemon.entities.Pokemon;
import com.stefanini.pokemon.entities.TipoPokemon;
import com.stefanini.pokemon.enums.EnumPokemonMensagemException;
import com.stefanini.pokemon.enums.EnumTipoPokemon;
import com.stefanini.pokemon.exception.PokemonException;
import com.stefanini.pokemon.parsers.PokemonParserDTO;
import com.stefanini.pokemon.parsers.TipoPokemonParserDTO;
import com.stefanini.pokemon.persistence.BaseDados;
import com.stefanini.pokemon.validadores.ValidarPokemon;

public class PokemonService extends ServiceBase {

	@Inject
	private BaseDados baseDados;
	@Inject
	private PokemonParserDTO pokemonParserDTO;
	@Inject
	private TipoPokemonParserDTO tipoPokemonParserDTO;

	private ValidarPokemon validarPokemon = new ValidarPokemon();

	private Long idMaior = (long) 0;

	public void maiorId() {
		if (baseDados.getPokemons().size() == 0) {
			idMaior = (long) -1;
		} else {
			for (int i = 0; i < baseDados.getPokemons().size(); i++) {
				if (baseDados.getPokemons().get(i).getId() > idMaior) {
					idMaior = baseDados.getPokemons().get(i).getId();
				}
			}
		}
	}

	public PokemonDTO incluir(PokemonDTO dto) throws Exception {

		maiorId();
		dto.setAtaque((int) (Math.random() * 3) + 1);
		dto.setDefesa((int) (Math.random() * 2) + 1);
		dto.setVida((100 + (Double) (Math.random() * 10) + 1));
		dto.setLevel(1);
		dto.setId(idMaior + 1);

		validarPokemon.validar(dto);
		validarPokemon.validarNomePokemonUnico(pokemonParserDTO.toEntity(dto), baseDados.getPokemons());
		Pokemon pokemon = pokemonParserDTO.toEntity(dto);

		try {
			baseDados.addPokemon(pokemon);
		} catch (Exception e) {
			throw new PokemonException(EnumPokemonMensagemException.POKEMON_ERRO_CADASTRAR);
		}

		return pokemonParserDTO.toDTO(pokemon);
	}

	public PokemonDTO alterar(PokemonDTO dto) throws Exception {

		validarPokemon.validar(dto);
		validarPokemon.validarNomePokemonUnico(pokemonParserDTO.toEntity(dto), baseDados.getPokemons());

		Pokemon pokemon = pokemonParserDTO.toEntity(dto);

		try {
			baseDados.alterarPokemon(pokemon);
		} catch (Exception e) {
			throw new PokemonException(EnumPokemonMensagemException.POKEMON_ERRO_ALTERAR);
		}

		return pokemonParserDTO.toDTO(pokemon);
	}

	public void excluir(Long id) throws PokemonException {
		try {
			baseDados.delete(id);
		} catch (PokemonException e) {
			throw e;
		} catch (Exception e) {
			throw new PokemonException(EnumPokemonMensagemException.POKEMON_ERRO_EXCLUIR, e);
		}
	}

	public List<PokemonDTO> listar() {
		return pokemonParserDTO.toDTO(baseDados.getPokemons());
	}

	public List<TipoPokemonDTO> listarTipos() throws PokemonException {
		List<TipoPokemonDTO> tipos = tipoPokemonParserDTO.toDTO(EnumTipoPokemon.getTipos());

		if (tipos == null || tipos.isEmpty()) {
			throw new PokemonException(EnumPokemonMensagemException.POKEMON_TIPO_VAZIO);
		}
		return tipos;
	}

	public List<TipoPokemon> listarTipoPokemon() throws PokemonException {
		List<TipoPokemon> tipoPokemons = new ArrayList<>();
		try {
			tipoPokemons = EnumTipoPokemon.getTipos();
		} catch (Exception e) {
			throw e;
		}

		return tipoPokemons;
	}

	public Map<Integer, AfinidadeCardDTO> listarAfinidade() {
		Map<Integer, AfinidadeCardDTO> afinidades = new HashMap<>();
		
		for (EnumTipoPokemon e : EnumTipoPokemon.values()) {
			afinidades.put(e.getId(), new AfinidadeCardDTO(
					EnumTipoPokemon.getImunidadesDesc(e),
					EnumTipoPokemon.getFraquezasDesc(e),
					EnumTipoPokemon.getForcasDesc(e)));
		}
		
		return afinidades;
	}
}
