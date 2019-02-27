package com.stefanini.pokemon.entities;

public class TipoPokemon extends EntityBase {

	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private String descricao;

	public TipoPokemon(Integer id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}
	public TipoPokemon() {
		
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
}
