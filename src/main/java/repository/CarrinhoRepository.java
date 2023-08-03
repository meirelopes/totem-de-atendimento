package repository;

import model.Carrinho;
import model.ItemProduto;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.transaction.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

public class CarrinhoRepository {

    private final EntityManager entityManager;
    ItemProdutoRepository itemProdutoRepository;

    public CarrinhoRepository(EntityManager entityManager, ItemProdutoRepository itemProdutoRepository) {
        this.entityManager = entityManager;
        this.itemProdutoRepository = itemProdutoRepository;
    }

    public Optional<Carrinho> buscarCarrinhoPorId(Long id) {

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Carrinho carrinho = entityManager.find(Carrinho.class, id);
        transaction.commit();
        return Optional.ofNullable(carrinho);

    }

    @Transactional
    public void salvarCarrinho(Carrinho carrinho) {

        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            entityManager.persist(carrinho);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            throw new RuntimeException("Erro ao salvar o carrinho no banco de dados.", e);
        }
    }

    public BigDecimal obterValorTotalCompraPorIdCarrinho(Long idCarrinho) {

        Carrinho carrinho = entityManager.find(Carrinho.class, idCarrinho);

        if (carrinho != null) {

            return carrinho.getValorTotal();

        }

        // Retorna zero caso o carrinho não seja encontrado
        return BigDecimal.ZERO;
    }

    // Método exclui um ítem do carrinho
    public void removerItemProduto(Long produtoId, Long carrinhoId) {

        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            ItemProduto itemProduto = itemProdutoRepository.buscarItemProdutoPorIdDoProdutoEIdDoCarrinho(produtoId, carrinhoId);

            if (itemProduto == null) {
                throw new RuntimeException("ItemProduto não encontrado no carrinho.");
            }

            entityManager.remove(itemProduto);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Erro ao remover o ItemProduto do carrinho.", e);
        }
    }

}


