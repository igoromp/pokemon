
## Seguir os passos abaixo para configuração de proxy

* ### sudo gedit /etc/hosts
```
127.0.0.1 pokemon.bb.com.br
```

* ### sudo gedit /etc/apache2/sites-enabled/000-default.conf

|                |                               |                             |
|----------------|-------------------------------|-----------------------------|
|ProxyPass       |/pokemon/rest|http://pokemon.bb.com.br:8080/pokemon/rest     |
|ProxyPassReverse|/pokemon/rest|http://pokemon.bb.com.br:8080/pokemon/rest     |
|ProxyPass       |/rest        |http://pokemon.bb.com.br:8080/rest             |
|ProxyPassReverse|/rest        |http://pokemon.bb.com.br:8080/rest             |
|ProxyPass       |/            |http://pokemon.bb.com.br:8000/                 |
|ProxyPassReverse|/            |http://pokemon.bb.com.br:8000/                 |

* ### service apache2 restart
```
(Lado direito do mouse no projeto) => Maven/Update Project/ (Selecionar Force Update) => Ok
```

* ### Para testar
```
http://pokemon.bb.com.br/pokemon/rest/pokemon
```
