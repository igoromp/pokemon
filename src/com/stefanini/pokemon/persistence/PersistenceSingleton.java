package com.stefanini.pokemon.persistence;

import java.util.ArrayList;
import java.util.List;

import com.stefanini.pokemon.entities.Pokemon;
import com.stefanini.pokemon.entities.TipoPokemon;
import com.stefanini.pokemon.entities.Treinador;
import com.stefanini.pokemon.entities.Usuario;
import com.stefanini.pokemon.log.Log;

public class PersistenceSingleton {
	private ArrayList<Pokemon> pokemons;
	private ArrayList<Treinador> treinadores;
	private ArrayList<TipoPokemon> tipos;
	private ArrayList<Usuario> usuarios;

	private static PersistenceSingleton instance;

	private PersistenceSingleton() {
		this.pokemons = new ArrayList<>();
		this.treinadores = new ArrayList<>();
		this.tipos = new ArrayList<>();
		this.usuarios = new ArrayList<>();

		Pokemon poke = new Pokemon();
		poke.setAtaque(50);
		poke.setDefesa(20);
		poke.setId(1L);
		poke.setLevel(5);
		poke.setNome("Charmander");
		poke.setVida(200D);
		Pokemon poke2 = new Pokemon();
		poke2.setAtaque(40);
		poke2.setDefesa(40);
		poke2.setId(2L);
		poke2.setLevel(5);
		poke2.setNome("Psyduck");
		poke2.setVida(150D);

		List<Pokemon> listaPokemon = new ArrayList<Pokemon>();

		Usuario usuario = new Usuario();
		usuario.setEmail("r@r");
		usuario.setId(1L);
		usuario.setSenha("1");

		Treinador treinador = new Treinador();
		treinador.setId(1L);
		treinador.setIdade(20);
		treinador.setNome("Regis");
		treinador.setPokemons(listaPokemon);
		treinador.setUsuario(usuario);

		this.addPokemon(poke);
		this.addPokemon(poke2);
		this.addTreinador(treinador);
	}

	public static PersistenceSingleton getInstance() {
		if (instance == null) {
			instance = new PersistenceSingleton();
		}

		return instance;
	}

	public void addPokemon(Pokemon pokemon) {
		this.pokemons.add(pokemon);
	}

	public void addTreinador(Treinador treinador) {
		this.treinadores.add(treinador);
	}

	public void addTipo(TipoPokemon tipo) {
		this.tipos.add(tipo);
	}

	public void addUsuario(Usuario usuario) {
		this.usuarios.add(usuario);
	}

	public ArrayList<Pokemon> getPokemons() {
		return this.pokemons;
	}

	public ArrayList<Treinador> getTreinadores() {
		return this.treinadores;
	}

	public ArrayList<TipoPokemon> getTipos() {
		return this.tipos;
	}

	public ArrayList<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public Treinador getTreinador(Long id) {
		for (Treinador each : this.treinadores) {
			Log.info(each.getNome());
			if (each.getId().equals(id)) {
				return each;
			}
		}
		return null;
	}

	public List<Treinador> getTodosTreinadores() {
		return this.treinadores;
	}

	public void excluirTreinadorPorId(Long id) throws Exception {
		try {
			this.treinadores.remove(getTreinador(id));
		}catch (Exception e) {
			throw new Exception("Erro ao excluir treinador");
		}
	}
}
