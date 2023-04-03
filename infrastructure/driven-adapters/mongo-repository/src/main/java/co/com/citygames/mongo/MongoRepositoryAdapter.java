package co.com.citygames.mongo;

import co.com.citygames.model.game.Game;
import co.com.citygames.model.game.gateways.GameGateway;
import co.com.citygames.mongo.data.GameData;
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
        return gameDataRepository
                .findAll()
                .switchIfEmpty(Flux.error(new RuntimeException("No games found")))
                .map(gameData -> objectMapper.map(gameData, Game.class));
    }

    @Override
    public Mono<Game> getGameById(String gameId) {
        return gameDataRepository
                .findById(gameId)
                .switchIfEmpty(Mono.error(new RuntimeException("Could not find game for id: " + gameId)))
                .map(gameData -> objectMapper.map(gameData, Game.class));
    }

    @Override
    public Mono<Game> saveGame(Game game) {
        return gameDataRepository
                .save(objectMapper.map(game, GameData.class))
                .map(gameData -> objectMapper.map(gameData, Game.class));
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
