package service;

import model.Carrinho;
import model.ItemProduto;
import repository.CarrinhoRepository;
import repository.ProdutoRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CarrinhoService {
    private CarrinhoRepository carrinhoRepository;

    private ProdutoRepository produtoRepository;

    public Carrinho criarCarrinho() {

        Carrinho carrinho = new Carrinho();

        carrinho.setValorTotal(BigDecimal.ZERO);

        carrinho.setFechado(false);

        carrinho.setItens(new ArrayList<>());

        // Salvar o carrinho no banco de dados

        return carrinho;
    }

    public void adicionarItem(ItemProduto item, int quantidade) {

        Carrinho carrinho = criarCarrinho();

        List < ItemProduto > itens = carrinho.getItens();

        if (itens == null) {

            itens = new ArrayList<>();

        }

        item.setQuantidade(quantidade);
        itens.add(item);
        atualizarValorTotalCarrinho(carrinho);

    }


    private void atualizarValorTotalCarrinho(Carrinho carrinho) {

        List<ItemProduto> itens = carrinho.getItens();

        BigDecimal valorTotal = BigDecimal.ZERO;

        for (ItemProduto item : itens) {

            BigDecimal subtotal = item.getProduto().getValorUnitario()
                    .multiply(BigDecimal.valueOf(item.getQuantidade()));

            valorTotal = valorTotal.add(subtotal);


            // atualizar no banco de dados

        }

    }

    private Optional<Carrinho> buscarPorId(Long id) {

        return carrinhoRepository.buscarCarrinhoPorId(id);

    }

    private Long buscarIdPorNome(String nomeProduto) {

        return produtoRepository.buscarIdPorNome(nomeProduto);

    }


}
