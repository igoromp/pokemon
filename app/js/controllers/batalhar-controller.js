angular.module('PokemonModule')
    .controller('BatalharController', BatalharController);

BatalharController.$inject = ['$rootScope', '$interval', '$location', 'BatalharService', 'CoresTiposPokemon', '$timeout'];

function BatalharController($rootScope, $interval, $location, BatalharService, CoresTiposPokemon, $timeout) {
    var self = this;

    self.service = BatalharService;
    self.tabelaLog = false;
    self.cores = CoresTiposPokemon;

    self.init = function () {

        self.adversario = $rootScope.adversario;
        if (!self.adversario) {
            $location.path('/listarTreinadores');
        }
        self.tempoRestante = 3;

        var timer = $interval(function () {
            if (self.tempoRestante <= 0) {
                $interval.cancel(timer);
            }
            self.tempoRestante -= 1;
        }, 1000);

        self.service.batalhar(self.adversario)
            .then(function (response) {
                self.log = response.data;
                self.tabelaDanosTreinadorUm = {};
                self.tabelaDanosTreinadorDois = {};
                for (var turno of self.log.turnos) {
                    if (self.tabelaDanosTreinadorUm[turno.pokemonUm.nome]) {
                        self.tabelaDanosTreinadorUm[turno.pokemonUm.nome] += turno.danoCausadoPokemonUm;
                    } else {
                        self.tabelaDanosTreinadorUm[turno.pokemonUm.nome] = turno.danoCausadoPokemonUm;
                    }

                    if (self.tabelaDanosTreinadorDois[turno.pokemonDois.nome]) {
                        self.tabelaDanosTreinadorDois[turno.pokemonDois.nome] += turno.danoCausadoPokemonDois;
                    } else {
                        self.tabelaDanosTreinadorDois[turno.pokemonDois.nome] = turno.danoCausadoPokemonDois;
                    }
                }
                
                $timeout(function (){
                    self.mostrarGanhoDeExp();
                }, 3000);
                

            }, function (error) {
                self.erro = 'Desculpe,algo errado aconteceu :/';
            });

            
        };
    
    
    self.mostrarGanhoDeExp = function() {
        $('#modal-xp').modal('show');
    };
    

    self.mostrarLog = function () {
        // self.tabelaLog = !self.tabelaLog;

        // if (!self.tabelaLog) {
        //     self.exibirLogNome = 'Exibir Log';
        // } else {
        //     self.exibirLogNome = 'Ocultar Log';
        // }
    };
}