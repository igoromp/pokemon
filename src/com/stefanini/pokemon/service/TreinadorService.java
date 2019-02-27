package com.stefanini.pokemon.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.stefanini.pokemon.dtos.LogBatalhaDTO;
import com.stefanini.pokemon.dtos.PokemonDTO;
import com.stefanini.pokemon.dtos.TreinadorDTO;
import com.stefanini.pokemon.entities.Pokemon;
import com.stefanini.pokemon.entities.Treinador;
import com.stefanini.pokemon.enums.EnumPokemonMensagemException;
import com.stefanini.pokemon.enums.EnumTreinadorMensagemException;
import com.stefanini.pokemon.exception.PokemonException;
import com.stefanini.pokemon.exception.TreinadorException;
import com.stefanini.pokemon.parsers.LogBatalhaParserDTO;
import com.stefanini.pokemon.parsers.PokemonParserDTO;
import com.stefanini.pokemon.parsers.TreinadorParserDTO;
import com.stefanini.pokemon.persistence.BaseDados;
import com.stefanini.pokemon.validadores.ValidarPokemon;
import com.stefanini.pokemon.validadores.ValidarTreinador;

public class TreinadorService extends ServiceBase {

	@Inject
	private BaseDados baseDados;
	private ValidarTreinador validarTreinador = new ValidarTreinador();
	private ValidarPokemon validarPokemon = new ValidarPokemon();

	private TreinadorParserDTO treinadorParserDTO = new TreinadorParserDTO();
	private PokemonParserDTO pokemonParserDTO = new PokemonParserDTO();
	private LogBatalhaParserDTO logParserDTO = new LogBatalhaParserDTO();

	private Long idMaiorTreinador = (long) 0;

	public void maiorIdTreinador() {
		if (baseDados.getTreinadores().size() == 0) {

			idMaiorTreinador = (long) -1;

		} else {
			for (int i = 0; i < baseDados.getTreinadores().size(); i++) {
				if (baseDados.getTreinadores().get(i).getId() > idMaiorTreinador) {
					idMaiorTreinador = baseDados.getTreinadores().get(i).getId();
				}
			}
		}
	}

	public TreinadorDTO incluir(TreinadorDTO dto) throws Exception {

		if (dto.getUsuario().getTipoAdmin() == null) {
			dto.getUsuario().setTipoAdmin(2);
		}
		maiorIdTreinador();
		dto.setId(idMaiorTreinador + 1);
		validarTreinador.validar(dto);
		validarTreinador.verificarEmailExistente(treinadorParserDTO.toEntity(dto), baseDados.getTreinadores());
		validarPokemon.verificarAlteracaoPokemonCadastrar(pokemonParserDTO.toEntityList(dto.getPokemons()),
				baseDados.getPokemons());

		Treinador treinador = treinadorParserDTO.toEntity(dto);

		try {
			baseDados.addTreinador(treinador);
		} catch (Exception e) {
			throw new TreinadorException(EnumTreinadorMensagemException.TREINADOR_ERRO_CADASTRAR);
		}

		return treinadorParserDTO.toDTO(treinador);
	}

	public void incluirPokemonNoTreinador(Long id, Long idTreinador) throws Exception {
		try {
			baseDados.addPokemonTreinador(id, idTreinador);
		} catch (PokemonException e) {
			throw e;
		} catch (Exception e) {
			throw new TreinadorException(EnumTreinadorMensagemException.TREINADOR_ERRO_CADASTRAR_PKM);
		}
	}

	public void alterar(TreinadorDTO dto) throws Exception {

		validarTreinador.validar(dto);
		validarTreinador.verificarAlteracaoEmailUnico(treinadorParserDTO.toEntity(dto), baseDados.getTreinadores());

		try {
			baseDados.editarTreinador(treinadorParserDTO.toEntity(dto));
		} catch (Exception e) {
			throw new TreinadorException(EnumTreinadorMensagemException.TREINADOR_ERRO_ALTERAR);
		}
	}

	public void excluirPokemon(Long id, Long idTreinador) throws Exception {
		try {
			baseDados.excluirPokemonDoTreinador(id, idTreinador);
		} catch (PokemonException e) {
			throw e;
		} catch (Exception e) {
			throw new PokemonException(EnumPokemonMensagemException.POKEMON_ERRO_EXCLUIR);
		}
	}

	public void excluir(Long id) throws TreinadorException {
		try {
			baseDados.excluirTreinador(id);
		} catch (TreinadorException e) {
			throw e;
		} catch (Exception e) {
			throw new TreinadorException(EnumTreinadorMensagemException.TREINADOR_ERRO_EXCLUIR);
		}
	}

	public List<TreinadorDTO> listar() throws TreinadorException {
		List<TreinadorDTO> treinadoresDTO = new ArrayList<>();

		try {
			treinadoresDTO = treinadorParserDTO.toDTO(baseDados.getTreinadores());
		} catch (Exception e) {
			throw new TreinadorException(EnumTreinadorMensagemException.TREINADOR_ERRO_CONSULTAR);
		}

		return treinadoresDTO;
	}

	public TreinadorDTO getTreinadorByEmail(String email) throws TreinadorException {
		List<TreinadorDTO> treinadores = this.listar();
		for (TreinadorDTO treinador : treinadores) {
			if (treinador.getUsuario().getEmail().equals(email)) {
				return treinador;
			}
		}
		throw new TreinadorException(EnumTreinadorMensagemException.TREINADOR_LOGIN_INCORRETO);
	}

	public Treinador getTreinadorById(Long id) throws TreinadorException {
		for (Treinador treinador : baseDados.getTreinadores()) {
			if (treinador.getId().equals(id)) {
				return treinador;
			}
		}
		throw new TreinadorException(EnumTreinadorMensagemException.TREINADOR_NAO_ENCONTRADO);
	}

	public List<PokemonDTO> listarPokemonsTreinador(Long id) throws Exception {
		Treinador treinador = getTreinadorById(id);
		List<PokemonDTO> pokemonsDTO = new ArrayList<>();

		try {
			pokemonsDTO = pokemonParserDTO.toDTO(baseDados.getPokemonsDisponiveis(treinador));
		} catch (PokemonException e) {
			throw e;
		} catch (Exception e) {
			throw new PokemonException(EnumPokemonMensagemException.POKEMON_ERRO_CONSULTAR, e);
		}

		return pokemonsDTO;
	}

	public void alterarPokemon(Long idTreinador, PokemonDTO dto) throws Exception {
		Treinador treinador = getTreinadorById(idTreinador);
		validarPokemon.validar(dto);
		Pokemon pokemon = pokemonParserDTO.toEntity(dto);
		try {
			baseDados.alterarPokemonDoTreinador(treinador, pokemon);
		} catch (Exception e) {
			throw new PokemonException(EnumPokemonMensagemException.POKEMON_ERRO_ALTERAR);
		}
	}

	public List<LogBatalhaDTO> obterLogBatalha(Treinador treinador) throws Exception {
		List<LogBatalhaDTO> logDTO = new ArrayList<>();
		try {
			logDTO = logParserDTO.toDtoList(baseDados.obterLog(treinador));
		} catch (Exception e) {
			throw new Exception(EnumTreinadorMensagemException.TREINADOR_ERRO_LOG.getMsg(), e);
		}
		
		if (logDTO.isEmpty()) {
			throw new TreinadorException(EnumTreinadorMensagemException.TREINADOR_ERRO_BATALHA);
		}
		
		return logDTO;
	}
}
