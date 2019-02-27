package com.stefanini.pokemon.dtos;

import java.io.Serializable;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

@JsonSerialize(include = Inclusion.NON_NULL)
public class UsuarioDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String email;
	private String senha;
	private Integer tipoAdmin;
	private IconeDTO icone;

	public UsuarioDTO(String email, String senha) {
		this.email = email;
		this.senha = senha;
	}

	public UsuarioDTO(Long id, String email, String senha, Integer tipoAdmin) {
		this.id = id;
		this.email = email;
		this.senha = senha;
		this.tipoAdmin = tipoAdmin;
	}

	public UsuarioDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setTipoAdmin(Integer tipoAdmin) {
			this.tipoAdmin = tipoAdmin;	
	}

	public Integer getTipoAdmin() {
		return tipoAdmin;
	}

	public IconeDTO getIcone() {
		return icone;
	}

	public void setIcone(IconeDTO icone) {
		this.icone = icone;
	}

}
