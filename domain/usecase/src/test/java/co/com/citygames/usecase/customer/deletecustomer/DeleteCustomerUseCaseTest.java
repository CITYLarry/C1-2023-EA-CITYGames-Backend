package co.com.citygames.usecase.customer.deletecustomer;

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

@ExtendWith(MockitoExtension.class)
class DeleteCustomerUseCaseTest {

    @Mock
    CustomerGateway gateway;

    DeleteCustomerUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new DeleteCustomerUseCase(gateway);
    }

    @Test
    @DisplayName("Delete customer successfully")
    void delete() {

        var customerId = "testId";

        Mockito.when(gateway.deleteCustomer(customerId)).thenReturn(Mono.empty());

        var result = useCase.apply(customerId);

        StepVerifier.create(result)
                .verifyComplete();

        Mockito.verify(gateway).deleteCustomer(customerId);
    }

    @Test
    @DisplayName("Delete non-existent customer")
    void deleteNonExistent() {
        var customerId = "testId";

        Mockito.when(gateway.deleteCustomer(customerId)).thenReturn(Mono.error(new RuntimeException("Could not find customer for id: " + customerId)));

        var result = useCase.apply(customerId);

        StepVerifier.create(result)
                .expectErrorMatches(throwable ->  throwable.getMessage().equals("Could not find customer for id: " + customerId))
                .verify();

        Mockito.verify(gateway).deleteCustomer(customerId);
    }
}