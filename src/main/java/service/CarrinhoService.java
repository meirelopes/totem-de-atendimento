package service;

import model.Carrinho;
import model.ItemProduto;
import org.apache.commons.beanutils.BeanUtils;
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
    private Carrinho carrinhoAtual;

    private ProdutoRepository produtoRepository;
    private ItemProdutoRepository itemProdutoRepository;

    public CarrinhoService(CarrinhoRepository carrinhoRepository, Carrinho carrinhoAtual,
                           ProdutoRepository produtoRepository, ItemProdutoRepository itemProdutoRepository) {

        this.carrinhoRepository = carrinhoRepository;
        this.carrinhoAtual = carrinhoAtual;
        this.produtoRepository = produtoRepository;
        this.itemProdutoRepository = itemProdutoRepository;

    }

    public Carrinho criarCarrinho() {

        carrinhoAtual = new Carrinho();

        carrinhoAtual.setValorTotal(BigDecimal.ZERO);

        carrinhoAtual.setFechado(false);

        carrinhoAtual.setItens(new ArrayList<>());

        // Salvar o carrinho no banco de dados

        return carrinhoAtual;
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

        carrinhoAtual = buscarCarrinho(idCarrinho);

        List<ItemProduto> itens = carrinhoAtual.getItens();

        if (itens != null) {
            for (ItemProduto item : itens) {
                if (item.getProduto().getId() == codigoProduto) {
                    item.setQuantidade(novaQuantidade);
                    break;
                }
            }

            atualizarValorTotalCarrinho(carrinhoAtual);

            carrinhoRepository.salvarCarrinho(carrinhoAtual);
        }
    }

    // Método exclui item produto do carrinho
    public void excluirItem(Long produtoId, Long carrinhoId) {
        carrinhoAtual = buscarCarrinho(carrinhoId);

        if (!verificarProdutoNoCarrinho(produtoId, carrinhoAtual)) {

            System.out.println("Produto não existe no carrinho.");

        } else {
            carrinhoRepository.removerItemProduto(produtoId, carrinhoId);
            List<ItemProduto> itensAtualizados = new ArrayList<>();
            List<ItemProduto> itensCarrinho = carrinhoAtual.getItens();
            for (ItemProduto item : itensCarrinho) {
                if (!item.getProduto().getId().equals(produtoId)) {
                    itensAtualizados.add(item);
                }
            }

            carrinhoAtual.setItens(itensAtualizados);

            atualizarValorTotalCarrinho(carrinhoAtual);

        }
    }

    public boolean verificarProdutoNoCarrinho(Long produtoId, Carrinho carrinho) {
        List<ItemProduto> itensCarrinho = carrinho.getItens();
        for (ItemProduto item : itensCarrinho) {
            if (item.getProduto().getId().equals(produtoId)) {
                return true; // Produto encontrado no carrinho
            }
        }
        return false; // Produto não encontrado no carrinho
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

    public BigDecimal devolverTroco(BigDecimal nota, Long carrinhoId) {

        BigDecimal valorTotal = carrinhoRepository.obterValorTotalCompraPorIdCarrinho(carrinhoId);


            BigDecimal troco = (nota.subtract(valorTotal));

            return troco;

    }


}
