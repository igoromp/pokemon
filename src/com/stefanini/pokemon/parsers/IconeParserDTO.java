package com.stefanini.pokemon.parsers;

import com.stefanini.pokemon.dtos.IconeDTO;
import com.stefanini.pokemon.entities.Icone;

public class IconeParserDTO extends AbstractParser<IconeDTO, Icone>{

	@Override
	IconeDTO toDTO(Icone entity) {
		IconeDTO dto = new IconeDTO();
		if(entity != null) {
			dto.setLinhas(entity.getLinhas());			
		}
		return dto;
	}

	@Override
	Icone toEntity(IconeDTO dto) {
		Icone entity = new Icone();
		if(dto != null) {
			entity.setLinhas(dto.getLinhas());
		}
		return entity;
	}
	
}
