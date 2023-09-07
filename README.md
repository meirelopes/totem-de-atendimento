# totem-de-atendimento

Esta é uma API de treinamento desenvolvida para a gestão de estoque e que tem como objetivo fornecer uma solução simples e funcional para a administração de produtos em estoque.

<p align="center">
     <a alt="Java">
        <img src="https://img.shields.io/badge/Java-v11-blue.svg" />
    </a>
    <a alt="Spring Boot">
        <img src="https://img.shields.io/badge/Spring%20Boot-v2.7.15-brightgreen.svg" />
    </a>
    <a alt="PostgreSQL">
        <img src="https://img.shields.io/badge/PostgreSQL-v42.6.0-blue.svg" />
    </a>
</p>

- [Principais Tecnologias](#principais-tecnologias)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Detalhes do Projeto](#detalhes-do-projeto)
- [Documentação](#documentação)
- [Postman](#postman)
- [Sugestões](#sugestões)
- [Autora](#autora)

## Principais Tecnologias:

- **Java 11**

- **Spring Boot 2.7.15**: Spring Boot, reponsável por aumentar a produtividade do desenvolvedor com sua configuração automática disponível em  [Spring Initializr](https://start.spring.io/)
  
- **Spring Data JPA**: Abstração da camada de acesso ao banco de dados, reduzindo o código padrão.
  
- **OpenAPI (Swagger)**: Integrado com o OpenAPI 3, possibilitando uma documentação de API perfeitamente clara para uma compreensão aprimorada e facilitando os testes.
  
- **Flyway**: realiza a migração de banco de dados, automatizando e simplificando o processo de versionamento.  Mantém a atualização de esquemas de banco de dados, garantindo a integridade e a consistência dos dados ao longo do tempo.
  
- **ModelMapper**: Simplifica a transferência de dados entre diferentes modelos de objetos, tornando a tarefa de conversão de dados mais eficiente e intuitiva.
  

## Estrutura do Projeto:

O projeto está estruturado em diversos conjuntos de pacotes, cada um com uma finalidade específica:

### Pacote API

Esse pacote é responsável por tratar da camada de controle da aplicação, como a interação com o consumnidor da API, processar solicitações HTTP e coordenar a execução das operações do sistema. Contém os seguintes sub pacotes:

- `controller`: este pacote contém nossos controladores Rest. Aqui, expomos nossos endpoints, seguindo o estilo arquitetural REST. DTOs são utilizados nesta camada para definir as interfaces de consumo de forma adequada, evitando a exposição de atributos desnecessários do modelo.

- `assembler`: desempenha um papel crucial na transformação de objetos de modelo em DTOs (Data Transfer Objects). 
  
- `disassembler`: responsável pela transformação dos DTOs em objetos de modelo.

- `dto`: : neste pacote, são definidos os Data Transfer Objects (DTOs), que servem como estruturas de dados simples para a transferência de informações entre diferentes partes da aplicação

- `exceptionhandler`: um manipulador de exceções global para lidar com as exceções do Spring.
  
- `openapi.controller`: este pacote é responsável por abrigar as interfaces de controle que incorporam a anotação OpenAPI. Aqui, definimos as operações da API, especificando detalhadamente como os endpoints devem se comportar e quais parâmetros devem ser aceitos.
  
### Pacote Core

O pacote core é um componente fundamental da aplicação, onde são mantidas as classes de configuração que afetam o comportamento e o funcionamento geral do sistema.

- `modelmapper`: contém as configurações da biblioteca modelmapper.
  
- `openapi`: contém as configurações da biblioteca openapi.

### Pacote Domain

- `exception`: define as exceções personalizadas.
  
- `model`: onde nossas entidades são definidas, usando anotações JPA (Jacarta) para ORM com nosso banco de dados SQL.
  
- `repository`: neste pacote, tratamos do acesso a dados usando interfaces fornecidas pelo Spring Data JPA.
  
- `service`: onde reside nossa lógica de negócios. Aqui validamos nossos dados, tratamos de exceções de negócios e gerenciamos o acesso ao nosso banco de dados SQL por meio de repositórios.
  
- `Application.java`: esta é a classe principal para executar nosso projeto. Ele inicializa nosso aplicativo Spring e conecta todos os componentes.

### Pacote test

- `apitest`: contém as classes responsáveis pelos testes de API. É utilizado o banco de dados PostgreSQL para a execução dos testes.
  
- `integracaotest`: contém as classes responsáveis pelos testes de integração.  É utilizado o banco de dados PostgreSQL para a execução dos testes.

- `mockmvc`: contém as classes de testes utilizando o @WebMvcTest para testar a classe controller.

- `util`: Contém as classes utilitárias, que são projetadas para fornecer funcionalidades comuns ou auxiliares que podem ser usadas em toda a aplicação. 

  
## Detalhes do Projeto:

O projeto inclui operações básicas de CRUD seguindo o uso de códigos de status HTTP apropriados. O tratamento de exceções é gerenciado globalmente com um `@ControllerAdvice` para garantir o tratamento consistente de exceções em todo o aplicativo.

O projeto utiliza um banco de dados postgreSQL para ambiente de desenvolvimento com a utilização de um arquivo afterMigration.sql que limpa os dados da tabela após a inicialização da aplicação e outro banco de dados PostgreSQL para fins de teste em ambiente de teste, com uma classe que exclui a tabela do banco de dados e outra casse responsável para popular a tabela com dados.

## Documentação

A documentação da API foi descrita obedecendo a especificação OpenAPI e pode ser encontrada na UI do Swagger. Para visualizá-lo, visite: Swagger UI.: [Swagger UI](http://localhost:8080/swagger-ui.html).

## Postman

Foi utilizado o Postman para  testar as solicitações HTTP, como GET, POST, PUT, DELETE e outros. Acessar em:  [Postman](https://www.postman.com/).
O arquivo da Collection gerenciamento-de-estoque pode ser acessado por meio do link: [gerenciamento-de-estoque](https://github.com/meirelopes/estoque-de-produtos/blob/main/estoque/gerenciamento-de-estoque.postman_collection.json)

## Sugestões

Se você encontrar algum problema ou tiver sugestões de melhorias, não hesite em me contactar por meio do e-mail jucemeirelopes@gmail.com.

## Autora

<table>
  <tr>
    <td align="center"><a href="https://github.com/meirelopes"><img src="https://github.com/meirelopes/alura-git/assets/105396487/e5fd7acb-f3d7-4283-8f85-9b942e8ec074" width="100px;" alt=""/><br/><strong>Jucemeire Lopes</strong></a><br/><a href="https://www.linkedin.com/in/jucemeirelopes/">LinkedIn</a></td>
       
  </tr>
</table>

