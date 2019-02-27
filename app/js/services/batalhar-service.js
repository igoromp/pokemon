angular.module('PokemonModule')
    .factory('BatalharService', BatalharService);

BatalharService.$inject = ['$http'];


function BatalharService($http) {

    var URL = 'http://pokemon.bb.com.br:8080';
    var API={
        BATALHAR: URL+'/pokemon/rest/batalhar',
        TREINAR:URL+'/pokemon/rest/batalhar/treinar'
    };


    var _batalhar = function (adversario) {
        return $http.post(API.BATALHAR, adversario);
    };

    var _treinar = function (pokemon) {
        return $http.post(API.TREINAR, pokemon);
    };

    var _obterLogBatalha = function (idTreinador) {
        return $http.get(API.BATALHAR + idTreinador);
    };

    return {
        batalhar: _batalhar,
        treinar: _treinar,
        obterLogBatalha: _obterLogBatalha
    };
}