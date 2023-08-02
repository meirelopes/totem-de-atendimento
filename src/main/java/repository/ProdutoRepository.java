package repository;

import model.Produto;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
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

}
