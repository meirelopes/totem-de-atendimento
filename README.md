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
- Caso a pessoa tente inserir alguma informação do tipo String, o sistema deve retornar uma mensagem: “Formato inválido, para escolher o item, você deve informar o número dele”. 
- Quando a pessoa selecionar o lanche que quer comprar, o sistema deve perguntar a quantidade do lanche solicitado que a pessoa quer comprar,
após o usuário selecionar o lanche e a quantidade, o carrinho de compra deve adicionar o código, quantidade, nome e valor do lanche e
mostrar o valor total do pedido até aquele momento. Sendo que os valores dos lanches são: 
     1. X-burger - R$ 10,00
     2. X-salada - R$ 12,00 
- Quando digitar 2, ou seja, o item Bebida, deve aparecer as opções:
     1. Refrigerante 
     2. Suco 
- Caso a pessoa tente escolher algum item fora das opções acima, o sistema deve mostrar a mensagem “Opção inválida, tente novamente” e mostrar novamente o menu inicial da bebida. 
- Caso a pessoa tente inserir alguma informação do tipo String, o sistema deve retornar uma mensagem: “Formato inválido, para escolher o item, você deve informar o número dele”. 
- Quando a pessoa selecionar a bebida que quer comprar, o sistema deve perguntar a quantidade de bebida que a pessoa quer comprar, após o usuário selecionar a bebida e a quantidade,
o carrinho de compra deve adicionar o código, a quantidade, nome e valor da bebida e mostrar o valor total do pedido até aquele momento. Sendo que os valores das bebidas são:
     1. Refrigerante - R$ 8,00
     2. Suco - R$ 6,00
- Após a pessoa informar o lanche ou bebida que quer comprar e ver o valor total do pedido até aquele momento, o sistema deve
perguntar se a pessoa deseja incluir mais itens, editar um item, remover item ou se deseja finalizar o pedido. 
- Se ela desejar comprar mais itens, o sistema deve voltar para o menu inicial. 
- Se ela desejar editar um item, o sistema deve solicitar o código do produto que deseja editar. Após receber um código válido e encontrar o produto,
o sistema deverá perguntar qual a nova quantidade de itens que o usuário deseja adicionar, após o usuário atualizar o valor da quantidade,
o sistema deve atualizar o valor total da compra e exibir novamente o carrinho atualizado. 
- Se ela desejar remover itens, o sistema deve solicitar o código do produto válido que deseja remover (o código é um número que deverá ser
adicionado ao produto automaticamente quando for adicionado ao carrinho), após ser feita a remoção do produto a partir do código, o sistema deve exibir o carrinho de compras atualizado. 
- Se ela desejar finalizar o pedido, o sistema deve mostrar o valor total do pedido e quais os itens que ela selecionou e perguntar qual a forma de pagamento,
sendo que o sistema deve aceitar cartão de crédito, cartão de débito, vale refeição e dinheiro. 
     1. Após a pessoa selecionar uma das opções: cartão de crédito, cartão de débito ou vale refeição, o sistema mostra a seguinte mensagem: "Compra finalizada com sucesso! Boa refeição”.
     2. Caso a pessoa selecione dinheiro, o sistema deve pedir qual o valor em dinheiro que o usuário usará para pagar, caso seja um valor
mais alto que o valor total da compra, o sistema deverá exibir o troco que o usuário deverá receber. 
     3. Caso a pessoa tente escolher alguma coisa fora das opções acima, o sistema deve mostrar a mensagem
“Opção inválida, tente novamente” e mostrar novamente as opções de cartão de crédito, cartão de débito, vale refeição e dinheiro.
- Testes unitários com pelo menos 90% de cobertura.

## Sugestões

Se você encontrar algum problema ou tiver sugestões de melhorias, não hesite em me contactar por meio do e-mail jucemeirelopes@gmail.com.

## Autora

<table>
  <tr>
    <td align="center"><a href="https://github.com/meirelopes"><img src="https://github.com/meirelopes/alura-git/assets/105396487/e5fd7acb-f3d7-4283-8f85-9b942e8ec074" width="100px;" alt=""/><br/><strong>Jucemeire Lopes</strong></a><br/><a href="https://www.linkedin.com/in/jucemeirelopes/">LinkedIn</a></td>
       
  </tr>
</table>

