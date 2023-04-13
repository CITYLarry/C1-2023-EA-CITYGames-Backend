package co.com.citygames.usecase.customer.getallcustomers;

import co.com.citygames.model.customer.Customer;
import co.com.citygames.model.customer.gateways.CustomerGateway;
import co.com.citygames.model.game.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class GetAllCustomersUseCaseTest {

    @Mock
    CustomerGateway gateway;

    GetAllCustomersUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new GetAllCustomersUseCase(gateway);
    }

    @Test
    @DisplayName("Get all customers successfully")
    void get() {

        var c1 = new Customer("testId", "test@mail.com", List.of());
        var c2 = new Customer("testId2", "test2@mail.com", List.of());

        Mockito.when(gateway.getAllCustomers()).thenReturn(Flux.just(c1, c2));

        var result = useCase.get();

        StepVerifier.create(result)
                .expectNext(c1)
                .expectNext(c2)
                .verifyComplete();

        Mockito.verify(gateway).getAllCustomers();
    }

    @Test
    @DisplayName("Get all customer with empty response")
    void getEmptyResponse() {

        Mockito.when(gateway.getAllCustomers()).thenReturn(Flux.empty());

        var result = useCase.get();

        StepVerifier.create(result)
                .verifyComplete();

        Mockito.verify(gateway).getAllCustomers();
    }
}