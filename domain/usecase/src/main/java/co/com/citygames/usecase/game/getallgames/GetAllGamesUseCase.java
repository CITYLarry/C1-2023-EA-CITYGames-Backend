package co.com.citygames.usecase.game.getallgames;

import co.com.citygames.model.game.Game;
import co.com.citygames.model.game.gateways.GameGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

import java.util.function.Supplier;

@RequiredArgsConstructor
public class GetAllGamesUseCase implements Supplier<Flux<Game>> {
    private final GameGateway gameGateway;

    public Flux<Game> get() {
        return gameGateway.getAllGames();
    }
}
