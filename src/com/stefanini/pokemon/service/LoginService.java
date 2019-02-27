package com.stefanini.pokemon.service;

import java.util.List;

import javax.inject.Inject;

import com.stefanini.pokemon.dtos.TreinadorDTO;
import com.stefanini.pokemon.dtos.UsuarioDTO;
import com.stefanini.pokemon.enums.EnumTreinadorMensagemException;
import com.stefanini.pokemon.exception.TreinadorException;
import com.stefanini.pokemon.parsers.TreinadorParserDTO;
import com.stefanini.pokemon.persistence.BaseDados;

public class LoginService extends ServiceBase{
	@Inject
	private BaseDados baseDados;
	

	public List<TreinadorDTO> listar() {
		return new TreinadorParserDTO().toDTO(baseDados.getTreinadores());
	}
	
	public UsuarioDTO validarLogin(UsuarioDTO usuarioDTO) throws TreinadorException {
		List<TreinadorDTO> treinadores = this.listar();
		
		for(TreinadorDTO treinador : treinadores) {
			if(treinador.getUsuario().getEmail().equals(usuarioDTO.getEmail()) && treinador.getUsuario().getSenha().equals(usuarioDTO.getSenha())) {
				
				usuarioDTO.setId(treinador.getUsuario().getId());
				usuarioDTO.setTipoAdmin(treinador.getUsuario().getTipoAdmin());
				
				return usuarioDTO;
			}
		}
		throw new TreinadorException(EnumTreinadorMensagemException.TREINADOR_LOGIN_INCORRETO);
	}
}