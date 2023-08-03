package view;


import model.ItemProduto;
import service.CarrinhoService;
import service.ItemProdutoService;

import java.util.InputMismatchException;
import java.util.Scanner;

/*

6. Quando a pessoa selecionar o lanche que quer comprar, o sistema deve perguntar a quantidade do lanche solicitado
que a pessoa quer comprar, após o usuário selecionar o lanche e a quantidade, o carrinho de compra deve adicionar o código,
quantidade, nome e valor do lanche e mostrar o valor total do pedido até aquele momento. Sendo que os valores dos lanches são:
a. X-burger - R$ 10,00
b. X-salada - R$ 12,00
8. Quando a pessoa selecionar a bebida que quer comprar, o sistema deve perguntar a quantidade de bebida que a pessoa quer comprar, após
o usuário selecionar a bebida e a quantidade, o carrinho de compra deve adicionar o código, a quantidade, nome e valor da bebida e mostrar
o valor total do pedido até aquele momento. Sendo que os valores das bebidas são:
Desafio - Kotlin
a. Refrigerantes - R$ 8,00
b. Sucos - R$ 6,00

9. Após a pessoa informar o lanche ou bebida que quer comprar e ver o valor total do pedido até aquele momento, o sistema deve perguntar
se a pessoa deseja incluir mais itens, editar um item, remover item ou se deseja finalizar o pedido.


a. Se ela desejar comprar mais itens, o sistema deve voltar para o menu inicial.
b. Se ela desejar editar um item, o sistema deve solicitar o código do produto que deseja editar. Após receber um código válido e
encontrar o produto, o sistema deverá perguntar qual a nova quantidade de itens que o usuário deseja adicionar, após o usuário atualizar o
valor da quantidade, o sistema deve atualizar o valor total da compra e exibir novamente o carrinho atualizado.
c. Se ela desejar remover itens, o sistema deve solicitar o código do produto válido que deseja remover (o código é um número que deverá ser
adicionado ao produto automaticamente quando for adicionado ao carrinho), após ser feita a remoção do produto a partir do código, o sistema
deve exibir o carrinho de compras atualizado.
d. Se ela desejar finalizar o pedido, o sistema deve mostrar o valor total do pedido e quais os itens que ela selecionou e perguntar qual a
forma de pagamento, sendo que o sistema deve aceitar cartão de crédito, cartão de débito, vale refeição e dinheiro.
i. Após a pessoa selecionar uma das opções: cartão de crédito, cartão de débito ou vale refeição, o sistema mostra a seguinte mensagem:
 "Compra finalizada com sucesso! Boa refeição”.
ii. Caso a pessoa selecione dinheiro, o sistema deve pedir qual o valor em dinheiro que o usuário usará para pagar,
caso seja um valor mais alto que o valor total da compra, o sistema deverá exibir o troco que o usuário deverá receber.
iii. Caso a pessoa tente escolher alguma coisa fora das opções acima, o sistema deve mostrar a mensagem “Opção inválida, tente novamente”
e mostrar novamente as opções de cartão de crédito, cartão de débito, vale refeição e dinheiro.

10. Testes unitários com pelo menos 90% de cobertura.

 */
public class Menu {
    Scanner scanner;

    CarrinhoService carrinhoService;
    ItemProdutoService itemProdutoService;

    public Menu(Scanner scanner, CarrinhoService carrinhoService, ItemProdutoService itemProdutoService) {

        this.scanner = scanner;
        this.carrinhoService = carrinhoService;
        this.itemProdutoService = itemProdutoService;

    }

    // Método pede ao usuário escolher o que deseja comprar: lanche ou bebida - testado
    public Integer menuInicial() {

        int escolha = 0;

        boolean entradaValida = false;

        do {

            try {

                System.out.println("Escolha a opção que deseja comprar:");
                System.out.println("1 - Lanche");
                System.out.println("2 - Bebida");
                System.out.print("Escolha: ");
                escolha = scanner.nextInt();

                if (escolha >= 1 && escolha <= 2) {

                    entradaValida = true;

                } else {

                    System.out.println("Opção inválida, tente novamente");

                }

            } catch (InputMismatchException e) {

                System.out.println("Formato inválido, para escolher o item, " +
                        "você deve informar o número dele");

                scanner.nextLine();

            }

        } while (!entradaValida);

        return escolha;


    }


    // Método recebe a escolha do usuário, se for lanche, pede que escolha entre X-burguer ou x-salada. Se for bebida
    // pede que escolha entre Refrigerante ou suco.
    // Pede ao usuário a quantidade, cria um item e retorna esse item - testado
    public ItemProduto escolhaItemProduto() {
        Integer escolha = menuInicial();
        ItemProduto itemProduto = null;
        int quantidade;

        switch (escolha) {

            case 1:

                String lanche = menuLanche();
                quantidade = informaQuantidade();
                itemProduto = itemProdutoService.criarItemProduto(lanche, quantidade);
                break;

            case 2:

                String bebida = menuBebida();
                quantidade = informaQuantidade();
                itemProduto = itemProdutoService.criarItemProduto(bebida, quantidade);
                break;

        }

        return itemProduto;
    }

    // Método que pede ao usuário que escolha o lanche - testado
    public String menuLanche() {

        int escolha = 0;

        String produto = null;

        boolean entradaValida = false;

        do {

            try {

                System.out.println("1 - X-burger");
                System.out.println("2 - X-salada");
                System.out.print("Escolha: ");
                escolha = scanner.nextInt();

                if (escolha >= 1 && escolha <= 2) {

                    entradaValida = true;

                } else {

                    System.out.println("Opção inválida, tente novamente");

                }

            } catch (InputMismatchException e) {

                System.out.println("Formato inválido, para escolher o item,\n" +
                        "você deve informar o número dele");

                scanner.nextLine();

            }

        } while (!entradaValida);

        if (escolha == 1) {

            produto = "X-burger";

        } else {

            produto = "X-salada";

        }
        scanner.nextLine();

        return produto;
    }


    // Método que pede ao usuário que escolha a bebida - testado

    public String menuBebida() {

        int escolha = 0;

        String produto = null;

        boolean entradaValida = false;

        do {

            try {

                System.out.println("1 - Refrigerante");
                System.out.println("2 - Suco");
                System.out.print("Escolha: ");
                escolha = scanner.nextInt();

                if (escolha >= 1 && escolha <= 2) {

                    entradaValida = true;

                } else {

                    System.out.println("Opção inválida, tente novamente");

                }

            } catch (InputMismatchException e) {

                System.out.println("Formato inválido, para escolher o item,\n" +
                        "você deve informar o número dele");

                scanner.nextLine();

            }

        } while (!entradaValida);

        if (escolha == 1) {

            produto = "Refrigerante";

        } else {

            produto = "Suco";

        }
        scanner.nextLine();

        return produto;


    }

    public void escolherOpcoes() {

        int escolha = 0;

        boolean entradaValida = false;

        do {

            try {

                System.out.println("Escolha uma opção para continuar:");
                System.out.println("1 - Incluir mais itens");
                System.out.println("2 - Editar um item");
                System.out.println("3 - Remover item");
                System.out.println("4 - Finalizar o pedido");
                System.out.print("Escolha: ");
                escolha = scanner.nextInt();

                if (escolha >= 1 && escolha <= 4) {

                    entradaValida = true;

                } else {

                    System.out.println("Opção inválida, tente novamente");

                }

            } catch (InputMismatchException e) {

                System.out.println("Formato inválido. Você deve informar um número de 1 a 4");

                scanner.nextLine();

            }

        } while (!entradaValida);

        acionaMetodo(escolha);

    }

    // Método chama o método de acordo com a escolha do usuário
    public void acionaMetodo(int escolha) {

        switch (escolha) {

            case 1:

                menuInicial();
                break;

            case 2:

                break;

            case 3:

                break;

            case 4:

                break;

        }

    }

    public void qualFormaPagamento() {

        int escolha = 0;

        boolean entradaValida = false;

        do {

            try {

                System.out.println("Escolha uma forma de pagamento:");
                System.out.println("1 - Cartão de crédito");
                System.out.println("2 - Cartão de débito");
                System.out.println("3 - Vale refeição");
                System.out.println("4 - Dinheiro");
                System.out.print("Escolha: ");
                escolha = scanner.nextInt();

                if (escolha >= 1 && escolha <= 4) {

                    entradaValida = true;

                } else {

                    System.out.println("Opção inválida, tente novamente");

                }

            } catch (InputMismatchException e) {

                System.out.println("Formato inválido. Você deve informar um número de 1 a 4");

                scanner.nextLine();

            }

        } while (!entradaValida);

    }

    public String boaRefeicao() {

        return "Compra finalizada com sucesso! Boa refeição";

    }

    // Método que pede ao usuário a quantidade do ítem escolhido e retorna essa quantidade - testado
    public Integer informaQuantidade() {

        boolean entradaValida = false;

        int quantidade = 0;

        do {

            try {

                System.out.println("Qual quantidade do ítem deseja adicionar no carrinho?");

                quantidade = scanner.nextInt();

                if (quantidade > 0) {

                    entradaValida = true;

                } else {

                    System.out.println("Quantidade não pode ser 0 ou número negativo.");
                    entradaValida = false;

                }

            } catch (InputMismatchException e) {

                System.out.println("Formato inválido. Digite um número inteiro para a quantidade.");
                scanner.nextLine();


            }

        } while (!entradaValida);

        return quantidade;

    }


    public void adicionarItem() {

        ItemProduto itemProduto = escolhaItemProduto();
        carrinhoService.adicionarItem(itemProduto);

    }


}
