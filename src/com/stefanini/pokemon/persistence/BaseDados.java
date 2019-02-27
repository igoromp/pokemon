package com.stefanini.pokemon.persistence;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Singleton;

import com.stefanini.pokemon.entities.LogBatalha;
import com.stefanini.pokemon.entities.Pokemon;
import com.stefanini.pokemon.entities.Treinador;
import com.stefanini.pokemon.enums.EnumConstantes;
import com.stefanini.pokemon.enums.EnumPokemonMensagemException;
import com.stefanini.pokemon.enums.EnumTreinadorMensagemException;
import com.stefanini.pokemon.exception.PokemonException;
import com.stefanini.pokemon.exception.TreinadorException;
import com.stefanini.pokemon.service.PokemonFactory;
import com.stefanini.pokemon.service.TreinadorFactory;

@Singleton
public class BaseDados {

	@Inject
	private TreinadorFactory treinadorFactory;

	@Inject
	private PokemonFactory pokemonFactory;

	private List<Treinador> treinadores = new ArrayList<>();
	private List<Pokemon> pokemons = new ArrayList<>();
	private List<LogBatalha> logs = new ArrayList<>();

	@PostConstruct
	public void init() {
		try {
			pokemons.addAll(pokemonFactory.gerar());
			treinadores.addAll(treinadorFactory.gerar(pokemons));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Pokemon> getPokemons() {
		return pokemons;
	}

	public void setPokemons(List<Pokemon> pokemons) {
		this.pokemons = pokemons;
	}

	public List<Treinador> getTreinadores() {
		return treinadores;
	}

	public void setTreinadores(List<Treinador> treinadores) {
		this.treinadores = treinadores;
	}

	public List<Pokemon> getPokemonsDisponiveis(Treinador treinador) throws PokemonException {
		List<Pokemon> pokemonsDisponiveis = new ArrayList<>(this.pokemons);
		for (Pokemon pokemon : treinador.getPokemons()) {
			for (Pokemon pokemonGeral : this.pokemons) {
				if (pokemon.getId().equals(pokemonGeral.getId())) {
					pokemonsDisponiveis.remove(pokemonGeral);
				}
			}
		}

		if (pokemonsDisponiveis.isEmpty()) {
			throw new PokemonException(EnumPokemonMensagemException.POKEMON_INDISPONIVEL);
		}

		return pokemonsDisponiveis;
	}

	public void addTreinador(Treinador treinador) {
		treinadores.add(treinador);
	}

	public void editarTreinador(Treinador treinador) {
		for (Treinador treinadorTemp : this.treinadores) {
			if (treinadorTemp.getId().equals(treinador.getId())) {
				this.treinadores.set(this.treinadores.indexOf(treinadorTemp), treinador);
				break;
			}
		}
	}

	public void excluirTreinador(Long id) throws TreinadorException {
		for (Treinador treinador : this.treinadores) {
			if (treinador.getId().equals(id)) {
				this.treinadores.remove(treinador);
				return;
			}
		}
		throw new TreinadorException(EnumTreinadorMensagemException.TREINADOR_NAO_ENCONTRADO);
	}

	public void addPokemon(Pokemon pokemon) {
		this.pokemons.add(pokemon);
	}

	public void alterarPokemon(Pokemon pokemon) {
		for (Pokemon t : pokemons) {
			if (t.getId().equals(pokemon.getId())) {
				this.pokemons.remove(t);
				this.pokemons.add(pokemon);
				for (Treinador treinador : this.getTreinadores()) {
					for (Pokemon pokemonT : treinador.getPokemons()) {
						if (pokemonT.getId().equals(pokemon.getId())) {
							pokemonT.setTipos(pokemon.getTipos());
							 pokemonT.setNome(pokemon.getNome());
						}
					}
				}
				break;
			}
		}
	}

	public void deletePokemon(Pokemon pokemon) {
		this.pokemons.remove(pokemon);
	}

	public void delete(Long idPokemon) throws PokemonException {

		for (Pokemon pkmBackEnd : this.getPokemons()) {
			if (pkmBackEnd.getId().equals(idPokemon)) {
				for (Treinador t : this.getTreinadores()) {
					for (Pokemon pokemonsT : t.getPokemons()) {
						if (pokemonsT.getId().equals(pkmBackEnd.getId()) && t.getUsuario().getTipoAdmin() != 1) {
							throw new PokemonException(EnumPokemonMensagemException.POKEMON_EM_USO);
						}
					}
				}
			}
		}
		// Se não tem treinador com esse pokemon selecionado pode remover
		for (Pokemon pokemon : this.getPokemons()) {
			if (pokemon.getId().equals(idPokemon)) {
				this.pokemons.remove(pokemon);
				break;
			}
		}
	}

	public void addPokemonTreinador(Long id, Long idTreinador) throws PokemonException {
		for (Treinador treinador : this.getTreinadores()) {
			if (treinador.getId().equals(idTreinador)) {
				for (Pokemon pokemon : this.getPokemons()) {
					if (pokemon.getId().equals(id)) {
						treinador.getPokemons().add(pokemon);
						break;
					}
				}
			}
		}
	}

	public void excluirPokemonDoTreinador(Long id, Long idTreinador) throws PokemonException {
		List<Treinador> treinadores = this.getTreinadores();
		for (Treinador treinador : treinadores) {
			if (treinador.getId().equals(idTreinador)) {
				for (Pokemon pokemon : treinador.getPokemons()) {
					if (pokemon.getId().equals(id)) {
						if (treinador.getPokemons().size() <= EnumConstantes.POKEMON_QTDE_MINIMO.getValue()) {
							throw new PokemonException(EnumPokemonMensagemException.POKEMON_QTDE_MINIMO);
						}
						treinador.getPokemons().remove(pokemon);
						break;
					}
				}
			}
		}
	}

	public void alterarPokemonDoTreinador(Treinador treinador, Pokemon pokemon) {

		for (Treinador t : this.getTreinadores()) {
			if (t.getId().equals(treinador.getId())) {
				for (Pokemon p : treinador.getPokemons()) {
					if (p.getId().equals(pokemon.getId())) {
						treinador.getPokemons().remove(p);
						treinador.getPokemons().add(pokemon);
						break;
					}
				}
			}
		}

	}
	
	public void salvarLog(LogBatalha log) {
		log.setData(new Date());
		this.logs.add(log);
	}
	
	public List<LogBatalha> getLogs() {
		return this.logs;
	}
	
	public List<LogBatalha> obterLog(Treinador treinador) {
		List<LogBatalha> meuLog = new ArrayList<>();

		for (LogBatalha log : getLogs()) {
			if (log.getTreinadorUm().getId().equals(treinador.getId()) || log.getTreinadorDois().getId().equals(treinador.getId())) {
				meuLog.add(log);
			}
		}
		return meuLog;
	}
}
