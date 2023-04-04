package co.com.citygames.usecase.customer.deletecustomer;

import co.com.citygames.model.customer.gateways.CustomerGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class DeleteCustomerUseCase implements Function<String, Mono<Void>> {

    private final CustomerGateway customerGateway;

    public Mono<Void> apply(String customerId) {
        return customerGateway.deleteCustomer(customerId);
    }
}
