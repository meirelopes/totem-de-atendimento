package repositoryTest;

import model.Carrinho;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import repository.CarrinhoRepository;

import javax.persistence.EntityManager;
import java.util.Optional;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CarrinhoRepositoryTest {

    @InjectMocks
    private CarrinhoRepository carrinhoRepository;
    @Mock
    private EntityManager entityManager;

    @BeforeEach
    @Rule
    public void setup() {
        MockitoAnnotations.openMocks(this);
        entityManager = Mockito.mock(entityManager);
        carrinhoRepository = new CarrinhoRepository();
        carrinhoRepository.setEntityManager(entityManager);

    }

    @Test
    public void testBuscarCarrinhoPorId() {
        // Mock do objeto Carrinho para ser retornado pelo EntityManager
        Long carrinhoId = 1L;
        Carrinho carrinhoMock = new Carrinho();
        carrinhoMock.setId(carrinhoId);

        // Configurando o comportamento do EntityManager mockado
        when(entityManager.find(Carrinho.class, carrinhoId)).thenReturn(carrinhoMock);

        // Chama o método que será testado
        Optional<Carrinho> resultado = carrinhoRepository.buscarCarrinhoPorId(carrinhoId);

        // Verifica se o resultado é o esperado
        assertEquals(carrinhoMock, resultado.orElse(null));
    }
}
