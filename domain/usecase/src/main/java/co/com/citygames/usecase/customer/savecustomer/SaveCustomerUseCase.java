package co.com.citygames.usecase.customer.savecustomer;

import co.com.citygames.model.customer.Customer;
import co.com.citygames.model.customer.gateways.CustomerGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class SaveCustomerUseCase implements Function<Customer, Mono<Customer>> {

    private final CustomerGateway customerGateway;

    public Mono<Customer> apply(Customer customer) {
        return customerGateway.saveCustomer(customer);
    }
}
