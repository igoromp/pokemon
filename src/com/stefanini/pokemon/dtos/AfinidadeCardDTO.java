package com.stefanini.pokemon.dtos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AfinidadeCardDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<String> imunidades = new ArrayList<>();
	private List<String> fraquezas = new ArrayList<>();
	private List<String> forcas = new ArrayList<>();

	public AfinidadeCardDTO(List<String> imunidades, List<String> fraquezas, List<String> forcas) {
		if (imunidades != null && fraquezas != null && forcas != null) {
			this.imunidades.addAll(imunidades);
			this.fraquezas.addAll(fraquezas);
			this.forcas.addAll(forcas);
		}
	}

	public List<String> getImunidades() {
		return imunidades;
	}

	public void setImunidades(List<String> imunidades) {
		this.imunidades = imunidades;
	}

	public List<String> getFraquezas() {
		return fraquezas;
	}

	public void setFraquezas(List<String> fraquezas) {
		this.fraquezas = fraquezas;
	}

	public List<String> getForcas() {
		return forcas;
	}

	public void setForcas(List<String> forcas) {
		this.forcas = forcas;
	}
}
