package com.stefanini.pokemon.parsers;

import com.stefanini.pokemon.dtos.UsuarioDTO;
import com.stefanini.pokemon.entities.Usuario;

public class UsuarioParserDTO extends AbstractParser<UsuarioDTO, Usuario> {

	@Override
	UsuarioDTO toDTO(Usuario entity) {
		
		UsuarioDTO dto = new UsuarioDTO();
		IconeParserDTO iconeParser = new IconeParserDTO();
		
		dto.setEmail(entity.getEmail());
		dto.setId(entity.getId());
		dto.setSenha(entity.getSenha());
		dto.setTipoAdmin(entity.getTipoAdmin());
		dto.setIcone(iconeParser.toDTO(entity.getIcone()));
		
		return dto;
	}

	@Override
	public
	Usuario toEntity(UsuarioDTO dto) {
		Usuario entity = new Usuario();
		IconeParserDTO iconeParser = new IconeParserDTO();
		
		entity.setEmail(dto.getEmail());
		entity.setId(dto.getId());
		entity.setSenha(dto.getSenha());
		entity.setTipoAdmin(dto.getTipoAdmin());
		entity.setIcone(iconeParser.toEntity(dto.getIcone()));
		
		return entity;
	}

}
