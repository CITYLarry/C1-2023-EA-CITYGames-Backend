package co.com.citygames.usecase.getallpcgames;

import co.com.citygames.model.game.Game;
import co.com.citygames.model.game.gateways.GameGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class GetAllPcGamesUseCase {
    private final GameGateway gameGateway;

    public Flux<Game> get() {
        return gameGateway.getAllPcGames();
    }
}
