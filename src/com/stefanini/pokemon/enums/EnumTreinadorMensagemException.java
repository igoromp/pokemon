package com.stefanini.pokemon.enums;

public enum EnumTreinadorMensagemException {
	
	TREINADOR_VAZIO				 ("Treinador vazio"),
	TREINADOR_ID_INVALIDO		 ("ID inválido"),
	TREINADOR_IDADE_INVALIDA	 ("Idade inválida"),
	TREINADOR_NOME_INVALIDO		 ("Nome inválido"),
	TREINADOR_POKEMONS_INVALIDOS ("Pokemons inválidos"),
	TREINADOR_USUARIO_VAZIO		 ("Usuario inválido"),
	TREINADOR_EMAIL_INVALIDO	 ("E-mail inválido"),
	TREINADOR_EMAIL_EXISTENTE	 ("E-mail já cadastrado"),
	TREINADOR_SENHA_INVALIDA	 ("Senha inválida"),
	
	TREINADOR_NAO_ENCONTRADO	 ("Treinador não encontrado"),
	TREINADOR_LOGIN_INCORRETO 	 ("Usuário ou senha incorretos"),
	TREINADOR_JA_LOGADO			 ("Usuário já logado"),
	
	TREINADOR_ERRO_CADASTRAR	 ("Erro ao cadastrar treinador"),
	TREINADOR_ERRO_CONSULTAR	 ("Erro ao consultar treinador"),
	TREINADOR_ERRO_ALTERAR		 ("Erro ao alterar treinador"),
	TREINADOR_ERRO_EXCLUIR		 ("Erro ao excluir treinador"),
	
	TREINADOR_ERRO_CADASTRAR_PKM ("Erro ao cadastrar pokemons no treinador"),
	
	TREINADOR_ERRO_LOG			 ("Erro ao obter log da batalha"),
	TREINADOR_ERRO_BATALHA		 ("Não há registros de batalha");
	
	private String msg;
	
	private EnumTreinadorMensagemException(String msg) {
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}
}
