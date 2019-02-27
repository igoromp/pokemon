angular.module(APP_NAME)
    .factory('PokemonService', PokemonService);

PokemonService.$inject = ['$http'];

function PokemonService($http) {
    var _pokemons = [];

    var _tipos = [];

    var _pokemonSelecionado;

    var _alterarPokemon = function (pokemon) {
        return $http.put('http://pokemon.bb.com.br/pokemon/rest/pokemon', pokemon);
    };

    var _cadastrarPokemon = function(pokemon) {
        return $http.post('http://pokemon.bb.com.br/pokemon/rest/pokemon', pokemon);
    };

    var _listar = function() {
        return $http.get('http://pokemon.bb.com.br/pokemon/rest/pokemon');
    };

    var _listarTipos = function() {
        return $http.get('http://pokemon.bb.com.br/pokemon/rest/pokemon/tipos');
    };

    var _listarAfinidade = function(pokemon) {
        return $http.post('http://pokemon.bb.com.br/pokemon/rest/pokemon/afinidade', pokemon);
    };

    var _remover = function(indexPokemonSelecionado) {
        return $http.delete('http://pokemon.bb.com.br/pokemon/rest/pokemon/' + indexPokemonSelecionado);
    };

    return {
        pokemons: _pokemons,
        tipos: _tipos,
        alterarPokemon : _alterarPokemon,
        pokemonSelecionado: _pokemonSelecionado,
        cadastrarPokemon: _cadastrarPokemon,
        listar: _listar,
        listarTipos: _listarTipos,
        listarAfinidade: _listarAfinidade,
        remover: _remover
    };
}