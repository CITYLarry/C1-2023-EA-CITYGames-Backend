package co.com.citygames.mongo.game;

import co.com.citygames.mongo.game.data.GameData;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface MongoGameDBRepository extends ReactiveMongoRepository<GameData, String> {
    Flux<GameData> findAllByEdition(String edition);
}
