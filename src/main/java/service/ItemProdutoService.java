package service;

import model.ItemProduto;
import repository.ItemProdutoRepository;
import repository.ProdutoRepository;

public class ItemProdutoService {

    ItemProdutoRepository itemProdutoRepository;
    ProdutoRepository produtoRepository;


    public ItemProdutoService(ItemProdutoRepository itemProdutoRepository, ProdutoRepository produtoRepository) {

        this.itemProdutoRepository = itemProdutoRepository;
        this.produtoRepository = produtoRepository;

    }

    public ItemProduto criarItemProduto(String nomeProduto, int quantidade) {

        Long produtoId = produtoRepository.buscarIdPorNome(nomeProduto);

        return itemProdutoRepository.criarItemProduto(produtoId, quantidade);

    }

}
