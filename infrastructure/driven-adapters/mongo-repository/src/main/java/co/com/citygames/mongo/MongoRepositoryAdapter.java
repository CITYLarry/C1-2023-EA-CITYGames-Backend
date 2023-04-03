package co.com.citygames.mongo;

import co.com.citygames.model.game.Game;
import co.com.citygames.model.game.gateways.GameGateway;
import lombok.RequiredArgsConstructor;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class MongoRepositoryAdapter implements GameGateway {

    private final MongoDBRepository gameDataRepository;
    private final ObjectMapper objectMapper;

    @Override
    public Flux<Game> getAllGames() {
        return null;
    }

    @Override
    public Mono<Game> getGameById() {
        return null;
    }

    @Override
    public Mono<Game> saveGame(Game game) {
        return null;
    }

    @Override
    public Mono<Game> updateGame(String gameId, Game game) {
        return null;
    }

    @Override
    public Mono<String> deleteGame(String gameId) {
        return null;
    }

    @Override
    public Flux<Game> getAllSonyGames() {
        return null;
    }

    @Override
    public Flux<Game> getAllMicrosoftGames() {
        return null;
    }

    @Override
    public Flux<Game> getAllPcGames() {
        return null;
    }
}
