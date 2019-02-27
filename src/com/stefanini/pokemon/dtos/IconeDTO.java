package com.stefanini.pokemon.dtos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class IconeDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<ArrayList<ArrayList<Integer>>> linhas;
	public List<ArrayList<ArrayList<Integer>>> getLinhas() {
		return linhas;
	}
	public void setLinhas(List<ArrayList<ArrayList<Integer>>> linhas) {
		this.linhas = linhas;
	}
}
