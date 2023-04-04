package co.com.citygames.usecase.customer.updatecustomer;

import co.com.citygames.model.customer.Customer;
import co.com.citygames.model.customer.gateways.CustomerGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.BiFunction;

@RequiredArgsConstructor
public class UpdateCustomerUseCase implements BiFunction<String, Customer, Mono<Customer>> {

    private final CustomerGateway customerGateway;

    public Mono<Customer> apply(String customerId, Customer customer) {
        return  customerGateway.updateCustomer(customerId, customer);
    }
}
