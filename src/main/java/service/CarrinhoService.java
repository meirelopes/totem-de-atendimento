package service;

import model.Carrinho;
import model.ItemProduto;
import repository.CarrinhoRepository;
import repository.ItemProdutoRepository;
import repository.ProdutoRepository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CarrinhoService {
    private CarrinhoRepository carrinhoRepository;

    private ProdutoRepository produtoRepository;
    private ItemProdutoRepository itemProdutoRepository;


    public CarrinhoService(CarrinhoRepository carrinhoRepository,
                           ProdutoRepository produtoRepository, ItemProdutoRepository itemProdutoRepository) {

        this.carrinhoRepository = carrinhoRepository;
        this.produtoRepository = produtoRepository;
        this.itemProdutoRepository = itemProdutoRepository;

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
    public Carrinho adicionarItem(ItemProduto item, Carrinho carrinho) {

        List<ItemProduto> itens = carrinho.getItens();

        if (itens == null) {

            itens = new ArrayList<>();

        }
        item.setCarrinho(carrinho);
        item.setId(item.getId());
        item.setQuantidade(item.getQuantidade());
        item.setProduto(item.getProduto());
        itens.add(item);
        atualizarValorTotalCarrinho(carrinho);
        itemProdutoRepository.salvarItemProduto(item);

        carrinhoRepository.salvarCarrinho(carrinho);
        return carrinho;

    }

    // Atualiza a quantidade de produto do carrinho - testado
    @Transactional
    public void atualizarItemPeloCodigoProduto(Long codigoProduto, int novaQuantidade, Long idCarrinho) {

        Carrinho carrinho = buscarCarrinho(idCarrinho);

        List<ItemProduto> itens = carrinho.getItens();

        if (itens != null) {
            for (ItemProduto item : itens) {
                if (item.getProduto().getId() == codigoProduto) {
                    item.setQuantidade(novaQuantidade);
                    break;
                }
            }

            atualizarValorTotalCarrinho(carrinho);

            carrinhoRepository.salvarCarrinho(carrinho);
        }
    }

    // Método exclui item produto do carrinho
    public void excluirItem(Long produtoId, Long carrinhoId) {
        Carrinho carrinho = buscarCarrinho(carrinhoId);
        carrinhoRepository.removerItemProduto(produtoId, carrinhoId);
        List<ItemProduto> itensAtualizados = new ArrayList<>();
        List<ItemProduto> itensCarrinho = carrinho.getItens();
        for (ItemProduto item : itensCarrinho) {
            if (!item.getProduto().getId().equals(produtoId)) {
                itensAtualizados.add(item);
            }
        }

        carrinho.setItens(itensAtualizados);

        atualizarValorTotalCarrinho(carrinho);

    }

    // Atualiza valor total do carrinho - testado
    private void atualizarValorTotalCarrinho(Carrinho carrinho) {

        List<ItemProduto> itens = carrinho.getItens();

        BigDecimal valorTotal = BigDecimal.ZERO;

        for (ItemProduto item : itens) {

            BigDecimal subtotal = item.getProduto().getValorUnitario()
                    .multiply(BigDecimal.valueOf(item.getQuantidade()));

            valorTotal = valorTotal.add(subtotal);



        }
        carrinho.setValorTotal(valorTotal);
        carrinhoRepository.salvarCarrinho(carrinho);


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

     public BigDecimal devolverTroco (BigDecimal nota, Long carrinhoId) {

        BigDecimal valorTotal = carrinhoRepository.obterValorTotalCompraPorIdCarrinho(carrinhoId);

        int comparacao = nota.compareTo(valorTotal);

        if (comparacao >= 0 ) {

            return nota.subtract(valorTotal);

        } else {

            BigDecimal troco = (nota.subtract(valorTotal)).multiply(BigDecimal.valueOf(-1));
            return troco;

        }

     }


}
