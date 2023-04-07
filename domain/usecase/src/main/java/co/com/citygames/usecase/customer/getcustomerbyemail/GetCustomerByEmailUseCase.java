package co.com.citygames.usecase.customer.getcustomerbyemail;

import co.com.citygames.model.customer.Customer;
import co.com.citygames.model.customer.gateways.CustomerGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class GetCustomerByEmailUseCase implements Function<String, Mono<Customer>> {

    private final CustomerGateway customerGateway;

    public Mono<Customer> apply(String email) {
        return customerGateway.getCustomerByEmail(email);
    }
}
