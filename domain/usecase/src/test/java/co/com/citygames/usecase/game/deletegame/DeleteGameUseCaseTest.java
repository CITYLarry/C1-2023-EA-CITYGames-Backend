package co.com.citygames.usecase.game.deletegame;

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

@ExtendWith(MockitoExtension.class)
class DeleteGameUseCaseTest {

    @Mock
    GameGateway gateway;

    DeleteGameUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new DeleteGameUseCase(gateway);
    }

    @Test
    @DisplayName("Delete game successfully")
    void delete() {
        var gameId = "testId";

        Mockito.when(gateway.deleteGame(gameId)).thenReturn(Mono.empty());

        var result = useCase.apply(gameId);

        StepVerifier.create(result)
                .verifyComplete();

        Mockito.verify(gateway).deleteGame(gameId);
    }

    @Test
    @DisplayName("Delete game with non-existent id")
    void deleteNonExistentId() {
        var gameId = "testId";

        Mockito.when(gateway.deleteGame(gameId)).thenReturn(Mono.error(new RuntimeException("Could not find game for id: " + gameId)));

        var result = useCase.apply(gameId);

        StepVerifier.create(result)
                .expectErrorMatches(throwable -> throwable instanceof RuntimeException && throwable.getMessage().equals("Could not find game for id: " + gameId))
                .verify();

        Mockito.verify(gateway).deleteGame(gameId);
    }
}