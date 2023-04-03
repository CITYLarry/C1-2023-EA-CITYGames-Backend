package co.com.citygames.mongo.game;

import co.com.citygames.mongo.game.data.GameData;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface MongoDBRepository extends ReactiveMongoRepository<GameData, String> {
}