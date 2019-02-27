package com.stefanini.pokemon.service;

import java.util.ArrayList;
import java.util.List;

import com.stefanini.pokemon.entities.Icone;
import com.stefanini.pokemon.entities.Pokemon;
import com.stefanini.pokemon.entities.Treinador;
import com.stefanini.pokemon.entities.Usuario;
import com.stefanini.pokemon.enums.EnumConstantes;

public class TreinadorFactory extends AbstractFactory<Treinador> {

	@Override
	public List<Treinador> gerar(List<Pokemon> l) {
		List<Treinador> treinadores = new ArrayList<>();

		Treinador treinador1 = criar(Long.valueOf(treinadores.size()), "Davide Viana", "david@gmail.com", "123", l, 1);
		treinadores.add(treinador1);
		Treinador treinador2 = criar(Long.valueOf(treinadores.size()), "Italo Bento", "italo@gmail.com", "124", l, 2);
		treinadores.add(treinador2);
		Treinador treinador3 = criar(Long.valueOf(treinadores.size()), "Leandro Ferreira", "leandro@gmail.com", "125",
				l, 2);
		treinadores.add(treinador3);
		Treinador treinador4 = criar(Long.valueOf(treinadores.size()), "Régis Bittencourt", "regis@gmail.com", "126", l,
				2);
		treinadores.add(treinador4);
		Treinador treinador5 = criar(Long.valueOf(treinadores.size()), "Warley Monteiro", "warley@gmail.com", "127", l,
				2);
		treinadores.add(treinador5);

		return treinadores;
	}

	private Treinador criar(Long id, String nome, String username, String senha, List<Pokemon> pokemons, int admin) {
		Treinador treinador = new Treinador();
		List<Pokemon> meusPokemons = new ArrayList<>();
		int pokemonIndex;
		boolean flag;

		treinador.setId(id);
		treinador.setNome(nome);
		treinador.setIdade(((int) (Math.random() * 20) + 20));

		// Gera pokemon aleatório para cada treinador
		while (meusPokemons.size() < EnumConstantes.POKEMON_QTDE_MAXIMO.getValue()) {
			pokemonIndex = (int) (Math.random() * pokemons.size());
			flag = false;
			
			for (int aux = 0; aux < meusPokemons.size(); aux++) {
				if (pokemonIndex == meusPokemons.get(aux).getId()) {
					flag = true;
				}
			}
			if (!flag) {
				meusPokemons.add(pokemons.get(pokemonIndex));
			}
		}

		treinador.setPokemons(meusPokemons);
		treinador.setUsuario(criar(id, username, senha, admin));

		return treinador;
	}

	private Usuario criar(Long id, String username, String senha, int admin) {
		Usuario usuario = new Usuario();

		usuario.setId(id);
		usuario.setSenha(senha);
		usuario.setEmail(username);
		usuario.setTipoAdmin(admin);
		if(username.contains("regis")) {
			usuario.setIcone(Icone.getIcone());
		}else if (username.contains("david")) {
			usuario.setIcone(Icone.getIconeFastasma());
		}else if (username.contains("italo")) {
			usuario.setIcone(Icone.getIconeFastasma());
		}else if(username.contains("warley")) {
			usuario.setIcone(Icone.getIconeCharmander());
		}else if(username.contains("leandro")) {
			usuario.setIcone(Icone.getIconeCogumelo());
		}
		else {
			usuario.setIcone(Icone.getRandomIcone());			
		}

		return usuario;
	}

	@Override
	public List<Treinador> gerar() {
		return null;
	}

}
