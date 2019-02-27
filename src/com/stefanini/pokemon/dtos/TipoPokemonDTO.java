package com.stefanini.pokemon.dtos;

import java.io.Serializable;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import com.stefanini.pokemon.enums.EnumTipoPokemon;

@JsonSerialize(include = Inclusion.NON_NULL)
public class TipoPokemonDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String descricao;
	private EnumTipoPokemon tipoPokemon;

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
	public EnumTipoPokemon getTipoPokemon() {
		return tipoPokemon;
	}
	public void setTipoPokemon(EnumTipoPokemon tipoPokemon) {
		this.tipoPokemon = tipoPokemon;
	}
}
