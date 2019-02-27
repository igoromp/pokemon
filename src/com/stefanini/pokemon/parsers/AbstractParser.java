package com.stefanini.pokemon.parsers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.stefanini.pokemon.entities.EntityBase;

public abstract class AbstractParser<DTO extends Serializable, ENTITY extends EntityBase> {

	abstract DTO toDTO(ENTITY entity);
	abstract ENTITY toEntity(DTO dto);
	
	public List<DTO> toDTO(List<ENTITY> entities) {
		List<DTO> dtos = new ArrayList<>();
		
		for (ENTITY entity : entities) {
			dtos.add(toDTO(entity));
		}
		
		return dtos;
	}
	
	public List<ENTITY> toEntity(List<DTO> dtos) {
		List<ENTITY> entities = new ArrayList<>();
		
		for (DTO dto : dtos) {
			entities.add(toEntity(dto));
		}
		
		return entities;
	}
	
}