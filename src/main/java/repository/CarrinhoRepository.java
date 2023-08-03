package repository;

import model.Carrinho;
import model.ItemProduto;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.transaction.Transactional;

import java.util.Optional;

public class CarrinhoRepository {

    private final EntityManager entityManager;


    public CarrinhoRepository(EntityManager entityManager) {

        this.entityManager = entityManager;

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

}


