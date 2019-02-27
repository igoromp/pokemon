angular.module('PokemonModule')
	.directive('cardPokemon', CardPokemon);

function CardPokemon() {
	return {
		templateUrl: '../app/pages/card.html',
		scope: {
			tab: '=?',
			pokemons: '=?'
		},
		link: function (scope) {
				scope.selecionado = function (selecionado) {
				scope.tab = selecionado;
				scope.estaSelecionado();
			};
			scope.estaSelecionado = function () {
				return scope.tab;
			};
		}
	};
}