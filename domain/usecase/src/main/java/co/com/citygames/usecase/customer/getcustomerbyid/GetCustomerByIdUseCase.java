package co.com.citygames.usecase.customer.getcustomerbyid;

import co.com.citygames.model.customer.Customer;
import co.com.citygames.model.customer.gateways.CustomerGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class GetCustomerByIdUseCase implements Function<String, Mono<Customer>> {

    private final CustomerGateway customerGateway;

    public Mono<Customer> apply(String customerId) {
        return customerGateway.getCustomerById(customerId);
    }
}
