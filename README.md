# xy-inc
Aplicação feita para acessar a base dos correios (www.buscacep.com.br) e de acordo com o cep ou logradouro informado, trazer a relação de endereços.
Foi utilizado o servidor web tomcat7 para fazer os testes no client, é possível utilizar outro servidor desde que seja configurado as dependencias no Maven.

Como executar a aplicação:

1) Inicie o servidor

2) Para buscar os endereços passando o cep, faremos uma chamada na URL http://localhost:8080/search-address/rest/address/getByCep/{cep}, retirar as chaves e no lugar de cep colocar um cep sem pontuação e/ou caracteres especiais.

3)Para buscar os endereços passando o cep, faremos uma chamada na URL http://localhost:8080/search-address/rest/address/getByAddress/{address}, retirar as chaves e no lugar de address colocar um endereço, poderá ser usado espaço entre os endereços.


