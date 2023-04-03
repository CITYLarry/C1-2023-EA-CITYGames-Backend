package co.com.citygames.usecase.getallgames;

import co.com.citygames.model.game.Game;
import co.com.citygames.model.game.gateways.GameGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class GetAllGamesUseCase {
    private final GameGateway gameGateway;

    public Flux<Game> get() {
        return gameGateway.getAllGames();
    }
}
