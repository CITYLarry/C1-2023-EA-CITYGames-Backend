package co.com.citygames.usecase.customer.updatecustomer;

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
class UpdateCustomerUseCaseTest {

    @Mock
    CustomerGateway customerGateway;

    UpdateCustomerUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new UpdateCustomerUseCase(customerGateway);
    }

    @Test
    @DisplayName("Update customer successfully")
    void update() {

        String customerId = "testId";
        var updatedCustomer = new Customer("testId", "newtest@mail.com", List.of());

        Mockito.when(customerGateway.updateCustomer(customerId, updatedCustomer))
                .thenReturn(Mono.just(updatedCustomer));


        var result = useCase.apply(customerId, updatedCustomer);

        StepVerifier.create(result)
                .expectNext(updatedCustomer)
                .verifyComplete();

        Mockito.verify(customerGateway).updateCustomer(customerId, updatedCustomer);
    }

    @Test
    @DisplayName("Update customer with non-existing id")
    void updateNonExistingId() {

        String customerId = "nonExistingId";
        var customerToUpdate = new Customer("nonExistingId", "newtest@mail.com", List.of());

        Mockito.when(customerGateway.updateCustomer(customerId, customerToUpdate))
                .thenReturn(Mono.error(new RuntimeException("Could not find customer for id: " + customerId)));

        var result = useCase.apply(customerId, customerToUpdate);

        StepVerifier.create(result)
                .expectErrorMatches(throwable -> throwable.getMessage().equals("Could not find customer for id: " + customerId))
                .verify();

        Mockito.verify(customerGateway).updateCustomer(customerId, customerToUpdate);
    }
}