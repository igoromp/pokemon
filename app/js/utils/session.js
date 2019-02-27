angular.module(APP_NAME)
        .factory('Session',Session);

function Session(){

    var _get = function(key){
        return sessionStorage.getItem(key);
    };

    var _set = function(key,value){
         sessionStorage.setItem(key,JSON.stringify(value));
    };

    var _destroy = function(){
        sessionStorage.clear();
    };

    var _length = function(){
        return sessionStorage.length;
    };


    return {
        get : _get,
        set:_set,
        destroy:_destroy,
        length:_length
    };
}

