package com.stefanini.pokemon.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.stefanini.pokemon.dtos.LogBatalhaDTO;
import com.stefanini.pokemon.dtos.PokemonDTO;
import com.stefanini.pokemon.dtos.TreinadorDTO;
import com.stefanini.pokemon.entities.LogBatalha;
import com.stefanini.pokemon.entities.Pokemon;
import com.stefanini.pokemon.entities.Treinador;
import com.stefanini.pokemon.entities.Turno;
import com.stefanini.pokemon.entities.Usuario;
import com.stefanini.pokemon.enums.EnumLevels;
import com.stefanini.pokemon.enums.EnumTipoPokemon;
import com.stefanini.pokemon.exception.TreinadorException;
import com.stefanini.pokemon.parsers.LogBatalhaParserDTO;
import com.stefanini.pokemon.parsers.PokemonParserDTO;
import com.stefanini.pokemon.parsers.TreinadorParserDTO;
import com.stefanini.pokemon.persistence.BaseDados;

public class BatalharService {

	@Inject
	TreinadorParserDTO treinadorParser;

	@Inject
	PokemonParserDTO pokemonParser;
	
	@Inject
	TreinadorService treinadorService;
	
	@Inject
	BaseDados baseDados;

	@Inject
	LogBatalhaParserDTO logBatalhaParser;

	public LogBatalhaDTO batalhar(TreinadorDTO treinadorDTOUm, TreinadorDTO treinadorDTODois) {
		Treinador treinadorUm = treinadorParser.toEntity(treinadorDTOUm);
		Treinador treinadorDois = treinadorParser.toEntity(treinadorDTODois);
		LogBatalha log = prepararLog(treinadorUm, treinadorDois);
		
		LogBatalhaDTO logBatalhaDTO = new LogBatalhaDTO();

		batalhaTreinador(treinadorUm, treinadorDois, log);

		setVencedor(log, treinadorUm, treinadorDois);
		
		log.getTreinadorUm().setPokemons(pokemonParser.toEntity(treinadorDTOUm.getPokemons()));
		log.getTreinadorDois().setPokemons(pokemonParser.toEntity(treinadorDTODois.getPokemons()));
		
		if(treinadorUm.getNome() == log.getVencedor().getNome()) {
			TreinadorDTO treinadorV = treinadorParser.toDTO(treinadorUm);
			evoluirPokemonTreinadorVencedor(treinadorV);
			Treinador t = treinadorParser.toEntity(treinadorV);
			baseDados.editarTreinador(t);
			
			TreinadorDTO treinadorD = treinadorParser.toDTO(treinadorDois);
			evoluirPokemonTreinadorDerrotado(treinadorD);
			Treinador d = treinadorParser.toEntity(treinadorD);
			baseDados.editarTreinador(d);
			
			baseDados.salvarLog(log);
			logBatalhaDTO = logBatalhaParser.toDTO(log);
			logBatalhaDTO.setTreinadorUm(treinadorV);
			logBatalhaDTO.setTreinadorDois(treinadorD);
		}
		
		if(treinadorDois.getNome() == log.getVencedor().getNome()) {
			TreinadorDTO treinadorV = treinadorParser.toDTO(treinadorDois);
			evoluirPokemonTreinadorVencedor(treinadorV);
			Treinador t = treinadorParser.toEntity(treinadorV);
			baseDados.editarTreinador(t);
			
			TreinadorDTO treinadorD = treinadorParser.toDTO(treinadorUm);
			evoluirPokemonTreinadorDerrotado(treinadorD);
			Treinador d = treinadorParser.toEntity(treinadorD);
			baseDados.editarTreinador(d);
			
			baseDados.salvarLog(log);
			logBatalhaDTO = logBatalhaParser.toDTO(log);
			logBatalhaDTO.setTreinadorDois(treinadorV);
			logBatalhaDTO.setTreinadorUm(treinadorD);
		}
		
		return logBatalhaDTO;
		
	}
	
	public LogBatalhaDTO treinar(TreinadorDTO treinadorDTO, PokemonDTO pokemonDTO) throws TreinadorException {
		Treinador treinador = treinadorParser.toEntity(treinadorDTO);
		Pokemon pokemon = pokemonParser.toEntity(pokemonDTO);
		
		Treinador fake = new Treinador();
		fake.setId(-999L);
		fake.setIdade(-99);
		fake.setNome("fake");
		fake.setUsuario(new Usuario());
		
		ArrayList<Pokemon> pokemonInimigo = new ArrayList<>();
		pokemonInimigo.add(pokemonParser.toEntity(getPokemonAleatorioPorNivel(pokemon.getLevel())));
		fake.setPokemons(pokemonInimigo);
				
		ArrayList<Pokemon> pokemonTreinador = new ArrayList<>();
		pokemonTreinador.add(pokemon);
		treinador.setPokemons(pokemonTreinador);
		
		LogBatalha log = prepararLog(treinador, fake);
		LogBatalhaDTO logBatalhaDTO = new LogBatalhaDTO();
		batalhaTreinador(treinador, fake, log);
		setVencedor(log, treinador, fake);
		
		log.getTreinadorUm().setPokemons(pokemonParser.toEntity(treinadorDTO.getPokemons()));
		
		definirAtributosPreEvolucao(pokemonDTO);
		
		if(treinador.getId() == log.getVencedor().getId()) {
			ganharExpVitoria(pokemonDTO);
		} else {
			ganharExpDerrota(pokemonDTO);
		}
		
		uparNivelPokemon(pokemonDTO);
		calculoDeAtributosGanhos(pokemonDTO);
		TreinadorDTO t = treinadorParser.toDTO(treinador);
		Pokemon p = pokemonParser.toEntity(pokemonDTO);
		baseDados.alterarPokemonDoTreinador(treinadorService.getTreinadorById(treinador.getId()), p);
		baseDados.salvarLog(log);
		List<PokemonDTO> pokemonTreinado = new ArrayList<>();
		pokemonTreinado.add(pokemonDTO);
		
		logBatalhaDTO = logBatalhaParser.toDTO(log);
		logBatalhaDTO.setTreinadorUm(t);
		logBatalhaDTO.getTreinadorUm().setPokemons(pokemonTreinado);
				
		return logBatalhaDTO;
		
	}
	
	private PokemonDTO getPokemonAleatorioPorNivel(Integer level) {
		List<Pokemon> todosOsPokemons = baseDados.getPokemons();
		Pokemon pkmAleatorio = todosOsPokemons.get((int) Math.round(Math.random() * (todosOsPokemons.size() - 1)));
		PokemonDTO pokeAleatorio = pokemonParser.toDTO(pkmAleatorio);
		
		for(int i = 0; i < level; i++) {
			pokeAleatorio.setLevel(pokeAleatorio.getLevel() + 1);
			evoluirAtributosPokemon(pokeAleatorio);
		}
		
		return pokeAleatorio;
	}
	
	private void definirAtributosPreEvolucao (PokemonDTO pokemon) {
		pokemon.setExpAntiga(pokemon.getExp());
		pokemon.setVidaAntiga(pokemon.getVida());
		pokemon.setDefesaAntiga(pokemon.getDefesa());
		pokemon.setAtaqueAntigo(pokemon.getAtaque());
		pokemon.setLevelAntigo(pokemon.getLevel());
	}
	
	private void calculoDeAtributosGanhos (PokemonDTO pokemon) {
		pokemon.setAtaqueGanho(pokemon.getAtaque() - pokemon.getAtaqueAntigo());
		pokemon.setDefesaGanha(pokemon.getDefesa() - pokemon.getDefesaAntiga());
		pokemon.setLevelGanho(pokemon.getLevel() - pokemon.getLevelAntigo());
		pokemon.setVidaGanha(pokemon.getVida() - pokemon.getVidaAntiga());
	}
	
	private void evoluirPokemonTreinadorVencedor(TreinadorDTO treinadorVencedor) {
		for(PokemonDTO pokemon : treinadorVencedor.getPokemons()) {
			definirAtributosPreEvolucao(pokemon);
			ganharExpVitoria(pokemon);
			uparNivelPokemon(pokemon);
		}
	}
	
	private void evoluirPokemonTreinadorDerrotado(TreinadorDTO treinadorDerrotado) {
		for(PokemonDTO pokemon : treinadorDerrotado.getPokemons()) {
			pokemon.setExpAntiga(pokemon.getExp());
			definirAtributosPreEvolucao(pokemon);
			ganharExpDerrota(pokemon);
			uparNivelPokemon(pokemon);
		}
	}
	
	private void uparNivelPokemon(PokemonDTO pokemon) {
		pokemon.setExpGanha(pokemon.getExp() - pokemon.getExpAntiga());
		for(EnumLevels e : EnumLevels.values()) {
			if(pokemon.getLevel() < e.getLevel() && pokemon.getExp() >= e.getExpNecessaria()) {	
				pokemon.setLevel(pokemon.getLevel() + 1);
				evoluirAtributosPokemon(pokemon);
				calculoDeAtributosGanhos(pokemon);
				pokemon.setExp(0l);
				return;
			}
		}
	}
	
	private void ganharExpVitoria(PokemonDTO pokemon) {
		if(pokemon.getExp() == 0 || pokemon.getLevel() == 1) {
			pokemon.setExp(pokemon.getExp() + 50l);
		}
		if(pokemon.getLevel() <= EnumLevels.LEVEL_25.getLevel() && pokemon.getLevel() > EnumLevels.LEVEL_1.getLevel()) {
			pokemon.setExp(pokemon.getExp() * 2);
		} 
		if(pokemon.getLevel() <= EnumLevels.LEVEL_50.getLevel() && pokemon.getLevel() > EnumLevels.LEVEL_25.getLevel()) {
			pokemon.setExp(pokemon.getExp() + (pokemon.getExp() / 2));
		} 
		if(pokemon.getLevel() <= EnumLevels.LEVEL_85.getLevel() && pokemon.getLevel() > EnumLevels.LEVEL_50.getLevel()) {
			pokemon.setExp(pokemon.getExp() + (pokemon.getExp() / 3));
		}
		if(pokemon.getLevel() <= EnumLevels.LEVEL_100.getLevel() && pokemon.getLevel() > EnumLevels.LEVEL_85.getLevel()) {
			pokemon.setExp(pokemon.getExp() + (pokemon.getExp() / 4));
		}
		if(pokemon.getLevel() == EnumLevels.LEVEL_100.getLevel()) {
			pokemon.setExp(pokemon.getExp());
		}
	}
	
	private void ganharExpDerrota(PokemonDTO pokemon) {
		if(pokemon.getExp() == 0 || pokemon.getLevel() == 1) {
			pokemon.setExp(pokemon.getExp() + 20l);
		}
		if(pokemon.getLevel() <= EnumLevels.LEVEL_25.getLevel() && pokemon.getLevel() > EnumLevels.LEVEL_1.getLevel()) {
			pokemon.setExp(pokemon.getExp() + 40l);
		} 
		if(pokemon.getLevel() <= EnumLevels.LEVEL_50.getLevel() && pokemon.getLevel() > EnumLevels.LEVEL_25.getLevel()) {
			pokemon.setExp(pokemon.getExp() + (pokemon.getExp() / 3));
		} 
		if(pokemon.getLevel() <= EnumLevels.LEVEL_85.getLevel() && pokemon.getLevel() > EnumLevels.LEVEL_50.getLevel()) {
			pokemon.setExp(pokemon.getExp() + (pokemon.getExp() / 4));
		}
		if(pokemon.getLevel() <= EnumLevels.LEVEL_100.getLevel() && pokemon.getLevel() > EnumLevels.LEVEL_85.getLevel()) {
			pokemon.setExp(pokemon.getExp() + (pokemon.getExp() / 5));
		}
		if(pokemon.getLevel() == EnumLevels.LEVEL_100.getLevel()) {
			pokemon.setExp(pokemon.getExp());
		}
	}
	
	private void evoluirAtributosPokemon(PokemonDTO pokemon) {
		pokemon.setAtaque((int) (pokemon.getAtaque() + Math.round((Math.random() * 5) + 1)));
		pokemon.setDefesa((int) (pokemon.getDefesa() + Math.round((Math.random() * 4) + 1)));
		pokemon.setVida(pokemon.getVida() + Math.round((Math.random() * 20) + 10));
	}

	private LogBatalha prepararLog(Treinador treinadorUm, Treinador treinadorDois) {
		LogBatalha log = new LogBatalha();

		log.setTreinadorUm(treinadorUm);
		log.setTreinadorDois(treinadorDois);
		log.setTurnos(new ArrayList<Turno>());

		return log;
	}

	private void batalhaTreinador(Treinador treinadorUm, Treinador treinadorDois, LogBatalha log) {
		while (treinadorUm.getPokemons().size() > 0 && treinadorDois.getPokemons().size() > 0) {
			Pokemon pokemonTreinadorUm = treinadorUm.getPokemons().get(0);
			Pokemon pokemonTreinadorDois = treinadorDois.getPokemons().get(0);

			batalhaPokemon(pokemonTreinadorUm, pokemonTreinadorDois, log);

			if (pokemonTreinadorUm.getVida() <= 0) {
				treinadorUm.getPokemons().remove(0);
			}
			if (pokemonTreinadorDois.getVida() <= 0) {
				treinadorDois.getPokemons().remove(0);
			}
		}
	}

	private void batalhaPokemon(Pokemon pokemonUm, Pokemon pokemonDois, LogBatalha log) {
		while (pokemonUm.getVida() > 0 && pokemonDois.getVida() > 0) {
			recalcularVidas(pokemonUm, pokemonDois, log);
		}
	}

	private void recalcularVidas(Pokemon pokemonUm, Pokemon pokemonDois, LogBatalha log) {
		Double danoCausadoPeloPokemonUm = Math.ceil(calcularDano(pokemonUm, pokemonDois));
		Double danoCausadoPeloPokemonDois = Math.ceil(calcularDano(pokemonDois, pokemonUm));
		
		if (danoCausadoPeloPokemonUm < 0) {
			pokemonUm.recalcularVida(danoCausadoPeloPokemonUm * (-1));
		} else {
			pokemonDois.recalcularVida(danoCausadoPeloPokemonUm);
		}

		if (pokemonDois.getVida() <= 0 || pokemonUm.getVida() <= 0) {
			Pokemon pokemonUmTemp = new Pokemon(pokemonUm);
			Pokemon pokemonDoisTemp = new Pokemon(pokemonDois);
			log.getTurnos().add(new Turno(pokemonUmTemp, pokemonDoisTemp, danoCausadoPeloPokemonUm, 0D));
			return;
		}

		if (danoCausadoPeloPokemonDois < 0) {
			pokemonDois.recalcularVida(danoCausadoPeloPokemonDois * (-1));
		} else {
			pokemonUm.recalcularVida(danoCausadoPeloPokemonDois);
		}

		Pokemon pokemonUmTemp = new Pokemon(pokemonUm);
		Pokemon pokemonDoisTemp = new Pokemon(pokemonDois);

		log.getTurnos()
				.add(new Turno(pokemonUmTemp, pokemonDoisTemp, danoCausadoPeloPokemonUm, danoCausadoPeloPokemonDois));
	}

	private Double calcularDano(Pokemon pokemonUm, Pokemon pokemonDois) {
		EnumTipoPokemon tipoPokemonUm = EnumTipoPokemon.values()[pokemonUm.getTipos().get(0).getId() - 1];
		EnumTipoPokemon tipoPokemonDois = EnumTipoPokemon.values()[pokemonDois.getTipos().get(0).getId() - 1];

		Double dano = EnumTipoPokemon.obterMultiplicadorDano(tipoPokemonUm, tipoPokemonDois);
		
		return (double) (
			(2D * pokemonUm.getLevel()/5D + 2D) *
			(((pokemonUm.getAtaque() * 1.0D) / (pokemonDois.getDefesa() * 1.0D))/50D + 2) * 
			(dano * (Math.random() * 15D + 85) / 100)
		);
	}

	private void setVencedor(LogBatalha log, Treinador treinadorUm, Treinador treinadorDois) {
		if (treinadorUm.getPokemons().size() != 0 || treinadorDois.getPokemons().size() != 0) {
			if (treinadorUm.getPokemons().size() > 0) {
				log.setVencedor(treinadorUm);
				log.setDerrotado(treinadorDois);
			} else {
				log.setVencedor(treinadorDois);
				log.setDerrotado(treinadorUm);
			}
		}
	}
}