package co.com.citygames.usecase.game.updategame;

import co.com.citygames.model.game.Game;
import co.com.citygames.model.game.gateways.GameGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class UpdateGameUseCaseTest {

    @Mock
    GameGateway gateway;

    UpdateGameUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new UpdateGameUseCase(gateway);
    }

    @Test
    @DisplayName("Update game successfully")
    void updateGameSuccess() {

        var gameId = "testId";
        var updatedGame = new Game(gameId, "newTitle", "1996", "newEdition", 2, false, List.of());

        Mockito.when(gateway.updateGame(gameId, updatedGame)).thenReturn(Mono.just(updatedGame));

        var result = useCase.apply(gameId, updatedGame);

        StepVerifier.create(result)
                .expectNext(updatedGame)
                .verifyComplete();

        Mockito.verify(gateway).updateGame(gameId, updatedGame);
    }

    @Test
    @DisplayName("Update game with non-existent id")
    void updateGameNonExistentId() {

        var gameId = "testId";
        var updatedGame = new Game(gameId, "newTitle", "1996", "newEdition", 2, false, List.of());

        Mockito.when(gateway.updateGame(gameId, updatedGame)).thenReturn(Mono.error(new RuntimeException("Could not find game for id: " + gameId)));

        var result = useCase.apply(gameId, updatedGame);

        StepVerifier.create(result)
                .expectErrorMatches(throwable -> throwable.getMessage().equals("Could not find game for id: " + gameId))
                .verify();

        Mockito.verify(gateway).updateGame(gameId, updatedGame);
    }
}