package co.com.citygames.usecase.getallmicrosoftgames;

import co.com.citygames.model.game.Game;
import co.com.citygames.model.game.gateways.GameGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class GetAllMicrosoftGamesUseCase {

    private final GameGateway gameGateway;

    public Flux<Game> get() {
        return gameGateway.getAllMicrosoftGames();
    }
}
