package com.stefanini.pokemon.dtos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.stefanini.pokemon.enums.EnumTipoPokemon;

public class AfinidadeDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<EnumTipoPokemon> imunidades = new ArrayList<>();
	private List<EnumTipoPokemon> fraquezas = new ArrayList<>();
	private List<EnumTipoPokemon> forcas = new ArrayList<>();

	public AfinidadeDTO() {

	}

	public AfinidadeDTO(List<EnumTipoPokemon> imunidades, List<EnumTipoPokemon> fraquezas, List<EnumTipoPokemon> forcas) {
		if (imunidades != null && fraquezas != null && forcas != null) {
			this.imunidades.addAll(imunidades);
			this.fraquezas.addAll(fraquezas);
			this.forcas.addAll(forcas);
		}
	}

	public List<EnumTipoPokemon> getImunidades() {
		return imunidades;
	}

	public void setImunidades(List<EnumTipoPokemon> imunidades) {
		this.imunidades = imunidades;
	}

	public List<EnumTipoPokemon> getFraquezas() {
		return fraquezas;
	}

	public void setFraquezas(List<EnumTipoPokemon> fraquezas) {
		this.fraquezas = fraquezas;
	}

	public List<EnumTipoPokemon> getForcas() {
		return forcas;
	}

	public void setForcas(List<EnumTipoPokemon> forcas) {
		this.forcas = forcas;
	}
}
