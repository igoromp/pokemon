package com.stefanini.pokemon.parsers;

import com.stefanini.pokemon.dtos.TipoPokemonDTO;
import com.stefanini.pokemon.entities.TipoPokemon;

public class TipoPokemonParserDTO extends AbstractParser<TipoPokemonDTO, TipoPokemon> {

	@Override
	TipoPokemonDTO toDTO(TipoPokemon entity) {
		if (entity == null) {
			return null;
		}
		TipoPokemonDTO tipoDto = new TipoPokemonDTO();
		tipoDto.setDescricao(entity.getDescricao());
		tipoDto.setId(entity.getId());
		return tipoDto;
	}

	@Override
	TipoPokemon toEntity(TipoPokemonDTO dto) {
		if (dto == null) {
			return null;
		}
		TipoPokemon tipo = new TipoPokemon(null, null);
		tipo.setDescricao(dto.getDescricao());
		tipo.setId(dto.getId());
		return tipo;
	}

}
