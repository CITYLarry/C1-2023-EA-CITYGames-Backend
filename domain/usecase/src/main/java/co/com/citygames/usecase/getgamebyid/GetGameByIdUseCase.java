package co.com.citygames.usecase.getgamebyid;

import co.com.citygames.model.game.Game;
import co.com.citygames.model.game.gateways.GameGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class GetGameByIdUseCase {

    private final GameGateway gameGateway;

    public Mono<Game> get() {
        return gameGateway.getGameById();
    }
}
