package co.com.citygames.usecase.game.savegame;

import co.com.citygames.model.game.Game;
import co.com.citygames.model.game.gateways.GameGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class SaveGameUseCase implements Function<Game, Mono<Game>> {

    private final GameGateway gameGateway;

    public Mono<Game> apply(Game game) {
        return gameGateway.saveGame(game);
    }
}
