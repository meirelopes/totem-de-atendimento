package main;

import model.Carrinho;
import model.ItemProduto;
import model.Produto;
import repository.CarrinhoRepository;
import repository.ItemProdutoRepository;
import repository.ProdutoRepository;
import service.CarrinhoService;
import service.ItemProdutoService;
import view.Menu;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Testes {

    public static void main(String[] args) {
        // desabilita os logs do hibernate
        Logger hibernateLogger = Logger.getLogger("org.hibernate");
        hibernateLogger.setLevel(java.util.logging.Level.WARNING);
        Carrinho carrinho = new Carrinho();

        //menu.menuInicial();
        String nomeUnidadePersistencia = "FastFoodExpress"; // Nome da unidade de persistência definido no arquivo persistence.xml

        EntityManagerFactory factory = Persistence.createEntityManagerFactory(nomeUnidadePersistencia);
        EntityManager entityManager = factory.createEntityManager();

        ProdutoRepository produtoRepository = new ProdutoRepository(entityManager);


        Scanner scanner = new Scanner(System.in);

        ItemProdutoRepository itemProdutoRepository = new ItemProdutoRepository(entityManager);
        CarrinhoRepository carrinhoRepository = new CarrinhoRepository(entityManager, itemProdutoRepository, carrinho);

        CarrinhoService carrinhoService = new CarrinhoService(carrinhoRepository, carrinho,produtoRepository, itemProdutoRepository);


        ItemProdutoService itemProdutoService = new ItemProdutoService(itemProdutoRepository, produtoRepository);

        Menu menu = new Menu(scanner,carrinhoService, itemProdutoService, carrinho);

        menu.iniciarSistema();


        entityManager.close();
        factory.close();
    }

}