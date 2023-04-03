package co.com.citygames.mongo.orderdetail;

import co.com.citygames.mongo.orderdetail.data.OrderDetailData;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface MongoDBRepository extends ReactiveMongoRepository<OrderDetailData, String> {
}
