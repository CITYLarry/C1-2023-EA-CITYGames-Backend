package co.com.citygames.usecase.customer.getallcustomers;

import co.com.citygames.model.customer.Customer;
import co.com.citygames.model.customer.gateways.CustomerGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

import java.util.function.Supplier;

@RequiredArgsConstructor
public class GetAllCustomersUseCase implements Supplier<Flux<Customer>> {

    private final CustomerGateway customerGateway;

    public Flux<Customer> get() {
        return customerGateway.getAllCustomers();
    }
}
