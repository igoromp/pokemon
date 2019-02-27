angular.module('PokemonModule')
    .directive('chart', Chart);

function Chart() {
    return{
        restrict: 'E',
        template: '<div></div>',
        scope: {
            configuracao : '='
        },
        link: function(scope,element){
            Highcharts.chart(element[0], scope.configuracao);
        }
    };
}