angular.module(APP_NAME)
    .controller('LoginController', LoginController);

LoginController.$inject = [
    '$rootScope',
    '$location',
    'Mensagens',
    'TipoMensagem',
    'UserService',
    'Session'
];

function LoginController($rootScope, $location, Mensagens, TipoMensagem,UserService,Session) {

    var self = this;
    self.service = UserService;
    $rootScope.treinadorSelecionado = {};
    $rootScope.user = {};

    self.login = function (usuario) {

        if(Session.get(usuario.email)){
            self.service.estaLogado(usuario)
                .then(function(resp){
                    if(resp) {$location.path('/listarTreinadores');}
                })
                .catch(console.log);
        }
            self.service.logar(usuario)
            .then(function (response) {
                $rootScope.user = response.data;
                $rootScope.addMensagem({
                    texto: Mensagens.MENSAGEM_LOGIN_SUCESSO,
                    tipo: TipoMensagem.SUCCESS
                }, true, true);

                Session.set(usuario.email,usuario);
                $location.path('/listarTreinadores');
            }).catch(console.log);
       
    };


}