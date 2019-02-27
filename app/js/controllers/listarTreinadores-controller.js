angular.module('PokemonModule')
    .controller('ListarTreinadoresController', ListarTreinadoresController);

ListarTreinadoresController.$inject = ['$rootScope', 'UserService', 'PokemonService', 'BatalharService', 'TipoMensagem', 'Mensagens', '$location', '$timeout'];

function ListarTreinadoresController($rootScope, UserService, PokemonService, BatalharService, TipoMensagem, Mensagens, $location, $timeout) {


    var self = this;
    self.service = UserService;
    self.servicePokemon = PokemonService;
    self.batalharService = BatalharService;
    self.pokemons = [];
    self.selecionado = {};
    // self.tipos = [];
    self.detalharPkm = [];
    self.pokemonModal = {};
    self.idTreinadorModal = '';
    self.idTreinador = '';
    self.pokemonSelecionado = {};
    self.filtro = '';
    self.coluna = 'nome';

    self.init = function () {
        self.listar();
    };

    self.listar = function () {
        self.service.listar()

            .then(function (response) {
                $rootScope.editarTreinador = false;
                self.service.treinadores = response.data;
                self.selecionado = self.service.treinadores.find(treinador => {
                    return treinador.id == self.selecionado.id;
                });
            }, function (error) {
                $rootScope.addMensagem({
                    texto: error.mensagem,
                    tipo: TipoMensagem.ERROR
                }, true, true); //mudei tipo de mensagem e botei pra apagar
            });

    };
    self.alterarValor = function (pokemon, idSelecionado) {
        self.pokemonModal = pokemon;
        self.idTreinadorModal = idSelecionado;
        $('#modal-pokemons').modal('hide');
        $('#modal-exclusaoPkm').modal('show');
    };

    self.excluir = function (treinador) {
        self.service.excluir(treinador.id)
            .then(function (response) {
                $rootScope.addMensagem({
                    texto: Mensagens.MENSAGEM_EXCLUIR_TREINADOR_SUCESSO,
                    tipo: TipoMensagem.SUCCESS
                }, true, true);
                if ($rootScope.user.usuario.tipoAdmin != 1) {
                    $timeout(function () {
                        $location.path("/login");
                    }, 200);
                } else {
                    self.listar();
                }
            }, function (error) {
                $rootScope.addMensagem({
                    texto: error.data.mensagem,
                    tipo: TipoMensagem.ERROR
                }, true, true);
            });
    };

    self.editar = function (treinador) {
        $rootScope.editarTreinador = true;
        //Convertendo treinador selecionado para string para ser amarmazenado no localstorage
        var treinadorJSON = JSON.stringify(treinador);
        //Armazenando o treinador a ser editado no localstorage
        localStorage.setItem("treinadorLocalStorage", treinadorJSON);
        localStorage.setItem("editar", "true");
        $rootScope.treinadorSelecionado = treinador;
        $location.path('/cadastrarTreinador');
    };

    self.removerPokemonDaLista = function (pokemonModal, idTreinadorModal) {
        self.idTreinador = idTreinadorModal;
        self.service.excluirPokemon(pokemonModal.id, self.idTreinador)
            .then(function (response) {
                self.listar();
                $('#modal-pokemons').modal('show');
                // self.selecionado.pokemons.splice(pokemon.id, 1);
                $rootScope.addMensagem({
                    texto: Mensagens.MENSAGEM_EXCLUIR_POKEMON_SUCESSO,
                    tipo: TipoMensagem.SUCCESS
                }, true, true);
            }, function (error) {
                $rootScope.addMensagem({
                    texto: error.data.mensagem,
                    tipo: TipoMensagem.ERROR
                }, true, true);
                $('#modal-pokemons').modal('show');
            });
    };

    self.batalhar = function (treinadorSelecionado) {
        $timeout(function () {
            $rootScope.adversario = treinadorSelecionado;
            $location.path('/batalhar');
        }, 200);
    };

    self.adicionarPokemonTreinador = function (idTreinador) {
        self.filtroPorNome = '';

        $('#modal-pokemons').modal('hide');
        $('#modal-inclusao').modal('show');

        self.service.listarPokemonsTreinador(idTreinador)
            .then(function (response) {
                self.service.pokemons = response.data;
            }, function (error) {
                $rootScope.addMensagem({
                    texto: error.data.mensagem,
                    tipo: TipoMensagem.ERROR
                }, true, true);
            });
    };

    self.detalhar = function (pokemon, treinador) {
        self.pkmTemp = angular.copy(pokemon);
        var cont = 0;
        var cont2 = 0;
        self.servicePokemon.listar()
            .then(function (response) {
                self.pokemons = response.data;
                self.servicePokemon.listarAfinidade(pokemon)
                    .then(function (response) {
                        self.afinidades = response.data;
                        // console.log(self.afinidades);
                        // console.log(self.pokemons);


                        pokemon.evolucoes.forEach(evolucao => {
                            self.pokemons.forEach(pokeBack => {
                                if (pokeBack.id == evolucao) {
                                    pokeBack.afinidades = {};
                                    self.detalharPkm.push(pokeBack);
                                }
                            });
                        });
                        self.detalharPkm.forEach(pokeTemp => {
                            if (pokemon.id == pokeTemp.id) {
                                pokeTemp = angular.copy(pokemon);
                                pokeTemp.afinidades = {};
                                self.detalharPkm.splice(cont, 1, pokeTemp);
                            }
                            cont++;
                            // console.log("contador 1: " + cont);
                        });
                        self.detalharPkm.forEach(pokeTemp => {
                            // console.log(pokeTemp);
                                    console.log(self.afinidades[pokeTemp.tipos[0].id]);
                                    pokeTemp.afinidades = self.afinidades[pokeTemp.tipos[0].id];
                                    self.detalharPkm.splice(cont2, 1, pokeTemp);
                            // console.log(self.detalharPkm);
                            // console.log(self.afinidades);
                            console.log(cont2);
                            cont2++;
                        });

                    });


            }, function (error) {
                $rootScope.addMensagem({
                    texto: error.mensagem,
                    tipo: TipoMensagem.ERROR
                }, true, true); //mudei tipo de mensagem e botei pra apagar
            });


        $('#modal-pokemons').modal('hide');
        $('#modal-detalharPkm').modal('show');
    };

    self.exibirLista = function () {
        self.detalharPkm = [];
        $('#modal-pokemons').modal('show');
    };

    self.selecionarPokemons = function (pokemon, idTreinador) {
        self.selecionado.pokemons.push(pokemon);
        $('#modal-inclusao').modal('hide');
        $('#modal-pokemons').modal('show');
        self.service.addPokemonTreinador(pokemon.id, idTreinador)
            .then(function () {
                $rootScope.addMensagem({
                    texto: Mensagens.MENSAGEM_INCLUIR_POKEMON_SUCESSO,
                    tipo: TipoMensagem.SUCCESS
                }, true, true);
            }, function (error) {
                $rootScope.addMensagem({
                    texto: error.data.mensagem,
                    tipo: TipoMensagem.ERROR
                }, true, true);
            });
    };

    self.editarPokemonDoTreinador = function (pokemon, idTreinador) {
        self.copia = angular.copy(self.selecionado);
        self.copiaPokemon = angular.copy(pokemon);
        self.pokemonSelecionado = pokemon;
        self.idTreinadorSelecionado = idTreinador;
        $('#modal-pokemons').modal('hide');
        $('#modal-editar').modal('show');
        self.listarTipos();
    };

    self.sairModalInclusao = function () {
        $('#modal-inclusao').modal('hide');
        $('#modal-pokemons').modal('show');
    };

    self.cancelarExclusao = function () {
        $('#modal-pokemons').modal('show');
    };

    self.listarTipos = function () {
        self.servicePokemon.listarTipos()
            .then(function (response) {
                self.tipos = response.data;
            }, function (error) {
                $rootScope.addMensagem({
                    texto: error.data.mensagem,
                    tipo: TipoMensagem.ERROR
                }, true, true);
            });
    };

    self.selecTipo = function () {
        self.pokemonSelecionado.tipos.push(self.pokemonSelecionado.tipo);
        if (self.pokemonSelecionado.tipos.length == 2) {
            if (self.pokemonSelecionado.tipos[0].descricao === self.pokemonSelecionado.tipos[1].descricao) {
                self.pokemonSelecionado.tipos.splice(self.pokemonSelecionado.tipos[0], 1);
                $rootScope.addMensagem({
                    texto: Mensagens.MENSAGEM_TIPO_EXISTENTE,
                    tipo: TipoMensagem.ERROR
                }, true, true);
                delete self.pokemonSelecionado.tipo;
            }
        }
        delete self.pokemonSelecionado.tipo; //Deleta o atributo tipo que foi incluído no array de tipos.
    };

    self.apagarTipoPkn = function (index) {
        self.pokemonSelecionado.tipos.splice(index, 1);
        delete self.pokemonSelecionado.tipo;
    };

    self.salvarPokemon = function (idTreinadorSelecionado, pokemonSelecionado) {

        self.service.alterarPokemonDoTreinador(idTreinadorSelecionado, pokemonSelecionado)
            .then(function (response) {
                $rootScope.addMensagem({
                    texto: Mensagens.MENSAGEM_ALTERAR_POKEMON_SUCESSO,
                    tipo: TipoMensagem.SUCCESS
                }, true, true);
                self.service.pokemonSelecionado = {
                    tipos: []
                };
                $('#modal-editar').modal('hide');
                $('#modal-pokemons').modal('show');
                // self.voltar();

            }, function (error) {
                $rootScope.addMensagem({
                    texto: error.data.mensagem,
                    tipo: TipoMensagem.ERROR
                }, true, true);
            });

    };

    self.cancelar = function () {
        self.listar();
        $timeout(function () {
            self.selecionado = self.copia;
            $('#modal-pokemons').modal('show');
        }, 100);
    };

    self.capturarPokemons = function () {
        $('#modal-pokemons').modal('hide');
        $timeout(function () {
            $location.path('/capturar');
        }, 200);
    };

    self.treinar = function (pokemonParaTreinar) {
        $('#modal-pokemons').modal('hide');
        $rootScope.pokemonParaTreinar = pokemonParaTreinar;
        $timeout(function () {
            $location.path('/treinar');
        }, 200);
    };

    self.exibirLogBatalhaPorUsuario = function (idTreinador) {
        self.batalharService.obterLogBatalha(idTreinador)
            .then(function (response) {
                self.service.logBatalha = response.data;
                self.service.idTreinador = idTreinador;
                $location.path('/logBatalha');
            }, function (error) {
                $rootScope.addMensagem({
                    texto: error.data.mensagem,
                    tipo: TipoMensagem.ERROR
                }, true, true);
                $location.path('/listarTreinadores');
            });
    };

    self.clickColuna = function (coluna) {
        if (self.coluna === coluna) {
            self.reverse = !self.reverse;
        }
        self.coluna = coluna;
    };
}