package co.com.citygames.mongo.customer;

import co.com.citygames.mongo.customer.data.CustomerData;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface MongoCustomerDBRepository extends ReactiveMongoRepository<CustomerData, String> {

    Mono<CustomerData> findCustomerDataByEmail(String email);
}
