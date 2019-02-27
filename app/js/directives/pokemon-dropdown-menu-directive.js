angular.module('PokemonModule')
    .directive('pokemonDropdownMenu', PokemonDropdownMenu);

function PokemonDropdownMenu() {
    return  {
        restrict: 'E',
        transclude: true,
        template: 
        `<div class="dropdown">
            <button class="btn btn-secondary dropdown-toggle" type="button" id="{{id}}" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                Opções
            </button>
            <div class="dropdown-menu" aria-labelledby="{{id}}" ng-transclude>
            </div>
        </div>`,
        scope: {
            id: '@'
        },
        link: function($scope) {
            $scope.id = $scope.id || ('menu-' + new Date().getTime());
        }
    };
}