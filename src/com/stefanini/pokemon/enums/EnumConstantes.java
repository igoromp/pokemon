package com.stefanini.pokemon.enums;

public enum EnumConstantes {

	POKEMON_QTDE_MINIMO (1),
	POKEMON_QTDE_MAXIMO (5);

	private Integer value;
	
	private EnumConstantes(Integer value) {
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}
}
