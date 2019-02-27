angular.module('PokemonModule')
    .controller('CadastrarTreinadorController', CadastrarTreinadorController);

CadastrarTreinadorController.$inject = ['$rootScope', '$location', 'UserService', 'PokemonService', 'Mensagens', 'TipoMensagem', 'QuantidadePokemons'];

function CadastrarTreinadorController($rootScope, $location, UserService, PokemonService, Mensagens, TipoMensagem, QuantidadePokemons) {
    var self = this;
    var qtdeSelecionados = 0;
    var eTreinadorValido;
    
    self.service = {};
    self.service.logar = UserService.logar;
    self.service.user = UserService;
    self.service.pokemon = PokemonService;
    //Recupera o treinador selecionado para edição
    self.service.user.treinadorSelecionado = JSON.parse(localStorage.getItem("treinadorLocalStorage"));
    $rootScope.editarTreinador = localStorage.getItem("editar");
    self.service.pokemon = PokemonService;
    self.treinador = self.service.user.treinadorSelecionado || {};
    self.selecionarPokemons = false;
    self.prosseguirClicado = false;

    console.log(self.service.user.treinadorSelecionado);

    self.prosseguir = function () {
        self.selecionarPokemons = true;
        if (!self.prosseguirClicado) {
            self.listarPokemons();
        }
    };

    self.voltar = function () {
        self.selecionarPokemons = false;
        self.prosseguirClicado = true;
    };

    self.cadastrar = function (treinador) {

        self.validaQuantidadePokemons();

        if (eTreinadorValido) {
            treinador.pokemons = [];

            self.service.pokemon.pokemons.forEach(pokemon => {
                if (pokemon.isSelected) {
                    treinador.pokemons.push(pokemon);
                }
            });

            self.service.user.cadastrar(treinador)
                .then(function (response) {
                    $rootScope.addMensagem({
                        texto: Mensagens.MENSAGEM_INCLUIR_TREINADOR_SUCESSO,
                        tipo: TipoMensagem.SUCCESS
                    }, true, true);
                    self.logarDeCadastro(treinador);
                    self.listarTreinadores();
                }, function (error) {
                    $rootScope.addMensagem({
                        texto: error.data.mensagem,
                        tipo: TipoMensagem.ERROR
                    }, true, true);
                });
        }
    };

    self.listarTreinadores = function () {
        $location.path('/listarTreinadores');
    };

    self.listarPokemons = function () {
        self.service.pokemon.listar()
            .then(function (response) {
                self.service.pokemon.pokemons = response.data;
            }, function (error) {
                $rootScope.addMensagem({
                    texto: Mensagens.MENSAGEM_LISTAR_POKEMONS_ERROR,
                    tipo: TipoMensagem.SUCCESS
                }, true, true);
            });
    };

    self.editar = function (treinador) {
        self.service.user.editar(treinador)
            .then(function (success) {

                $rootScope.addMensagem({
                    texto: Mensagens.MENSAGEM_ALTERAR_TREINADOR_SUCESSO,
                    tipo: TipoMensagem.SUCCESS
                }, true, true);

                self.treinador = {};
                $rootScope.editarTreinador = false;
                $rootScope.treinadorSelecionado = {};
                localStorage.removeItem("treinadorLocalStorage");
                localStorage.removeItem("editar");
                $location.path('/listarTreinadores');
            }, function (error) {
                $rootScope.addMensagem({
                    texto: error.data.mensagem,
                    tipo: TipoMensagem.ERROR
                }, true, true);
            });
    };

    self.cancelarEdicao = function () {
         $rootScope.editarTreinador = false;
        self.service.treinadorSelecionado = {};
        $rootScope.treinadorSelecionado = {};
        localStorage.clear();
        self.service.user = {};
        $location.path('/listarTreinadores');
    };

    self.selecionarPokemon = function (pokemon) {
        if (!pokemon.isSelected && qtdeSelecionados < QuantidadePokemons.QTDE_MAX_POKEMONS) {
            pokemon.isSelected = true;
            qtdeSelecionados++;
        } else if (pokemon.isSelected) {
            pokemon.isSelected = false;
            qtdeSelecionados--;
        }
    };

    self.validaQuantidadePokemons = function () {
        eTreinadorValido = true;

        if (qtdeSelecionados < QuantidadePokemons.QTDE_MIN_POKEMONS) {
            eTreinadorValido = false;

            $rootScope.addMensagem({
                texto: Mensagens.MENSAGEM_MIN_POKEMONS,
                tipo: TipoMensagem.ERROR
            }, true, true);


        }

        if (qtdeSelecionados > QuantidadePokemons.QTDE_MAX_POKEMONS) {
            eTreinadorValido = false;

            $rootScope.addMensagem({
                texto: Mensagens.MENSAGEM_MAX_POKEMONS,
                tipo: TipoMensagem.ERROR
            }, true, true);
        }
    };

    self.logarDeCadastro = function (treinador) {
        self.loginCadastro = {};
        self.loginCadastro.email = treinador.usuario.email;
        self.loginCadastro.senha = treinador.usuario.senha;

        self.service.logar(self.loginCadastro)
            .then(function (response) {
                $rootScope.user = response.data;
                $rootScope.addMensagem({
                    texto: Mensagens.MENSAGEM_LOGIN_SUCESSO,
                    tipo: TipoMensagem.SUCCESS
                }, true, true);
                $location.path('/listarTreinadores');
            }, function (error) {
                if (error.status == 503) {
                    $rootScope.addMensagem({
                        texto: Mensagens.MENSAGEM_SERVIDOR_INDISPONIVEL,
                        tipo: TipoMensagem.ERROR
                    }, true, true);
                } else {
                    $rootScope.addMensagem({
                        texto: error.data.mensagem,
                        tipo: TipoMensagem.ERROR
                    }, true, true);
                }
            });
    };
}