package co.com.citygames.usecase.getgamebyid;

import co.com.citygames.model.game.Game;
import co.com.citygames.model.game.gateways.GameGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class GetGameByIdUseCase implements Function<String, Mono<Game>> {

    private final GameGateway gameGateway;

    public Mono<Game> apply(String gameId) {
        return gameGateway.getGameById(gameId);
    }
}
