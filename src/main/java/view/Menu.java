package view;


import model.Carrinho;
import model.ItemProduto;
import service.CarrinhoService;
import service.ItemProdutoService;

import javax.persistence.criteria.CriteriaBuilder;
import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

/*
10. Testes unitários com pelo menos 90% de cobertura.
 */
public class Menu {
    Scanner scanner;

    Carrinho carrinhoAtual;

    CarrinhoService carrinhoService;
    ItemProdutoService itemProdutoService;

    public Menu(Scanner scanner, CarrinhoService carrinhoService, ItemProdutoService itemProdutoService, Carrinho carrinhoAtual) {

        this.scanner = scanner;
        this.carrinhoService = carrinhoService;
        this.itemProdutoService = itemProdutoService;
        this.carrinhoAtual = carrinhoAtual;

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

            System.out.println(boaRefeicao());

        } else if (escolha == 4) {

            BigDecimal troco = carrinhoService.devolverTroco(informaNotaParaPagamento(), carrinhoAtual.getId());

            int comparacao = troco.compareTo(BigDecimal.ZERO);

            if (comparacao < 0) {

                // ARRUMAR BUG SE FOR VALOR DA NOTA MENOR TEM QUE PEDIR QUE INSIRA OUTRA FORMA DE PAGAMENTO
                System.out.println("Valor insuficiente para pagamento.");

            } else {

                System.out.println("Troco de R$ " + troco);
                System.out.println(boaRefeicao());

            }

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
                scanner.next();
                valorValido = false;
            }
        } while (!valorValido);

        return nota;
    }


    public String boaRefeicao() {

        return "Compra finalizada com sucesso! Boa refeição!";

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

        carrinhoAtual = escolherCarrinho();
        ItemProduto itemProduto = escolhaItemProduto();

        if (verificarItemExistente(carrinhoAtual, itemProduto)) {

            desejaAtualizarQuantidade(itemProduto);

        } else {

            return carrinhoService.adicionarItem(itemProduto, carrinhoAtual);

        }
        return null;
    }


    // Método que pede ao usuário que escolha a bebida - testado

    public void desejaAtualizarQuantidade(ItemProduto itemProduto) {

        System.out.println("O produto já está no carrinho. Deseja atualizar a quantidade?" +
                " (Digite 1 para sim ou 2 para não)");

        int escolha = 0;

        boolean entradaValida = false;

        do {

            try {

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

                System.out.println("Formato inválido, para escolher o item,\n" +
                        "você deve informar o número dele");

                scanner.nextLine();

            }

        } while (!entradaValida);

        if (escolha == 1) {

            int quantidade = informaQuantidade();

            carrinhoService.atualizarItemPeloCodigoProduto(itemProduto.getProduto().getId(), quantidade, carrinhoAtual.getId());
            mostrarCarrinho(carrinhoAtual);
            escolherOpcoes();

        } else {

            mostrarCarrinho(carrinhoAtual);
            escolherOpcoes();

        }

    }


    private boolean verificarItemExistente(Carrinho carrinho, ItemProduto itemProduto) {
        for (ItemProduto item : carrinho.getItens()) {
            if (item.getProduto().getId().equals(itemProduto.getProduto().getId())) {
                return true;
            }
        }
        return false;
    }


    public void incluirItens() {

        adicionarItem();
        mostrarCarrinho(carrinhoAtual);
        escolherOpcoes();

    }

    // Método que inicia o sistema
    public void iniciarSistema() {

        carrinhoAtual = carrinhoService.criarCarrinho();
        ItemProduto itemProduto = escolhaItemProduto();
        carrinhoService.adicionarItem(itemProduto, carrinhoAtual);
        mostrarCarrinho(carrinhoAtual);
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

                incluirItens();

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

        Long produtoId = informaCodigoProduto();

        if (!carrinhoService.verificarProdutoNoCarrinho(produtoId, carrinhoAtual)) {

            System.out.println("Não exista produto com o código " + produtoId + " no carrinho.");
            escolherOpcoes();
        } else {

            int quantidade = informaQuantidade();

            carrinhoService.atualizarItemPeloCodigoProduto(produtoId, quantidade, carrinhoAtual.getId());

            mostrarCarrinho(carrinhoAtual);

            escolherOpcoes();
        }
    }

    public Long informaCodigoProduto() {

        boolean eValido = false;
        Long produtoId = 0L;


        do {
            try {

                System.out.println("Informe o código do produto que gostaria de alterar:");
                produtoId = scanner.nextLong();

                if (produtoId > 0) {

                    eValido = true;

                } else {

                    System.out.println("Código do produto deve ser um número positivo diferente de zero");
                    eValido = false;

                }

            } catch (InputMismatchException e) {

                System.out.println("Formato inválido. O código do produto deve ser um número inteiro.");
                eValido = false;
                scanner.nextLine();

            }

        } while (!eValido);

        return produtoId;
    }


    //NÚMERO 1
    // Método pergunta ao usuário se já possui um carrinho aberto ou não
    // Se sim irá pedir o código do carrinho e buscará no banco de dados
    // Se não irá criar um carrinho - testado
    public Carrinho escolherCarrinho() {

        int escolha = 0;

        boolean entradaValida = false;

        do {

            try {

                System.out.println("Você já possui um carrinho aberto. Deseja continuar usando o mesmo carrinho?");
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

        if (escolha == 2) {

            System.out.println("Novo carrinho aberto!");
            return carrinhoService.criarCarrinho();


        } else if (escolha == 1) {


            return carrinhoAtual;

        } else {

            return null;

        }

    }


    public void excluirItem() {

        Long produtoId = informaCodigoProduto();

        if (!carrinhoService.verificarProdutoNoCarrinho(produtoId, carrinhoAtual)) {

            System.out.println("Não exista produto com o código " + produtoId + " no carrinho.");
            escolherOpcoes();

        } else {

            carrinhoService.excluirItem(produtoId, carrinhoAtual.getId());
            mostrarCarrinho(carrinhoAtual);
            escolherOpcoes();
        }
    }

}
