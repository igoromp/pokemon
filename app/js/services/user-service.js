angular.module(APP_NAME)
    .factory('UserService', UserService);

    UserService.$inject = ['$rootScope','$http','API'];

   

    function UserService($rootScope,$http,API) {
        var _idTreinador = null;

        var _treinadores = [];

        var _pokemons = [];

        var _pokemonSelecionado = {};

        var _editarTreinador = false;

        var _indexTreinadorSelecionado;

        var _treinadorSelecionado = $rootScope.treinadorSelecionado || {};
        console.log('API',API);
        var _cadastrar = function (treinador) {
            return $http.post(API.TREINADOR, treinador);
        };

        var _alterarPokemonDoTreinador = function (indexTreinadorSelecionado, pokemonSelecionado) {
            return $http.put(API.TREINADOR + indexTreinadorSelecionado, pokemonSelecionado);
        };

        var _listar = function () {
            return $http.get(API.TREINADOR);
        };

        var _excluir = function(id) {
            return $http.delete(API.TREINADOR+'/'+id);
        };

        var _excluirPokemon = function(id, idTreinador) {
            return $http.delete(API.TREINADOR_EXCLUIR_POKEMON+ '/'+ id + '/' + idTreinador);
        };

        var _esta_logado = function(email){
            return $http.post(API.LOGIN+"/"+email);
        };

        var _logar = function(usuario){
            return $http.post(API.LOGIN, usuario);
        };

        var _deslogar = function(){
            return $http.get(API.LOGIN);
        };

        var _treinador = function(){
            return $http.get(API.LOGIN_OBTER_TREINADOR);
        };

        var _editar = function(treinador){
            return $http.put(API.TREINADOR,treinador);
        };

        var _listarPokemons = function() {
            return $http.get(API.LISTA_POKEMONS);
        };

        var _listarPokemonsTreinador = function(idTreinador) {
            return $http.get(API.TREINADOR_LISTA_POKEMONS+'/' + idTreinador);
        };

        var _addPokemonTreinador = function(id, idTreinador) {
            return $http.post(API.TREINADOR+'/' + id + '/' + idTreinador);
        };

        
        
        return {
            idTreinador: _idTreinador,
            treinadores: _treinadores,
            cadastrar: _cadastrar,
            listar: _listar,
            excluir: _excluir,
            logar: _logar,
            estaLogado:_esta_logado,
            deslogar: _deslogar,
            pokemonSelecionado: _pokemonSelecionado,
            indexTreinadorSelecionado: _indexTreinadorSelecionado,
            editar: _editar,
            treinador: _treinador,
            alterarPokemonDoTreinador: _alterarPokemonDoTreinador,
            excluirPokemon: _excluirPokemon,
            listarPokemons: _listarPokemons,
            listarPokemonsTreinador: _listarPokemonsTreinador,
            pokemons: _pokemons,
            addPokemonTreinador: _addPokemonTreinador,
            treinadorSelecionado: _treinadorSelecionado,
            editarTreinador: _editarTreinador,
        };
    }