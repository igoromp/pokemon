package com.stefanini.pokemon.entities;

import java.util.List;

public class Pokemon extends EntityBase {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;
	private List<TipoPokemon> tipos;
	private Integer level = 0;
	private Integer ataque = 0;
	private Integer defesa = 0;
	private Double vida = 0d;
	private Long exp = 0L;
	private Integer velocidade;
	private Integer ataqueEspecial;
	private Integer defesaEspecial;
	private List<Integer> evolucoes;
	
	public Pokemon(Pokemon pokemon) {
		this.nome = pokemon.getNome();
		this.tipos = pokemon.getTipos();
		this.level = pokemon.getLevel();
		this.id = pokemon.getId();
		this.ataque = pokemon.getAtaque();
		this.defesa = pokemon.getDefesa();
		this.vida = pokemon.getVida();
		this.exp = pokemon.getExp();
		this.velocidade = pokemon.getVelocidade();
		this.ataqueEspecial = pokemon.getAtaqueEspecial();
		this.defesaEspecial = pokemon.getDefesaEspecial();
		this.evolucoes = pokemon.getEvolucoes();
	}
	
	
	public Integer getVelocidade() {
		return velocidade;
	}

	public void setVelocidade(Integer velocidade) {
		this.velocidade = velocidade;
	}

	public Integer getAtaqueEspecial() {
		return ataqueEspecial;
	}

	public void setAtaqueEspecial(Integer ataqueEspecial) {
		this.ataqueEspecial = ataqueEspecial;
	}

	public Integer getDefesaEspecial() {
		return defesaEspecial;
	}

	public void setDefesaEspecial(Integer defesaEspecial) {
		this.defesaEspecial = defesaEspecial;
	}

	public List<Integer> getEvolucoes() {
		return evolucoes;
	}

	public void setEvolucoes(List<Integer> evolucoes) {
		this.evolucoes = evolucoes;
	}

	public Pokemon() {
	}

	public Long getExp() {
		return exp;
	}
	
	public void setExp(Long exp) {
		this.exp = exp;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<TipoPokemon> getTipos() {
		return tipos;
	}

	public void setTipos(List<TipoPokemon> tipos) {
		this.tipos = tipos;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getAtaque() {
		return ataque;
	}

	public void setAtaque(Integer ataque) {
		this.ataque = ataque;
	}

	public Integer getDefesa() {
		return defesa;
	}

	public void setDefesa(Integer defesa) {
		this.defesa = defesa;
	}

	public Double getVida() {
		return vida < 0 ? 0 : vida;
	}

	public void setVida(Double vida) {
		this.vida = vida;
	}

	public Double recalcularVida(Double dano) {
		this.vida -= dano;
		return this.vida;
	}

	public boolean equals(Pokemon pokemon) {

		int count = 0;
		for (TipoPokemon tipoPokemon : pokemon.getTipos()) {
			for (TipoPokemon tipoGeral : this.getTipos()) {
				if (tipoPokemon.getId().equals(tipoGeral.getId())
						&& tipoPokemon.getDescricao().equals(tipoGeral.getDescricao())) {
					count++;
				}
			}
		}

		return this.getTipos().size() == count && this.getId().equals(pokemon.getId())
				&& this.getNome().equals(pokemon.getNome()) && this.getVida().equals(pokemon.getVida())
				&& this.getAtaque().equals(pokemon.getAtaque()) && this.getDefesa().equals(pokemon.getDefesa())
				&& this.getLevel().equals(pokemon.getLevel());
	}

	

}
