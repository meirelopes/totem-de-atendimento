package repository;

import model.ItemProduto;
import model.Produto;

import javax.persistence.EntityManager;

public class ItemProdutoRepository {

    private EntityManager entityManager;

    public ItemProdutoRepository(EntityManager entityManager) {

        this.entityManager = entityManager;

    }

    public ItemProduto criarItemProduto(Long idProduto, Integer quantidade) {

        Produto produto = entityManager.find(Produto.class, idProduto);

        if (produto == null) {

            throw new RuntimeException("Produto não encontrado.");

        }

        ItemProduto itemProduto = new ItemProduto();
        itemProduto.setProduto(produto);
        itemProduto.setQuantidade(quantidade);

        return itemProduto;
    }

}
