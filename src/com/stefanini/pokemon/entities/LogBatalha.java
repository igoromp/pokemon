package com.stefanini.pokemon.entities;

import java.util.Date;
import java.util.List;

public class LogBatalha extends EntityBase{
	
	private static final long serialVersionUID = 1L;

	private Treinador treinadorUm;
	private Treinador treinadorDois;
	private Treinador vencedor;
	private Treinador derrotado;
	private List<Turno> turnos;
	private Date data;
	
	
	public LogBatalha() {
	}

	public Treinador getTreinadorUm() {
		return treinadorUm;
	}

	public void setTreinadorUm(Treinador treinadorUm) {
		this.treinadorUm = treinadorUm;
	}

	public Treinador getTreinadorDois() {
		return treinadorDois;
	}

	public void setTreinadorDois(Treinador treinadorDois) {
		this.treinadorDois = treinadorDois;
	}


	public Treinador getVencedor() {
		return vencedor;
	}

	public void setVencedor(Treinador vencedor) {
		this.vencedor = vencedor;
	}
	
	public Treinador getDerrotado() {
		return derrotado;
	}
	
	public void setDerrotado(Treinador derrotado) {
		this.derrotado = derrotado;
	}

	public List<Turno> getTurnos() {
		return turnos;
	}

	public void setTurnos(List<Turno> turnos) {
		this.turnos = turnos;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
}
