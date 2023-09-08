# totem-de-atendimento

Esta é uma API de treinamento 

<p align="center">
     <a alt="Java">
        <img src="https://img.shields.io/badge/Java-v11-blue.svg" />
    </a>
    <a alt="PostgreSQL">
        <img src="https://img.shields.io/badge/PostgreSQL-v42.6.0-blue.svg" />
    </a>
   <a></a>
          <img src="https://img.shields.io/badge/hibernate-5.5.7-blue"/>

     
</p>

- [Principais Tecnologias](#principais-tecnologias)
- [Detalhes do Projeto](#detalhes-do-projeto)
- [Sugestões](#sugestões)
- [Autora](#autora)

## Principais Tecnologias:

- **Java 11**

- **Hibernate** 
  
- **Postgres**

## Detalhes do Projeto:

O projeto é um sistema para totem de auto-atendimento que deve atender aos seguintes requisitos:

- A tela inicial deve ter um menu onde a pessoa deve selecionar se ela quer comprar: 
     1. Lanche 
     2. Bebida
- Caso a pessoa tente escolher algum item fora das opções acima, o sistema deve mostrar a mensagem “Opção inválida, tente novamente” e mostrar novamente o menu inicial. 
- O sistema deve aceitar apenas o número da opção, ou seja, se a pessoa quiser um lanche ela deve inserir 1 e caso queira uma bebida ela deve digitar 2. 
- Caso a pessoa tente inserir alguma informação do tipo String o sistema deve retornar uma mensagem: “Formato inválido, para escolher o item, você deve informar o número dele”. 
- Quando digitar 1, ou seja, o item Lanche, deve aparecer as opções:
     1. X-burger 
     2. X-salada
- Caso a pessoa tente escolher algum item fora das opções acima, o sistema deve mostrar a mensagem "Opção inválida, tente novamente" e mostrar novamente o menu inicial do lanche.

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

