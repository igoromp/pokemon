angular.module('PokemonModule')
.constant('Mensagens', {
    MENSAGEM_INCLUIR_POKEMON_SUCESSO: 'Pokemon incluído com sucesso.',
    MENSAGEM_EXCLUIR_POKEMON_SUCESSO: 'Pokemon excluído com sucesso',
    MENSAGEM_ALTERAR_POKEMON_SUCESSO: 'Pokemon alterado com sucesso',
    MENSAGEM_CAPTURAR_POKEMON_SUCESSO: 'Pokemon capturado com sucesso',

    MENSAGEM_INCLUIR_TREINADOR_SUCESSO: 'Treinador incluído com sucesso.',
    MENSAGEM_EXCLUIR_TREINADOR_SUCESSO: 'Treinador excluído com sucesso.',
    MENSAGEM_ALTERAR_TREINADOR_SUCESSO: 'Treinador alterado com sucesso.',

    MENSAGEM_LISTAR_POKEMONS_ERROR: 'Erro ao listar pokemons.',
    
    MENSAGEM_LOGIN_SUCESSO: 'Login efetuado com sucesso.',
    MENSAGEM_LOGIN_ERROR: 'Usuário ou senha inválido.',
    
    MENSAGEM_MIN_POKEMONS: 'Mínimo de 3 pokemons',
    MENSAGEM_MAX_POKEMONS: 'Máximo de 5 pokemons',

    MENSAGEM_NOME_POKEMON: 'Por favor inserir Nome do Pokemon.',
    MENSAGEM_TIPO_POKEMON: 'Por favor inserir Tipo do Pokemon.',
    MENSAGEM_TIPO_EXISTENTE: 'Tipo já incluído',
    MENSAGEM_EXCLUIR_POKEMON_ERROR: 'Este pokemon está sendo usado.',

    MENSAGEM_SERVIDOR_INDISPONIVEL : 'Servidor indisponível'

}).constant('TipoMensagem', {
    SUCCESS: 'success',
    ERROR: 'danger',
    INFO: 'info',
    WARNING: 'warning'

}).constant('QuantidadePokemons', {
    QTDE_MIN_POKEMONS: 3,
    QTDE_MAX_POKEMONS: 5
}).constant('CoresTiposPokemon', {
    NORMAL : 'white', 
    FOGO : 'red',
    AGUA : 'blue',
    ELETRICO : 'yellow', 
    GRAMA : 'green',
    GELO : 'aquamarine',
    LUTADOR : 'brown', 
    VENENOSO : 'purple', 
    TERRESTREE : 'burlywood', 
    VOADOR : 'bisque', 
    PSIQUICO : 'pink', 
    INSETO : 'green', 
    PEDRA : 'gray', 
    FANTASMA : 'black', 
    DRAGAO : 'orange', 
    SOMBRIO : 'black', 
    ACO : 'gray',
    FADA : 'pink'
});