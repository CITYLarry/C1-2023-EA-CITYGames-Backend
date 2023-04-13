package co.com.citygames.usecase.customer.getcustomerbyemail;

import co.com.citygames.model.customer.Customer;
import co.com.citygames.model.customer.gateways.CustomerGateway;
import co.com.citygames.usecase.customer.getcustomerbyid.GetCustomerByIdUseCase;
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
class GetCustomerByEmailUseCaseTest {

    @Mock
    CustomerGateway gateway;

    GetCustomerByEmailUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new GetCustomerByEmailUseCase(gateway);
    }

    @Test
    @DisplayName("Get customer by email successfully")
    void getByEmail() {

        var customerEmail = "test@mail.com";
        var customer = new Customer("testId", customerEmail, List.of());

        Mockito.when(gateway.getCustomerByEmail(customerEmail)).thenReturn(Mono.just(customer));

        var result = useCase.apply(customerEmail);

        StepVerifier.create(result)
                .expectNext(customer)
                .verifyComplete();

        Mockito.verify(gateway).getCustomerByEmail(customerEmail);
    }

    @Test
    @DisplayName("Get customer by non-existent email")
    void getByIdNonExistentId() {

        var customerEmail = "nonExistentEmail";

        Mockito.when(gateway.getCustomerByEmail(customerEmail)).thenReturn(Mono.error(new RuntimeException("Could not find customer for email: " + customerEmail)));

        var result = useCase.apply(customerEmail);

        StepVerifier.create(result)
                .expectErrorMatches(throwable -> throwable.getMessage().equals("Could not find customer for email: " + customerEmail))
                .verify();

        Mockito.verify(gateway).getCustomerByEmail(customerEmail);
    }
}