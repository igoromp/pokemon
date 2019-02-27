angular.module('PokemonModule')
    .controller('TreinarController', TreinarController);

TreinarController.$inject = ['$rootScope', '$location', 'BatalharService', '$timeout'];

function TreinarController($rootScope, $location, BatalharService, $timeout) {
    var self = this;

    self.service = BatalharService;

    $rootScope.editarTreinador = false;

    self.init = function () {
        self.pokemonParaTreinar = angular.copy($rootScope.pokemonParaTreinar);
        $rootScope.pokemonParaTreinar = null;

        //se chegou na página sem definir o pokemon a ser treinado
        if (!self.pokemonParaTreinar) {
            $location.path('/listarTreinadores');
        }

        self.service.treinar(self.pokemonParaTreinar)
            .then(function (resposta) {
                self.log = resposta.data;
                $timeout(function (){
                    self.mostrarGanhoDeExp();
                }, 1000);

            }, function (erro) {});
    };

    self.mostrarGanhoDeExp = function() {
        $('#modal-xp').modal('show');
    };
}