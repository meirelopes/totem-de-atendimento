package repository;

import model.Produto;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.util.List;


public class ProdutoRepository {

    private EntityManager entityManager;

    public ProdutoRepository(EntityManager entityManager) {

        this.entityManager = entityManager;

    }

    public List<Produto> listarProdutos() {

        String jpql = "SELECT p FROM Produto p";

        TypedQuery<Produto> query = entityManager.createQuery(jpql, Produto.class);

        return query.getResultList();

    }

    public void adicionarProduto() {


    }

    //  Método retornará o id do primeiro produto com o nome correspondente encontrado no banco de dados.
    //  Se houver mais de um produto com o mesmo nome, apenas o id do primeiro será retornado.
    public Long buscarIdPorNome(String nome) {

        TypedQuery<Long> query = entityManager.createQuery(
                "SELECT p.id FROM Produto p WHERE p.nome = :nome", Long.class
        );
        query.setParameter("nome", nome);

        try {
            return query.getSingleResult();

        } catch (NoResultException e) {

            return null;

        }

    }

    //Método retornará o valor do primeiro produto com o nome correspondente encontrado no banco de dados.
    // Se houver mais de um produto com o mesmo nome, apenas o valor do primeiro será retornado.
    public BigDecimal buscarValorPorNome(String nome) {

        TypedQuery<BigDecimal> query = entityManager.createQuery(

                "SELECT p.valorUnitario FROM Produto p WHERE p.nome = :nome", BigDecimal.class);

        query.setParameter("nome", nome);

        try {

            return query.getSingleResult();

        } catch (NoResultException e) {

            return null;
        }
    }

}
