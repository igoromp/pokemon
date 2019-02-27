package com.stefanini.pokemon.parsers;

import java.util.ArrayList;
import java.util.List;

import com.stefanini.pokemon.dtos.TurnoDTO;
import com.stefanini.pokemon.entities.Turno;

public class TurnoParserDTO extends AbstractParser<TurnoDTO, Turno> {

	@Override
	TurnoDTO toDTO(Turno entity) {

		TurnoDTO dto = new TurnoDTO();
		dto.setPokemonUm(entity.getPokemonUm());
		dto.setPokemonDois(entity.getPokemonDois());
		dto.setDanoCausadoPokemonUm(entity.getDanoCausadoPokemonUm());
		dto.setDanoCausadoPokemonDois(entity.getDanoCausadoPokemonDois());
		
		return dto;
	}

	@Override
	Turno toEntity(TurnoDTO dto) {

		Turno turno = new Turno();
		turno.setPokemonUm(dto.getPokemonUm());
		turno.setPokemonDois(dto.getPokemonDois());
		turno.setDanoCausadoPokemonUm(dto.getDanoCausadoPokemonUm());
		turno.setDanoCausadoPokemonDois(dto.getDanoCausadoPokemonDois());
		
		return turno;
	}

	public List<TurnoDTO> toDtoList(List<Turno> turnos){
		if(turnos == null) {
			return null;
		}
		ArrayList<TurnoDTO> dtos = new ArrayList<>();
		
		for(Turno each : turnos) {
			dtos.add(this.toDTO(each));
		}
		
		return dtos;
	}

	public List<Turno> toEntityList(List<TurnoDTO> turnos) {
		if(turnos == null) {
			return null;
		}
		ArrayList<Turno> entities= new ArrayList<>();
		
		for(TurnoDTO each : turnos) {
			entities.add(this.toEntity(each));
		}
		
		return entities;
	}
}
