package co.com.citygames.usecase.customer.getcustomerbyid;

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
class GetCustomerByIdUseCaseTest {

    @Mock
    CustomerGateway gateway;

    GetCustomerByIdUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new GetCustomerByIdUseCase(gateway);
    }

    @Test
    @DisplayName("Get customer by id successfully")
    void getById() {

        var customerId = "testId";
        var customer = new Customer(customerId, "test@mail.com", List.of());

        Mockito.when(gateway.getCustomerById(customerId)).thenReturn(Mono.just(customer));

        var result = useCase.apply(customerId);

        StepVerifier.create(result)
                .expectNext(customer)
                .verifyComplete();

        Mockito.verify(gateway).getCustomerById(customerId);
    }

    @Test
    @DisplayName("Get customer by non-existent id")
    void getByIdNonExistentId() {

        var customerId = "nonExistentId";

        Mockito.when(gateway.getCustomerById(customerId)).thenReturn(Mono.error(new RuntimeException("Could not find customer for id: " + customerId)));

        var result = useCase.apply(customerId);

        StepVerifier.create(result)
                .expectErrorMatches(throwable -> throwable.getMessage().equals("Could not find customer for id: " + customerId))
                .verify();

        Mockito.verify(gateway).getCustomerById(customerId);
    }
}