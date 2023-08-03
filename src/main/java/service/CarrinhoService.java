package service;

import model.Carrinho;
import model.ItemProduto;
import repository.CarrinhoRepository;
import repository.ProdutoRepository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CarrinhoService {
    private CarrinhoRepository carrinhoRepository;

    private ProdutoRepository produtoRepository;

    public CarrinhoService(CarrinhoRepository carrinhoRepository, ProdutoRepository produtoRepository) {

        this.carrinhoRepository = carrinhoRepository;
        this.produtoRepository = produtoRepository;

    }

    public Carrinho criarCarrinho() {

        Carrinho carrinho = new Carrinho();

        carrinho.setValorTotal(BigDecimal.ZERO);

        carrinho.setFechado(false);

        carrinho.setItens(new ArrayList<>());

        // Salvar o carrinho no banco de dados

        return carrinho;
    }

    @Transactional
    public void adicionarItem(ItemProduto item) {

        Carrinho carrinho = criarCarrinho();

        List<ItemProduto> itens = carrinho.getItens();

        if (itens == null) {

            itens = new ArrayList<>();

        }

        item.setId(item.getId());
        item.setQuantidade(item.getQuantidade());
        item.setProduto(item.getProduto());
        itens.add(item);
        atualizarValorTotalCarrinho(carrinho);
        carrinhoRepository.salvarCarrinho(carrinho);

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

    public Carrinho buscarCarrinho(Long id) {
        if (id != null) {
            Optional<Carrinho> optionalCarrinho = carrinhoRepository.buscarCarrinhoPorId(id);
            if (optionalCarrinho.isPresent()) {
                return optionalCarrinho.get();
            } else {
                throw new RuntimeException("Carrinho não encontrado");
            }
        } else {
            throw new IllegalArgumentException("ID do carrinho não pode ser nulo");
        }
    }


}
