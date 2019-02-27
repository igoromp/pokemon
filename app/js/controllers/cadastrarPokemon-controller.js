angular.module('PokemonModule')
    .controller('CadastrarPokemonController', CadastrarPokemonController);

CadastrarPokemonController.$inject = ['$location', '$rootScope', 'PokemonService', 'Mensagens', 'TipoMensagem'];

function CadastrarPokemonController($location, $rootScope, PokemonService, Mensagens, TipoMensagem) {

    var self = this;
    self.service = PokemonService;

    if (!$rootScope.editarTreinador) {
        $rootScope.editarTreinador = false;
    }

    self.pokemon = self.service.pokemonSelecionado || {
        tipos: []
    };
    self.tipos = []; //recebe os tipos do back-end

    self.init = function () {
        self.listarTipos();
    };

    self.apagarTipoPkn = function (index) {
        self.pokemon.tipos.splice(index, 1);
        delete self.pokemon.tipo;
    };

    self.listarTipos = function () {
        self.service.listarTipos()
            .then(function (response) {
                self.tipos = response.data;
            }, function (error) {
                $rootScope.addMensagem({
                    texto: error.data.mensagem,
                    tipo: TipoMensagem.ERROR
                }, true, true);
            });
    };

    self.cadastrarPokemon = function () {
        if (self.pokemon.nome != undefined) {
            if (self.pokemon.tipos.length != 0) {
                self.service.cadastrarPokemon(self.pokemon)
                    .then(function () {
                        $rootScope.addMensagem({
                            texto: Mensagens.MENSAGEM_INCLUIR_POKEMON_SUCESSO,
                            tipo: TipoMensagem.SUCCESS
                        }, true, true);
                        self.voltar();
                    }, function (error) {
                        $rootScope.addMensagem({
                            texto: error.data.mensagem,
                            tipo: TipoMensagem.ERROR
                        }, true, true);
                    });
            }
        }
    };

    self.alterarPokemon = function () {
        self.service.alterarPokemon(self.pokemon)
            .then(function (response) {
                $rootScope.addMensagem({
                    texto: Mensagens.MENSAGEM_ALTERAR_POKEMON_SUCESSO,
                    tipo: TipoMensagem.SUCCESS
                }, true, true);
                self.service.pokemonSelecionado = {
                    tipos: []
                };
                self.voltar();

            }, function (error) {
                $rootScope.addMensagem({
                    texto: error.data.mensagem,
                    tipo: TipoMensagem.ERROR
                }, true, true);
            });
    };

    self.selecTipo = function () {
        self.pokemon.tipos.push(self.pokemon.tipo);
        if (self.pokemon.tipos.length == 2) {
            if (self.pokemon.tipos[0].descricao === self.pokemon.tipos[1].descricao) {
                self.pokemon.tipos.splice(self.pokemon.tipos[0], 1);
                $rootScope.addMensagem({
                    texto: Mensagens.MENSAGEM_TIPO_EXISTENTE,
                    tipo: TipoMensagem.ERROR
                }, true, true);
                delete self.pokemon.tipo;
            }
        }
        delete self.pokemon.tipo; //Deleta o atributo tipo que foi incluído no array de tipos.
    };


    self.voltar = function () {
        $location.path('/listar');
        self.pokemon.id = undefined;
        self.service.pokemonSelecionado = {
            tipos: []
        };
    };

    self.apagarTipoPkn = function (index) {
        self.pokemon.tipos.splice(index, 1);
        delete self.pokemon.tipo;
    };

}