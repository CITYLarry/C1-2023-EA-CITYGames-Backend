package co.com.citygames.usecase.savegame;

import co.com.citygames.model.game.Game;
import co.com.citygames.model.game.gateways.GameGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class SaveGameUseCase {

    private final GameGateway gameGateway;

    public Mono<Game> save(Game game) {
        return gameGateway.saveGame(game);
    }
}
