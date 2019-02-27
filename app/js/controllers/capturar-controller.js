angular.module('PokemonModule')
    .controller('CapturarController', CapturarController);

CapturarController.$inject = ['PokemonService', 'UserService', '$interval', '$timeout', 'TipoMensagem', 'Mensagens', '$rootScope', '$location'];

function CapturarController(PokemonService, UserService, $interval, $timeout, TipoMensagem, Mensagens, $rootScope, $location) {
    var self = this;
    const ALTURA_TELA = $('#tela_capturar').height();
    const LARGURA_TELA = $('#tela_capturar').width();
    const DURACAO_MINIMA_POKEMON = 500;
    const DURACAO_MAXIMA_POKEMON = 500;
    const INTERVALO_APARICAO_POKEMON = 3000;

    self.service = {};
    self.service.pokemon = PokemonService;
    self.service.user = UserService;

    $rootScope.editarTreinador = false;

    self.tempoRestante = 15;

        var timer = $interval(function () {
            if (self.tempoRestante <= 0) {
                $interval.cancel(timer);
            }
            self.tempoRestante -= 1;
        }, 1000);

    self.init = function () {
        self.pokemonsNaTela = [];
        self.service.pokemon.listar()
            .then(function (resposta) {
                self.pokemons = resposta.data;
                $interval(function () {
                    var tempPokemon = _buscarPokemonAleatorio();
                    self.pokemonsNaTela.push(tempPokemon);
                    _apagarPokemonAposTempo(tempPokemon);
                }, INTERVALO_APARICAO_POKEMON);
                $timeout(function (){
                    $location.path('/listarTreinadores');
                }, 15000);
            }, function (erro) {
                $location.path('/listarTreinadores');
                $rootScope.addMensagem({
                    texto: erro.message || erro.mensagem,
                    tipo: TipoMensagem.ERROR
                }, true, true);
            });
    };

    function _apagarPokemonAposTempo(pokemonNaTela) {
        $timeout(function () {
            _excluirPokemonDaTela(pokemonNaTela);
        }, Math.random() * DURACAO_MAXIMA_POKEMON + DURACAO_MINIMA_POKEMON);
    }

    function _buscarPokemonAleatorio() {
        var randomPokemon = self.pokemons[Math.round(Math.random() * self.pokemons.length - 1)];
        var isDuplicate = !!self.pokemonsNaTela.find(pokemon => {
            return pokemon.id == randomPokemon.id;
        });
        if (isDuplicate) {
            return _buscarPokemonAleatorio();
        } else {
            randomPokemon.esquerda = Math.round(Math.random() * (LARGURA_TELA));
            randomPokemon.topo = Math.round(Math.random() * (ALTURA_TELA));
            return angular.copy(randomPokemon);
        }
    }

    self.capturar = function (pokemon) {
        var idTreinador = $rootScope.user.id;
        self.service.user.addPokemonTreinador(pokemon.id, idTreinador)
        .then(function () {
            _excluirPokemonDaTela(pokemon);
                $rootScope.addMensagem({
                    texto: Mensagens.MENSAGEM_CAPTURAR_POKEMON_SUCESSO,
                    tipo: TipoMensagem.SUCCESS
                }, true, true);
            }, function (error) {
                $rootScope.addMensagem({
                    texto: error.data.mensagem,
                    tipo: TipoMensagem.ERROR
                }, true, true);
            });
    };

    function _excluirPokemonDaTela(pokemonNaTela){
        self.pokemonsNaTela = self.pokemonsNaTela.filter(pokemon => {
            return pokemon.id != pokemonNaTela.id;
        });
    }

}