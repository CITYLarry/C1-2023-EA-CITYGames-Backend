package co.com.citygames.model.customer.gateways;

import co.com.citygames.model.customer.Customer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerGateway {

    Flux<Customer> getAllCustomers();
    Mono<Customer> getCustomerById(String customerId);
    Mono<Customer> saveCustomer(Customer customer);
    Mono<Customer> updateCustomer(String customerId, Customer customer);
    Mono<Void> deleteCustomer(String customerId);

    Mono<Customer> getCustomerByEmail(String email);
}
