package repository;

import model.Carrinho;
import model.ItemProduto;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
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

}


