angular.module('PokemonModule')
    .controller('LogBatalhaController', LogBatalhaController);

LogBatalhaController.$Inject = [
    'UserService',
    'BatalharService',
    '$location',
    '$rootScope',
    'TipoMensagem'
];

function LogBatalhaController(UserService, BatalharService, $location, $rootScope) {

    var self = this;
    self.service = UserService;
    self.batalharService = BatalharService;
    self.logLuta = [];
    self.logTreino = [];
    self.tabGrafico = 1;

    self.init = function () {
        if (self.service.logBatalha) {
            self.obterLog();
        } else {
            $location.path('/listarTreinadores');
        }
    };

    self.obterLog = function () {
        var vitoriasDerrotas = _getVitoriasDerrotas(self.service.logBatalha);
        var danoCausadoPorPokemon = _getDanoPorPokemon(self.service.logBatalha);
        var danoPorTempo = _getDanoPorTempo(self.service.logBatalha);
        self.graficoPizzaVitoriasDerrotas = _criarGraficoPizza(vitoriasDerrotas, 'Vitorias/Derrotas');
        self.graficoPizzaDano = _criarGraficoColuna(danoCausadoPorPokemon, 'Dano por Pokemon');
        self.graficoLinhaTemporal = _criarGraficoLinha(danoPorTempo, 'Quantidade de golpes por batalha');
        self.separarLutaTreinamento(self.service.logBatalha);
    };

    self.separarLutaTreinamento = function (logBatalha) {
        for (var log of logBatalha) {
            if (log.treinadorDois.nome !== 'fake') {
                self.logLuta.push(log);
            } else {
                self.logTreino.push(log);
            }
        }
    };

    function _criarGraficoPizza(dados, titulo) {
        return {
            chart: {
                type: 'pie',
                backgroundColor: 'rgba(255, 255, 255, 0.0)'
            },
            colors: ['green', 'red'],
            title: {
                text: titulo
            },
            tooltip: {
                headerFormat: '',
                pointFormat: '<span style="color:{point.color}">●</span><b>{point.name}</b>: {point.y}'
            },
            plotOptions: {
                pie: {
                    dataLabels: {
                        enabled: false
                    },
                    showInLegend: true,
                    allowPointSelect : true
                }
            },
            series: [{
                name: titulo,
                colorByPoint: true,
                data: dados
            }]
        };
    }

    function _criarGraficoColuna(dados, titulo) {
        return {
            chart: {
                type: 'column',
                backgroundColor: 'rgba(255, 255, 255, 0.0)'
            },
            colors: ['#5C7782', '#ffcc29', '#A8CF45', '#A85182', '#029EFB', '#007DBF', '#41A4D8', '#5C2A89', '#FF7F0E', '#2CA02C'],
            title: {
                text: titulo
            },
            tooltip: {
                headerFormat: '',
                pointFormat: '<span style="color:{point.color}">●</span><b>{point.name}</b>: {point.y}'
            },
            xAxis: {
                type: 'category'
            },
            legend: {
                enabled: false
            },
            series: [{
                name: titulo,
                colorByPoint: true,
                data: dados
            }]
        };
    }

    function _criarGraficoLinha(dados, titulo) {
        return {
            chart: {
                type: 'spline'
            },
            title: {
                text: titulo
            },

            yAxis: {
                title: {
                    text: 'Quantidade de golpes'
                }
            },
            xAxis: {
                type: 'datetime'
            },

            series: dados
        };
    }

    function _getVitoriasDerrotas(logs) {
        var vitorias = 0;
        var derrotas = 0;
        for (var log of logs) {
            if (log.vencedor.id == self.service.idTreinador) {
                vitorias += 1;
            } else {
                derrotas += 1;
            }
        }

        return [{
            name: 'Vitorias',
            y: vitorias,
        }, {
            name: 'Derrotas',
            y: derrotas
        }];
    }

    function _getDanoPorPokemon(logs) {
        var pokemons = {};
        for (var log of logs) {
            var pokemon;
            if (log.treinadorUm.id == self.service.idTreinador) {
                pokemon = 'pokemonUm';
            } else {
                pokemon = 'pokemonDois';
            }
            for (var turno of log.turnos) {
                if (pokemons[turno[pokemon].id]) {
                    pokemons[turno[pokemon].id].dano += turno['danoCausadoP' + pokemon.slice(1)];
                } else {
                    pokemons[turno[pokemon].id] = {
                        dano: turno['danoCausadoP' + pokemon.slice(1)],
                        nome: turno[pokemon].nome
                    };
                }
            }
        }

        var arrayPokemons = [];

        for (var key in pokemons) {
            arrayPokemons.push([
                pokemons[key].nome,
                pokemons[key].dano,
            ]);
        }

        return arrayPokemons;
    }

    function _getDanoPorTempo(logs) {
        var dados = [];
        var pokemon = {};

        for (var log of logs) {

            pokemonPosicao = log.treinadorUm.id === self.service.idTreinador ? 'pokemonUm' : 'pokemonDois';

            for (var turno of log.turnos) {
                idPokemon = turno[pokemonPosicao].id;

                if (pokemon[idPokemon]) {
                 
                    if (log.data === pokemon[idPokemon].data[pokemon[idPokemon].data.length - 1][0]) {
                        pokemon[idPokemon].data[pokemon[idPokemon].data.length - 1][1]++;
                    } else {
                        pokemon[idPokemon].data.push([log.data, 1]);
                    }
              
                } else {
                    pokemon[idPokemon] = {
                        'name': turno[pokemonPosicao].nome,
                        'data': [
                            [
                                log.data,
                                1
                            ]
                        ]
                    };
                }

            }
        }

        for (var key in pokemon) {
            dados.push(pokemon[key]);
        }

        return dados;
    }

    self.tabSelecionada = function (id) {
        self.tabGrafico = id;
    };
}