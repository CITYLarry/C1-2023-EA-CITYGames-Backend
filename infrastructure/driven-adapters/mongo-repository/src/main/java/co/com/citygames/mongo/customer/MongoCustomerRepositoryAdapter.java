package co.com.citygames.mongo.customer;

import co.com.citygames.model.customer.Customer;
import co.com.citygames.model.customer.gateways.CustomerGateway;
import co.com.citygames.model.game.Game;
import co.com.citygames.mongo.customer.data.CustomerData;
import co.com.citygames.mongo.game.data.GameData;
import lombok.RequiredArgsConstructor;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class MongoCustomerRepositoryAdapter implements CustomerGateway {

    private final MongoCustomerDBRepository customerRepository;
    private final ObjectMapper objectMapper;

    @Override
    public Flux<Customer> getAllCustomers() {
        return customerRepository
                .findAll()
                .switchIfEmpty(Flux.empty())
                .map(customerData -> objectMapper.map(customerData, Customer.class));
    }

    @Override
    public Mono<Customer> getCustomerById(String customerId) {
        return customerRepository
                .findById(customerId)
                .switchIfEmpty(Mono.error(new RuntimeException("Could not find customer for id: " + customerId)))
                .map(customerData -> objectMapper.map(customerData, Customer.class));
    }

    @Override
    public Mono<Customer> saveCustomer(Customer customer) {
        return customerRepository
                .save(objectMapper.map(customer, CustomerData.class))
                .map(customerData -> objectMapper.map(customerData, Customer.class));
    }

    @Override
    public Mono<Customer> updateCustomer(String customerId, Customer customer) {
        return customerRepository
                .findById(customerId)
                .switchIfEmpty(Mono.error(new RuntimeException("Could not find customer for id: " + customerId)))
                .flatMap(customerData -> {
                    customer.setCustomerId(customerData.getCustomerId());
                    return customerRepository.save(objectMapper.map(customer, CustomerData.class));
                })
                .map(customerData -> objectMapper.map(customerData, Customer.class));
    }

    @Override
    public Mono<Void> deleteCustomer(String customerId) {
        return customerRepository
                .findById(customerId)
                .switchIfEmpty(Mono.error(new RuntimeException("Could not find customer for id: " + customerId)))
                .flatMap(customerData -> customerRepository.deleteById(customerData.getCustomerId()));
    }
}
