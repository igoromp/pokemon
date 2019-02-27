angular.module(APP_NAME)
    .factory('API',API);

   
    function API(){
        
        var URL = 'http://pokemon.bb.com.br';
        var CONTEXT = {
            REST:URL+'/pokemon/rest',
            DEFAULT:URL+'/pokemon'
        };

        var API ={
            HAS_LOGGED:CONTEXT.REST+'/login/verify-login',
            LOGIN:CONTEXT.REST+'/login',
            LOGIN_OBTER_TREINADOR:this.LOGIN+'/obter',
            BATALHAR: CONTEXT.REST+'/batalhar',
            TREINAR:CONTEXT.REST+'/treinar',
            TREINADOR:CONTEXT.REST+'/treinador',
            TREINADOR_EXCLUIR_POKEMON:CONTEXT.REST+'/treinador/pokemons/',
            TREINADOR_LISTA_POKEMONS:CONTEXT.REST+'/treinador/pokemonsTreinador/',
            LISTA_POKEMONS:CONTEXT.REST+'/pokemon'
         };


        return API;
    }




