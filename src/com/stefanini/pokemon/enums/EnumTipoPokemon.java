package com.stefanini.pokemon.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stefanini.pokemon.dtos.AfinidadeDTO;
import com.stefanini.pokemon.entities.TipoPokemon;

public enum EnumTipoPokemon {

	NORMAL		(1, "Normal"), 
	FOGO		(2, "Fogo"), 
	AGUA		(3, "Agua"), 
	ELETRICO	(4, "Elétrico"), 
	GRAMA		(5, "Grama"), 
	GELO		(6,	"Gelo"), 
	LUTADOR		(7, "Lutador"), 
	VENENOSO	(8, "Venenoso"), 
	TERRESTRE	(9, "Terrestre"), 
	VOADOR		(10,"Voador"), 
	PSIQUICO	(11, "Psiquico"), 
	INSETO		(12, "Inseto"), 
	PEDRA		(13, "Pedra"), 
	FANTASMA	(14,"Fantasma"), 
	DRAGAO		(15, "Dragao"), 
	SOMBRIO		(16, "Sombrio"), 
	ACO			(17, "Aço"), 
	FADA		(18, "Fada");

	private Integer id;
	private String descricao;
	private static Map<EnumTipoPokemon, AfinidadeDTO> maps = new HashMap<>();

	private EnumTipoPokemon(Integer codigo, String descricao) {
		this.id = codigo;
		this.descricao = descricao;
	}

	public Integer getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}

	public static Double obterMultiplicadorDano(EnumTipoPokemon eAtacante, EnumTipoPokemon eDefensor) {
		for (EnumTipoPokemon eTipoAtacante : getImunes(eAtacante)) {
			if (eDefensor.equals(eTipoAtacante)) {
				return 0D;
			}
		}

		for (EnumTipoPokemon eTipoAtacante : getFraquezas(eAtacante)) {
			if (eDefensor.equals(eTipoAtacante)) {
				return 0.5;
			}
		}

		for (EnumTipoPokemon eTipoAtacante : getForcas(eAtacante)) {
			if (eDefensor.equals(eTipoAtacante)) {
				return 2D;
			}
		}

		return 1D;
	}

	public static List<EnumTipoPokemon> getImunes(EnumTipoPokemon e) {
		return getMap().get(e).getImunidades();
	}

	public static List<EnumTipoPokemon> getFraquezas(EnumTipoPokemon e) {
		return getMap().get(e).getFraquezas();
	}

	public static List<EnumTipoPokemon> getForcas(EnumTipoPokemon e) {
		return getMap().get(e).getForcas();
	}
	
	public static List<String> getFraquezasDesc(EnumTipoPokemon e) {
		List<String> fraquezas = new ArrayList<>();
		
		for (EnumTipoPokemon enumFraquezas : EnumTipoPokemon.getFraquezas(e)) {
			fraquezas.add(enumFraquezas.getDescricao());
		}
		
		return fraquezas;
	}
	
	public static List<String> getForcasDesc(EnumTipoPokemon e) {
		List<String> forcas = new ArrayList<>();
		
		for (EnumTipoPokemon enumForcas : EnumTipoPokemon.getForcas(e)) {
			forcas.add(enumForcas.getDescricao());
		}
		
		return forcas;
	}
	
	public static List<String> getImunidadesDesc(EnumTipoPokemon e) {
		List<String> imunidades = new ArrayList<>();
		
		for (EnumTipoPokemon enumImunidades : EnumTipoPokemon.getImunes(e)) {
			imunidades.add(enumImunidades.getDescricao());
		}
		
		return imunidades;
	}

	public static List<TipoPokemon> getTipos() {
		List<TipoPokemon> tipos = new ArrayList<>();
		
		for (EnumTipoPokemon eTipoPokemon : EnumTipoPokemon.values()) {
			tipos.add(new TipoPokemon(eTipoPokemon.getId(), eTipoPokemon.getDescricao()));
		}
		return tipos;
	}
	
	public static Map<EnumTipoPokemon, AfinidadeDTO> getMap() {
		final List<EnumTipoPokemon> LISTA_VAZIA = Collections.<EnumTipoPokemon>emptyList();
		
		if (maps.isEmpty()) {

			maps.put(NORMAL, new AfinidadeDTO(
					Arrays.asList(FANTASMA), 
					Arrays.asList(PEDRA, ACO), 
					LISTA_VAZIA));
			
			maps.put(FOGO, new AfinidadeDTO(
					LISTA_VAZIA, 
					Arrays.asList(FOGO, AGUA, PEDRA, DRAGAO), 
					Arrays.asList(GRAMA, GELO, INSETO, ACO)));
			
			maps.put(AGUA, new AfinidadeDTO(
					LISTA_VAZIA, 
					Arrays.asList(AGUA, GRAMA, DRAGAO), 
					Arrays.asList(FOGO, TERRESTRE, PEDRA)));
			
			maps.put(ELETRICO, new AfinidadeDTO(
					Arrays.asList(TERRESTRE), 
					Arrays.asList(ELETRICO, GRAMA, DRAGAO), 
					Arrays.asList(AGUA, VOADOR)));
			
			maps.put(GRAMA, new AfinidadeDTO(
					LISTA_VAZIA,
					Arrays.asList(FOGO, GRAMA, VENENOSO, VOADOR, INSETO, DRAGAO, ACO),
					Arrays.asList(AGUA, TERRESTRE, PEDRA)));
			
			maps.put(GELO, new AfinidadeDTO(
					LISTA_VAZIA,
					Arrays.asList(FOGO, AGUA, GELO, ACO),
					Arrays.asList(GRAMA, TERRESTRE, VOADOR, DRAGAO)));
			
			maps.put(LUTADOR, new AfinidadeDTO(
					Arrays.asList(FANTASMA),
					Arrays.asList(VENENOSO, VOADOR, PSIQUICO, INSETO, FADA),
					Arrays.asList(NORMAL, GELO, PEDRA, SOMBRIO, ACO)));
			
			maps.put(VENENOSO, new AfinidadeDTO(
					Arrays.asList(ACO),
					Arrays.asList(VENENOSO, TERRESTRE, PEDRA, FANTASMA),
					Arrays.asList(GRAMA, FADA)));
			
			maps.put(TERRESTRE, new AfinidadeDTO(
					Arrays.asList(VOADOR),
					Arrays.asList(GRAMA, INSETO),
					Arrays.asList(FOGO, ELETRICO, VENENOSO, PEDRA, ACO)));
			
			maps.put(VOADOR, new AfinidadeDTO(
					LISTA_VAZIA,
					Arrays.asList(ELETRICO, PEDRA, ACO),
					Arrays.asList(GRAMA, LUTADOR, INSETO)));
			
			maps.put(PSIQUICO, new AfinidadeDTO(
					Arrays.asList(SOMBRIO),
					Arrays.asList(PSIQUICO, ACO),
					Arrays.asList(LUTADOR, VENENOSO)));
			
			maps.put(INSETO, new AfinidadeDTO(
					LISTA_VAZIA,
					Arrays.asList(FOGO, LUTADOR, VENENOSO, VOADOR, FANTASMA, ACO, FADA),
					Arrays.asList(GRAMA, PSIQUICO, SOMBRIO)));
			
			maps.put(PEDRA, new AfinidadeDTO(
					LISTA_VAZIA,
					Arrays.asList(LUTADOR, TERRESTRE, ACO),
					Arrays.asList(FOGO, GELO, VOADOR, INSETO)));
			
			maps.put(FANTASMA, new AfinidadeDTO(
					Arrays.asList(NORMAL),
					Arrays.asList(SOMBRIO),
					Arrays.asList(PSIQUICO, FANTASMA)));
			
			maps.put(DRAGAO, new AfinidadeDTO(
					Arrays.asList(FADA),
					Arrays.asList(ACO),
					Arrays.asList(DRAGAO)));
			
			maps.put(SOMBRIO, new AfinidadeDTO(
					LISTA_VAZIA,
					Arrays.asList(LUTADOR, SOMBRIO, FADA),
					Arrays.asList(PSIQUICO, FANTASMA)));
			
			maps.put(ACO, new AfinidadeDTO(
					LISTA_VAZIA,
					Arrays.asList(FOGO, AGUA, ELETRICO, ACO),
					Arrays.asList(GELO, PEDRA, FADA)));
			
			maps.put(FADA, new AfinidadeDTO(
					LISTA_VAZIA,
					Arrays.asList(FOGO, VENENOSO, ACO),
					Arrays.asList(LUTADOR, DRAGAO, SOMBRIO)));
		}

		return maps;
	}
}
