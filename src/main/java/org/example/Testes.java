package org.example;

import model.Produto;
import repository.ProdutoRepository;
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

        Scanner scanner = new Scanner(System.in);

        Menu menu = new Menu(scanner);

        // desabilita os logs do hibernate
        Logger hibernateLogger = Logger.getLogger("org.hibernate");
        hibernateLogger.setLevel(java.util.logging.Level.WARNING);

        //menu.menuInicial();
        String nomeUnidadePersistencia = "FastFoodExpress"; // Nome da unidade de persistência definido no arquivo persistence.xml

        EntityManagerFactory factory = Persistence.createEntityManagerFactory(nomeUnidadePersistencia);
        EntityManager entityManager = factory.createEntityManager();

        ProdutoRepository produtoRepository = new ProdutoRepository(entityManager);

        List<Produto> produtos = produtoRepository.listarProdutos();

        for (Produto produto : produtos) {
            System.out.println("Nome: " + produto.getNome() + ", Valor: R$" + produto.getValorUnitario());
        }

        entityManager.close();
        factory.close();
    }

}