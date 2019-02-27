angular.module('PokemonModule')
    .controller('ListarPokemonController', ListarPokemonController);

ListarPokemonController.$inject = ['$rootScope', '$location', 'PokemonService', 'Mensagens', 'TipoMensagem'];

function ListarPokemonController($rootScope, $location, PokemonService, Mensagens, TipoMensagem) {

    var self = this;
    self.service = PokemonService;
    $rootScope.editarTreinador = false;
    self.coluna = 'id';
    self.teste = [];

    self.indexPokemonSelecionado = {};
    self.filtroPorNome = '';

    self.init = function () {
        self.listar();
    };

    self.listar = function () {
        self.service.listar()
        .then(function(response) {
            self.service.pokemons = response.data;
            // self.service.pokemons.forEach(pokemon => {
            //     self.setTipo(pokemon);            
            // });

        }, function(error) {
            $rootScope.addMensagem({texto: error.data.mensagem, tipo: TipoMensagem.ERROR}, true, true);
        });
    };

    self.novoPokemon = function () {
        $location.path("/cadastrar");
    };

    self.editarPokemon = function (pokemon, index) {
        pokemon.index = index;
        $rootScope.editarTreinador = false;
        self.service.pokemonSelecionado = pokemon;
        $location.path("/cadastrar");
    };

    // self.setTipo = function (pokemon) {
    //     self.service.tipos.forEach(tipo => {
    //         if (pokemon.tipos.id === tipo.id) {
    //             pokemon.tipos.descricao = tipo.descricao;
    //         }
    //     });
    // };

    self.excluir = function (indexPokemonSelecionado) {
        self.service.remover(indexPokemonSelecionado)
            .then(function (response){
                $rootScope.addMensagem({texto: Mensagens.MENSAGEM_EXCLUIR_POKEMON_SUCESSO, tipo: TipoMensagem.SUCCESS}, true, true);
                self.listar();
            }, function(error){
                $rootScope.addMensagem({ texto: error.data.mensagem, tipo: TipoMensagem.ERROR }, true, true);
            });
            self.listar();
    };

    self.clickColuna = function (coluna) {
        if (self.coluna === coluna) {
            self.reverse = !self.reverse;
        }
        self.coluna = coluna;
    };
}