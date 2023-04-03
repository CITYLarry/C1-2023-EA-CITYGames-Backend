package co.com.citygames.usecase.updategame;

import co.com.citygames.model.game.Game;
import co.com.citygames.model.game.gateways.GameGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.BiFunction;

@RequiredArgsConstructor
public class UpdateGameUseCase implements BiFunction<String, Game, Mono<Game>> {

    private final GameGateway gameGateway;

    public Mono<Game> apply(String gameId, Game game) {
        return gameGateway.updateGame(gameId, game);
    }
}
