package com.stefanini.pokemon.dtos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

@JsonSerialize(include = Inclusion.NON_NULL)
public class LogBatalhaDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private TreinadorDTO treinadorUm;
	private TreinadorDTO treinadorDois;
	private TreinadorDTO vencedor;
	private List<TurnoDTO> turnos = new ArrayList<>();
	private Date data;

	public LogBatalhaDTO() {

	}

	public TreinadorDTO getTreinadorUm() {
		return treinadorUm;
	}

	public void setTreinadorUm(TreinadorDTO treinadorUm) {
		this.treinadorUm = treinadorUm;
	}

	public TreinadorDTO getTreinadorDois() {
		return treinadorDois;
	}

	public void setTreinadorDois(TreinadorDTO treinadorDois) {
		this.treinadorDois = treinadorDois;
	}

	public TreinadorDTO getVencedor() {
		return vencedor;
	}

	public void setVencedor(TreinadorDTO vencedor) {
		this.vencedor = vencedor;
	}

	public List<TurnoDTO> getTurnos() {
		return turnos;
	}

	public void setTurnos(List<TurnoDTO> turnos) {
		this.turnos = turnos;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
}
