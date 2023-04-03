package co.com.citygames.model.game.gateways;

import co.com.citygames.model.game.Game;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface GameRepository {

    Flux<Game> getAllGames();
    Mono<Game> getGameById();
    Mono<Game> saveGame();
    Mono<String> deleteGame();

    Flux<Game> getAllSonyGames();
    Flux<Game> getAllMicrosoftGames();
    Flux<Game> getAllPcGames();
}
