package co.com.citygames.usecase.game.savegame;

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
class SaveGameUseCaseTest {

    @Mock
    GameGateway gateway;

    SaveGameUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new SaveGameUseCase(gateway);
    }

    @Test
    @DisplayName("Save game successfully")
    void saveGameSuccessfully() {

        var game = new Game("testId", "title", "testPrice","edition","testCover", 1, true);

        Mockito.when(gateway.saveGame(game)).thenReturn(Mono.just(game));

        var result = useCase.apply(game);

        StepVerifier.create(result)
                .expectNext(game)
                .verifyComplete();

        Mockito.verify(gateway).saveGame(game);
    }
}