angular.module('PokemonModule')
    .directive('logBatalha', LogBatalha);

LogBatalha.$inject = ['CoresTiposPokemon'];

function LogBatalha(CoresTiposPokemon) {
    return {
        template:
            `
            <table class="table table-hover text-center" ng-class="{'table-scroll' : scrollable}">
            <thead>
                <tr align="center">
                    <td ng-class="log.vencedor.nome === log.treinadorUm.nome ? 'logVitoria' : 'logDerrota'" colspan="3">{{log.treinadorDois.nome == 'fake' ? log.turnos[0].pokemonUm.nome : log.treinadorUm.nome}}</td>
                    <td>x</td>
                    <td ng-class="log.vencedor.nome === log.treinadorDois.nome ? 'logVitoria' : 'logDerrota'" colspan="3">{{log.treinadorDois.nome == 'fake' ? log.turnos[0].pokemonDois.nome : log.treinadorDois.nome}}</td>
                </tr>
                <tr align="center">
                    <td>Nome</td>
                    <td>Dano</td>
                    <td>Vida</td>
                    <td>x</td>
                    <td>Vida</td>
                    <td>Dano</td>
                    <td>Nome</td>
                </tr>
            </thead>
            <tbody>
                <tr align="center" ng-repeat="turno in log.turnos">
                    <td ng-class="{'logVitoria' : turno.pokemonDois.vida <= 0, 'logDerrota' : turno.pokemonUm.vida <= 0}">{{turno.pokemonUm.nome}}</td>
                    <td ng-style="{'border': '1px solid ' + cores[turno.pokemonUm.tipos[0].descricao.toUpperCase()]}" ng-class="{'logVitoria' : turno.pokemonDois.vida <= 0, 'logDerrota' : turno.pokemonUm.vida <= 0}">{{turno.danoCausadoPokemonUm}}</td>
                    <td ng-class="{'logVitoria' : turno.pokemonDois.vida <= 0, 'logDerrota' : turno.pokemonUm.vida <= 0}">{{turno.pokemonUm.vida}}</td>
                    <td>x</td>
                    <td ng-class="{'logVitoria' : turno.pokemonUm.vida <= 0,'logDerrota' : turno.pokemonDois.vida <= 0}">{{turno.pokemonDois.vida}}</td>
                    <td ng-style="{'border': '1px solid ' + cores[turno.pokemonDois.tipos[0].descricao.toUpperCase()]}" ng-class="{'logVitoria' : turno.pokemonUm.vida <= 0,'logDerrota' : turno.pokemonDois.vida <= 0}">{{turno.danoCausadoPokemonDois}}</td>
                    <td ng-class="{'logVitoria' : turno.pokemonUm.vida <= 0,'logDerrota' : turno.pokemonDois.vida <= 0}">{{turno.pokemonDois.nome}}</td>
                </tr>
            </tbody>
        </table>
        `,
        scope: {
           log : '=',
           scrollable : '='
        },
        link: function($scope){
            $scope.cores = CoresTiposPokemon;
        }
    };
}
