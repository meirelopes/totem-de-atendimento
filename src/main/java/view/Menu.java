package view;


import model.Carrinho;
import model.ItemProduto;
import service.CarrinhoService;
import service.ItemProdutoService;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

/*
c. Se ela desejar remover itens, o sistema deve solicitar o código do produto válido que deseja remover (o código é um número que deverá ser
adicionado ao produto automaticamente quando for adicionado ao carrinho), após ser feita a remoção do produto a partir do código, o sistema
deve exibir o carrinho de compras atualizado.
d. Se ela desejar finalizar o pedido, o sistema deve mostrar o valor total do pedido e quais os itens que ela selecionou e perguntar qual a
forma de pagamento, sendo que o sistema deve aceitar cartão de crédito, cartão de débito, vale refeição e dinheiro.

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

        acionaMetodoQualFormaDePagamento(escolha);

    }

    // Método chama o método de acordo com a escolha do usuário
    public void acionaMetodoQualFormaDePagamento(int escolha) {

        if (escolha > 0 && escolha < 4) {

            boaRefeicao();

        } else if (escolha == 4) {

            System.out.println("Informe o código do carrinho:");
            Long id = scanner.nextLong();
            BigDecimal troco = carrinhoService.devolverTroco(informaNotaParaPagamento(), id);
            System.out.println("Troco de R$ " + troco);
            boaRefeicao();
        }

    }

    public BigDecimal informaNotaParaPagamento() {

        BigDecimal nota = BigDecimal.ZERO;
        boolean valorValido;

        do {
            try {
                System.out.println("Informe a nota que irá realizar o pagamento:");
                nota = scanner.nextBigDecimal();
                valorValido = nota.compareTo(BigDecimal.ZERO) > 0;
                if (!valorValido) {
                    System.out.println("Valor inválido. A nota deve ser maior que zero.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Formato inválido. Informe um número válido.");
                scanner.next(); // Limpar o buffer de entrada para evitar loop infinito
                valorValido = false;
            }
        } while (!valorValido);

        return nota;
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

    // Método adiciona um ítem no carrinho e retorna esse carrinho -testado
    public Carrinho adicionarItem() {

        // escolher entre carrinho novo ou antigo
        Carrinho carrinho = escolherCarrinho();

        ItemProduto itemProduto = escolhaItemProduto();
        return carrinhoService.adicionarItem(itemProduto, carrinho);

    }
// Método que inicia o sistema
    public void iniciarSistema() {
        Carrinho carrinho = adicionarItem();
        mostrarCarrinho(carrinho);
        escolherOpcoes();

    }

    // Método mostra na tela o que foi comprado e o valor total até o momento - testado
    public void mostrarCarrinho(Carrinho carrinho) {

        System.out.println("CÓDIGO DO CARRINHO: " + carrinho.getId());
        System.out.println("ÍTENS ADICIONADO NO CARRINHO:");
        for (ItemProduto item : carrinho.getItens()) {
            System.out.println("Código do produto: " + item.getProduto().getId() + " - " + item.getProduto().getNome()
                    + "| Valor unitário: R$ " + item.getProduto().getValorUnitario()
                    + "| Quantidade: " + item.getQuantidade());
        }
        System.out.println("Valor total: R$ " + carrinho.getValorTotal());
    }


    // Método oferece opções de interação com o usuário
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

                iniciarSistema();

                break;

            case 2:

                atualizarItem();

                break;

            case 3:

                excluirItem();

                break;

            case 4:

                qualFormaPagamento();

                break;

        }

    }

    // Método atualiza a quantidade de um item
    public void atualizarItem() {

        System.out.println("Informe o código do carrinho:");
        Long carrinhoId = scanner.nextLong();

        System.out.println("Informe o código do produto que gostaria de alterar:");
        Long produtoId = scanner.nextLong();

        int quantidade = informaQuantidade();

        carrinhoService.atualizarItemPeloCodigoProduto(produtoId, quantidade, carrinhoId);
        Carrinho carrinho = carrinhoService.buscarCarrinho(carrinhoId);
        mostrarCarrinho(carrinho);
        escolherOpcoes();
    }

//NÚMERO 1
    // Método pergunta ao usuário se já possui um carrinho aberto ou não
    // Se sim irá pedir o código do carrinho e buscará no banco de dados
    // Se não irá criar um carrinho - testado
    public Carrinho escolherCarrinho() {

        Carrinho carrinho = null;

        int escolha = 0;

        boolean entradaValida = false;

        do {

            try {

                System.out.println("Já possui um carrinho aberto?");
                System.out.println("1 - Sim");
                System.out.println("2 - Não");
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

        if (escolha == 1) {

            // tratar exceção para formato inválido
            System.out.println("Informe o código do carrinho:");

            Long codigo = scanner.nextLong();

            carrinho = carrinhoService.buscarCarrinho(codigo);

        } else if (escolha == 2) {

            carrinho = carrinhoService.criarCarrinho();

        }

        return carrinho;

    }

    public void excluirItem() {

        System.out.println("Informe o código do carrinho:");
        Long carrinhoId = scanner.nextLong();

        System.out.println("Informe o código o produto que deseja excluir:");
        Long produtoId = scanner.nextLong();

        carrinhoService.excluirItem(produtoId, carrinhoId);
        Carrinho carrinho = carrinhoService.buscarCarrinho(carrinhoId);
        mostrarCarrinho(carrinho);
        escolherOpcoes();

    }

}
