package com.stefanini.pokemon.enums;

public enum EnumPokemonMensagemException {
	
	POKEMON_VAZIO			("Pokemon vazio"),
	POKEMON_EXISTENTE		("Pokemon já cadastrado"),
	POKEMON_ID_INVALIDO		("ID inválido"),
	POKEMON_ATAQUE_INVALIDO	("Ataque inválido"),
	POKEMON_DEFESA_INVALIDA	("Defesa inválida"),
	POKEMON_LEVEL_INVALIDO	("Level inválido"),
	POKEMON_NOME_INVALIDO	("Nome inválido"),
	POKEMON_VIDA_INVALIDA	("Vida inválida"),
	POKEMON_TIPO_VAZIO		("Tipo de Pokemons vazio"),
	
	POKEMON_QTDE_MINIMO		("Mínimo de 1 Pokemons"),
	
	POKEMON_ERRO_CADASTRAR	("Erro ao cadastrar Pokemon"),
	POKEMON_ERRO_ALTERAR	("Erro ao alterar Pokemon"),
	POKEMON_ERRO_CONSULTAR	("Erro ao consultar Pokemon"),
	POKEMON_ERRO_EXCLUIR	("Erro ao excluir Pokemon"),
	
	POKEMON_DIF_BASE_DADOS	("Pokemon diferente da base de dados"),
	POKEMON_EM_USO			("Este pokemon está sendo usado"),
	
	POKEMON_INDISPONIVEL	("Nenhum pokemon disponível");
	
	private String msg;

	private EnumPokemonMensagemException(String msg) {
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}
}
