package com.stefanini.pokemon.validadores;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.stefanini.pokemon.dtos.TreinadorDTO;
import com.stefanini.pokemon.entities.Treinador;
import com.stefanini.pokemon.enums.EnumConstantes;
import com.stefanini.pokemon.enums.EnumTreinadorMensagemException;
import com.stefanini.pokemon.exception.TreinadorException;

public class ValidarTreinador extends Validador<TreinadorDTO> {

	/** Validação de dados nulos e vazios. */
	@Override
	public void validar(TreinadorDTO treinador) throws Exception {
		if (treinador == null) {
			throw new TreinadorException(EnumTreinadorMensagemException.TREINADOR_VAZIO);
		}
		if (treinador.getId() == null || treinador.getId() < 0) {
			throw new TreinadorException(EnumTreinadorMensagemException.TREINADOR_ID_INVALIDO);
		}
		if (treinador.getIdade() == null || treinador.getIdade() <= 0 || treinador.getIdade() > 100) {
			throw new TreinadorException(EnumTreinadorMensagemException.TREINADOR_IDADE_INVALIDA);
		}
		if (treinador.getNome() == null || treinador.getNome().trim().isEmpty()) {
			throw new TreinadorException(EnumTreinadorMensagemException.TREINADOR_NOME_INVALIDO);
		}
		if (treinador.getPokemons() == null
				|| treinador.getPokemons().size() < EnumConstantes.POKEMON_QTDE_MINIMO.getValue()) {
			throw new TreinadorException(EnumTreinadorMensagemException.TREINADOR_POKEMONS_INVALIDOS);
		}
		if (treinador.getUsuario() == null) {
			throw new TreinadorException(EnumTreinadorMensagemException.TREINADOR_USUARIO_VAZIO);
		}
		if (treinador.getUsuario().getEmail() == null || treinador.getUsuario().getEmail().trim().isEmpty()
				|| !validarEmail(treinador.getUsuario().getEmail().trim())) {
			throw new TreinadorException(EnumTreinadorMensagemException.TREINADOR_EMAIL_INVALIDO);
		}
		if (treinador.getUsuario().getSenha() == null || treinador.getUsuario().getSenha().trim().isEmpty()) {
			throw new TreinadorException(EnumTreinadorMensagemException.TREINADOR_SENHA_INVALIDA);
		}
	}

	/** Validar o padrão de e-mail. */
	public boolean validarEmail(String email) {
		String EMAIL_PATTERN = "^[A-Za-z0-9]*\\@{1}[A-Za-z0-9]*(\\.{0,1}[A-Za-z0-9])*$";

		Pattern pattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);

		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

	/** Verifica se o e-mail já existe na base de dados. */
	public void verificarEmailExistente(Treinador treinador, List<Treinador> treinadores) throws TreinadorException {

		for (Treinador t : treinadores) {
			if (t.getUsuario().getEmail().equalsIgnoreCase(treinador.getUsuario().getEmail())) {
				throw new TreinadorException(EnumTreinadorMensagemException.TREINADOR_EMAIL_EXISTENTE);
			}
		}
	}

	/** Verifica se o usuário está forçando a alteração de e-mail. */
	public void verificarAlteracaoEmailUnico(Treinador treinador, List<Treinador> treinadores) throws Exception {

		for (Treinador t : treinadores) {
			if (treinador.getId() == t.getId()) {
				if (!treinador.getUsuario().getEmail().equalsIgnoreCase(t.getUsuario().getEmail())) {
					throw new Exception("Calma aí né David !");
				}
			}
		}
	}
}
