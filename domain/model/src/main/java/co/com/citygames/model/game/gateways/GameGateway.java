package co.com.citygames.model.game.gateways;

import co.com.citygames.model.game.Game;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface GameGateway {

    Flux<Game> getAllGames();
    Mono<Game> getGameById(String gameId);
    Mono<Game> saveGame(Game game);
    Mono<Game> updateGame(String gameId, Game game);
    Mono<String> deleteGame(String gameId);

    Flux<Game> getAllSonyGames();
    Flux<Game> getAllMicrosoftGames();
    Flux<Game> getAllPcGames();
}
