package co.com.citygames.usecase.updategame;

import co.com.citygames.model.game.Game;
import co.com.citygames.model.game.gateways.GameGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class UpdateGameUseCase {

    private final GameGateway gameGateway;

    public Mono<Game> update(String gameId, Game game) {
        return gameGateway.updateGame(gameId, game);
    }
}
