package co.com.citygames.usecase.game.getallsonygames;

import co.com.citygames.model.game.Game;
import co.com.citygames.model.game.gateways.GameGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class GetAllSonyGamesUseCaseTest {

    @Mock
    GameGateway gateway;

    GetAllSonyGamesUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new GetAllSonyGamesUseCase(gateway);
    }

    @Test
    @DisplayName("Get all sony games successfully")
    void get() {
        var g1 = new Game("testId", "title", "testPrice","Play Station 5","testCover", 1, true );
        var g2 = new Game("testId2", "title2","testPrice2", "Play Station 5", "testCover2", 0,true);

        Mockito.when(gateway.getAllSonyGames()).thenReturn(Flux.just(g1, g2));

        var result = useCase.get();

        StepVerifier.create(result)
                .expectNext(g1)
                .expectNext(g2)
                .verifyComplete();

        Mockito.verify(gateway).getAllSonyGames();
    }
}