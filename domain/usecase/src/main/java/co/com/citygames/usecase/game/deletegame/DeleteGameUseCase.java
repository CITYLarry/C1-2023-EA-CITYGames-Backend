package co.com.citygames.usecase.game.deletegame;

import co.com.citygames.model.game.gateways.GameGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class DeleteGameUseCase implements Function<String, Mono<Void>> {

    private final GameGateway gameGateway;

    public Mono<Void> apply(String gameId) {
        return gameGateway.deleteGame(gameId);
    }
}
