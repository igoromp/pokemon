var APP_NAME = 'PokemonModule';

angular.module(APP_NAME, ['ngRoute'])
    .config(config)
    .run(run);

config.$inject = ['$routeProvider', '$httpProvider'];
run.$inject = ['$rootScope', '$location', 'UserService','Session'];

function config($routeProvider, $httpProvider) {
    $routeProvider
        .when('/login', {
            templateUrl: '/app/pages/login.html',
            controller: 'LoginController as vm'
        })
        .when('/listar', {
            templateUrl: '/app/pages/listar.html',
            controller: 'ListarPokemonController as vm'
        })
        .when('/cadastrar', {
            templateUrl: '/app/pages/cadastrar.html',
            controller: 'CadastrarPokemonController as vm'
        })
        .when('/listarTreinadores', {
            templateUrl: '/app/pages/listarTreinadores.html',
            controller: 'ListarTreinadoresController as vm'
        })
        .when('/cadastrarTreinador', {
            templateUrl: '/app/pages/cadastrarTreinador.html',
            controller: 'CadastrarTreinadorController as vm'
        })
        .when('/batalhar', {
            templateUrl: '/app/pages/batalhar.html',
            controller: 'BatalharController as vm'
        })
        .when('/logBatalha', {
            templateUrl: '/app/pages/logBatalha.html',
            controller: 'LogBatalhaController as vm'
        })
        .when('/capturar', {
            templateUrl: '/app/pages/capturar.html',
            controller: 'CapturarController as vm'
        })
        .when('/treinar',{
            templateUrl: '/app/pages/treinar.html',
            controller: 'TreinarController as vm'
        })
        .otherwise({
            redirectTo: '/login'
        });

    $httpProvider.defaults.withCredentials = true;
    $httpProvider.defaults.headers.common.Authorization = 'pode passar';
}

function run($rootScope, $location, UserService,Session) {
    var lastPage = '';
    $rootScope.user = null;

    UserService.treinador()
        .then(function (response) {
            $rootScope.user = response.data;
            $location.path(lastPage !== '/login' ? lastPage : '/listarTreinadores');
        }, function (error) {
            $rootScope.user = {};
        });

    $rootScope.eLogado = function () {
        //return $rootScope.user && !!Object.keys($rootScope.user).length;
        return Session.length() > 0 ;
    };

    $rootScope.$on('$routeChangeStart', function (evt, route) {
        lastPage = lastPage ? lastPage : route.originalPath;
        if ($rootScope.eLogado()) {
            if (route.originalPath === '/login' || route.originalPath === '/cadastrarTreinador' ||
                route.originalPath === '/listarTreinadores') {
                if (route.originalPath === '/cadastrarTreinador' && $rootScope.treinadorSelecionado != undefined) {
                    $location.path('/cadastrarTreinador');
                }
                if ($rootScope.treinadorSelecionado == undefined) {
                    $location.path('/listarTreinadores');
                }
            }
        } else {
            if (route.originalPath !== '/login' && route.originalPath !== '/cadastrarTreinador') {
                $location.path('/login');
            }
        }
    });

    $rootScope.addMensagem = function (mensagem) {
        $(function () {
            $.bootstrapGrowl(mensagem.texto, {
                type: mensagem.tipo,
                align: 'center',
                width: 'auto',
                delay: 1500,
                allow_dismiss: false,
                stackup_spacing: -60
            });
        });
    };


    $rootScope.deslogar = function () {
        UserService.deslogar()
            .then(function (response) {
                $rootScope.user = {};
                Session.destroy();
                $location.path('/login');
            }, function (error) {

            });
    };
}