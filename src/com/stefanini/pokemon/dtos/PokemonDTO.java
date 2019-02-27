package com.stefanini.pokemon.dtos;

import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.stefanini.pokemon.entities.TipoPokemon;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PokemonDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;
	private Integer level = 0;
	private Integer levelAntigo = 0;
	private Integer levelGanho = 0;
	private Integer ataque = 0;
	private Integer ataqueAntigo = 0;
	private Integer ataqueGanho = 0;
	private Integer defesa = 0;
	private Integer defesaAntiga = 0;
	private Integer defesaGanha = 0;
	private Double vida = 0d;
	private Double vidaAntiga = 0d;
	private Double vidaGanha = 0d;
	private List<TipoPokemon> tipos;
	private Long exp = 0L;
	private Long expAntiga = 0L;
	private Long expGanha = 0L;
	private Integer velocidade;
	private Integer ataqueEspecial;
	private Integer defesaEspecial;
	private List<Integer> evolucoes;
	
	public Integer getLevelAntigo() {
		return levelAntigo;
	}

	public void setLevelAntigo(Integer levelAntigo) {
		this.levelAntigo = levelAntigo;
	}

	public Integer getLevelGanho() {
		return levelGanho;
	}

	public void setLevelGanho(Integer levelGanho) {
		this.levelGanho = levelGanho;
	}

	public Integer getAtaqueAntigo() {
		return ataqueAntigo;
	}

	public void setAtaqueAntigo(Integer ataqueAntigo) {
		this.ataqueAntigo = ataqueAntigo;
	}

	public Integer getAtaqueGanho() {
		return ataqueGanho;
	}

	public void setAtaqueGanho(Integer ataqueGanho) {
		this.ataqueGanho = ataqueGanho;
	}

	public Integer getDefesaAntiga() {
		return defesaAntiga;
	}

	public void setDefesaAntiga(Integer defesaAntiga) {
		this.defesaAntiga = defesaAntiga;
	}

	public Integer getDefesaGanha() {
		return defesaGanha;
	}

	public void setDefesaGanha(Integer defesaGanha) {
		this.defesaGanha = defesaGanha;
	}

	public Double getVidaAntiga() {
		return vidaAntiga;
	}

	public void setVidaAntiga(Double vidaAntiga) {
		this.vidaAntiga = vidaAntiga;
	}

	public Double getVidaGanha() {
		return vidaGanha;
	}

	public void setVidaGanha(Double vidaGanha) {
		this.vidaGanha = vidaGanha;
	}

	public Long getExpGanha() {
		return expGanha;
	}

	public void setExpGanha(Long expGanha) {
		this.expGanha = expGanha;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Double getVida() {
		return vida;
	}
	public void setVida(Double vida) {
		this.vida = vida;
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
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public List<TipoPokemon> getTipos() {
		return tipos;
	}
	public void setTipos(List<TipoPokemon> list) {
		this.tipos = list;
	}

	public Long getExp() {
		return exp;
	}

	public void setExp(Long exp) {
		this.exp = exp;
	}
	public Long getExpAntiga() {
		return expAntiga;
	}
	public void setExpAntiga(Long expAntiga) {
		this.expAntiga = expAntiga;
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
	
}