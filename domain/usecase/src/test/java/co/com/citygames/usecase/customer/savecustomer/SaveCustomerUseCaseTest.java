package co.com.citygames.usecase.customer.savecustomer;

import co.com.citygames.model.customer.Customer;
import co.com.citygames.model.customer.gateways.CustomerGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class SaveCustomerUseCaseTest {

    @Mock
    CustomerGateway gateway;

    SaveCustomerUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new SaveCustomerUseCase(gateway);
    }

    @Test
    @DisplayName("Save customer successfully")
    void save() {
        var customerToSave = new Customer("testId", "test@mail.com", "testPassword", List.of());
        var savedCustomer = new Customer("testId", "test@mail.com", "testPassword", List.of());

        Mockito.when(gateway.saveCustomer(customerToSave)).thenReturn(Mono.just(savedCustomer));

        var result = useCase.apply(customerToSave);

        StepVerifier.create(result)
                .expectNext(savedCustomer)
                .verifyComplete();

        Mockito.verify(gateway).saveCustomer(customerToSave);
    }
}