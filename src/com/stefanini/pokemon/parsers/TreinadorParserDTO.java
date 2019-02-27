package com.stefanini.pokemon.parsers;

import java.util.ArrayList;
import java.util.List;

import com.stefanini.pokemon.dtos.TreinadorDTO;
import com.stefanini.pokemon.entities.Treinador;

public class TreinadorParserDTO extends AbstractParser<TreinadorDTO, Treinador> {

	@Override
	public TreinadorDTO toDTO(Treinador entity) {
		PokemonParserDTO pokemonParser = new PokemonParserDTO();
		UsuarioParserDTO usuarioParser = new UsuarioParserDTO();
		
		TreinadorDTO dto = new TreinadorDTO();
		dto.setId(entity.getId());
		dto.setIdade(entity.getIdade());
		dto.setNome(entity.getNome());
		dto.setPokemons(pokemonParser.toDtoList(entity.getPokemons()));
		dto.setUsuario(usuarioParser.toDTO(entity.getUsuario()));

		return dto;
	}

	@Override
	public Treinador toEntity(TreinadorDTO dto) {
		PokemonParserDTO pokemonParser = new PokemonParserDTO();
		UsuarioParserDTO usuarioParser = new UsuarioParserDTO();
		
		Treinador entity = new Treinador();
		entity.setId(dto.getId());
		entity.setIdade(dto.getIdade());
		entity.setNome(dto.getNome());
		entity.setPokemons(pokemonParser.toEntityList(dto.getPokemons()));
		entity.setUsuario(usuarioParser.toEntity(dto.getUsuario()));

		return entity;
	}

	public List<TreinadorDTO> toDtoList(List<Treinador> treinadores) {
		ArrayList<TreinadorDTO> dtos = new ArrayList<>();

		for (Treinador each : treinadores) {
			dtos.add(this.toDTO(each));
		}

		return dtos;
	}

	public List<Treinador> toEntityList(List<TreinadorDTO> treinadores) {
		ArrayList<Treinador> entities = new ArrayList<>();

		for (TreinadorDTO each : treinadores) {
			entities.add(this.toEntity(each));
		}

		return entities;
	}
}
