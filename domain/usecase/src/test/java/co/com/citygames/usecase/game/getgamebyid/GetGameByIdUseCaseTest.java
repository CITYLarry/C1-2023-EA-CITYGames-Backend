package co.com.citygames.usecase.game.getgamebyid;

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
class GetGameByIdUseCaseTest {

    @Mock
    GameGateway gateway;

    GetGameByIdUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new GetGameByIdUseCase(gateway);
    }

    @Test
    @DisplayName("Get game by id successfully")
    void apply() {

        var gameId = "testId";
        var game = new Game("testId", "title", "testPrice","edition","testCover", 1, true);

        Mockito.when(gateway.getGameById(gameId)).thenReturn(Mono.just(game));

        var result = useCase.apply(gameId);

        StepVerifier.create(result)
                .expectNext(game)
                .verifyComplete();

        Mockito.verify(gateway).getGameById(gameId);
    }

    @Test
    @DisplayName("Get game by id with non-existent id")
    void getByIdNonExistentId() {

        var gameId = "testId";

        var error = new RuntimeException("Could not find game for id: " + gameId);

        Mockito.when(gateway.getGameById(gameId)).thenReturn(Mono.error(error));

        var result = useCase.apply(gameId);

        StepVerifier.create(result)
                .expectErrorMatches(throwable -> throwable.getMessage().equals(error.getMessage()))
                .verify();

        Mockito.verify(gateway).getGameById(gameId);
    }
}