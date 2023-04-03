package co.com.citygames.mongo;

import co.com.citygames.mongo.data.GameData;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface MongoDBRepository extends ReactiveMongoRepository<GameData, String> {
}
