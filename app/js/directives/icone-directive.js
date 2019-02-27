angular.module('PokemonModule')
    .directive('icone',Icone);

    Icone.$inject = [];

    function Icone(){
        return {
            restrict: 'E',
            template : `
                <div ng-if="editar">
                   <p>Esquerdo:</p>
                   <input type="number" ng-model="cor[0]" min="0" max="255">
                   <input type="number" ng-model="cor[1]" min="0" max="255">
                   <input type="number" ng-model="cor[2]" min="0" max="255">
                   <br>
                   <p>Direito:</p>
                   <input type="number" ng-model="corEsquerda[0]" min="0" max="255">
                   <input type="number" ng-model="corEsquerda[1]" min="0" max="255">
                   <input type="number" ng-model="corEsquerda[2]" min="0" max="255">
                </div>
                <table class="{{editar? 'editarIcone' : 'icone'}}">
                    <tr ng-repeat="linha in model">
                        <td ng-repeat="ponto in linha" 
                        style="background-color:rgb({{ponto[0]}},{{ponto[1]}},{{ponto[2]}})"
                        ng-mousedown="editar ? setPonto($event, ponto) : null"
                        ng-mouseover="editar ? setPontoMouseOver($event,ponto) : null"
                        oncontextmenu="return false">
                        </td>
                    </tr>
                </table>
            `,
            scope: {
                model : '=',
                editar: '@'
            },
            link : function(scope,element){
                scope.cor = [255,255,255];
                scope.corEsquerda = [255,255,255];

                scope.setPontoMouseOver = function(event, ponto){
                    if(event.buttons){
                        var corParaUsar = event.which == 1 ? scope.cor : scope.corEsquerda;
                        ponto[0] = corParaUsar[0];
                        ponto[1] = corParaUsar[1];
                        ponto[2] = corParaUsar[2];
                    }
                };

                scope.setPonto = function(event, ponto){
                    var corParaUsar = event.which == 1 ? scope.cor : scope.corEsquerda;

                    ponto[0] = corParaUsar[0];
                    ponto[1] = corParaUsar[1];
                    ponto[2] = corParaUsar[2];
                };
            }
        };
    }