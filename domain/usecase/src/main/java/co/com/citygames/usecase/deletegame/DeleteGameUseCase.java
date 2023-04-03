package co.com.citygames.usecase.deletegame;

import co.com.citygames.model.game.Game;
import co.com.citygames.model.game.gateways.GameGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class DeleteGameUseCase {

    private final GameGateway gameGateway;

    public Mono<Void> delete(String gameId) {
        return gameGateway.deleteGame(gameId);
    }
}
