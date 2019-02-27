package com.stefanini.pokemon.parsers;

import java.util.ArrayList;
import java.util.List;

import com.stefanini.pokemon.dtos.LogBatalhaDTO;
import com.stefanini.pokemon.entities.LogBatalha;

public class LogBatalhaParserDTO extends AbstractParser<LogBatalhaDTO, LogBatalha>{

	@Override
	public LogBatalhaDTO toDTO(LogBatalha entity) {
		TreinadorParserDTO treinadorParser = new TreinadorParserDTO();
		TurnoParserDTO turnoParser = new TurnoParserDTO();
		LogBatalhaDTO logDTO = new LogBatalhaDTO();
		
		logDTO.setTreinadorUm(treinadorParser.toDTO(entity.getTreinadorUm()));
		logDTO.setTreinadorDois(treinadorParser.toDTO(entity.getTreinadorDois()));
		if(entity.getVencedor() != null) {
			logDTO.setVencedor(treinadorParser.toDTO(entity.getVencedor()));			
		}
		logDTO.setTurnos(turnoParser.toDtoList(entity.getTurnos()));
		logDTO.setData(entity.getData());
		
		return logDTO;
	}

	@Override
	public LogBatalha toEntity(LogBatalhaDTO dto) {
		TreinadorParserDTO treinadorParser = new TreinadorParserDTO();
		TurnoParserDTO turnoParser = new TurnoParserDTO();
		LogBatalha log = new LogBatalha();

		log.setTreinadorUm(treinadorParser.toEntity(dto.getTreinadorUm()));
		log.setTreinadorDois(treinadorParser.toEntity(dto.getTreinadorDois()));
		if(dto.getVencedor() != null) {
			log.setVencedor(treinadorParser.toEntity(dto.getVencedor()));
		}
		log.setTurnos(turnoParser.toEntity(dto.getTurnos()));
		log.setData(dto.getData());
		
		return log;
	}

	public List<LogBatalhaDTO> toDtoList(List<LogBatalha> logs){
		if(logs == null) {
			return null;
		}
		ArrayList<LogBatalhaDTO> dtos = new ArrayList<>();
		
		for(LogBatalha each : logs) {
			dtos.add(this.toDTO(each));
		}
		
		return dtos;
	}

	public List<LogBatalha> toEntityList(List<LogBatalhaDTO> logs) {
		if(logs == null) {
			return null;
		}
		ArrayList<LogBatalha> entities= new ArrayList<>();
		
		for(LogBatalhaDTO each : logs) {
			entities.add(this.toEntity(each));
		}
		
		return entities;
	}
}
