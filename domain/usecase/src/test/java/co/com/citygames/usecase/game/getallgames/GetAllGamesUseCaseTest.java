package co.com.citygames.usecase.game.getallgames;

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

import java.util.List;

@ExtendWith(MockitoExtension.class)
class GetAllGamesUseCaseTest {

    @Mock
    GameGateway gateway;

    GetAllGamesUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new GetAllGamesUseCase(gateway);
    }

    @Test
    @DisplayName("Get all games successfully")
    void get() {

        var g1 = new Game("testId", "title", "testPrice","edition","testCover", 1, true );
        var g2 = new Game("testId2", "title2","testPrice2", "edition2", "testCover2", 0,true);

        Mockito.when(gateway.getAllGames()).thenReturn(Flux.just(g1, g2));

        var result = useCase.get();

        StepVerifier.create(result)
                .expectNext(g1)
                .expectNext(g2)
                .verifyComplete();

        Mockito.verify(gateway).getAllGames();
    }

    @Test
    @DisplayName("Get all games with empty response")
    void getEmptyResponse() {

        Mockito.when(gateway.getAllGames()).thenReturn(Flux.empty());

        var result = useCase.get();

        StepVerifier.create(result)
                .verifyComplete();

        Mockito.verify(gateway).getAllGames();
    }
}