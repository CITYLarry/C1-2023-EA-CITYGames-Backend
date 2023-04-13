package co.com.citygames.usecase.game.getallmicrosoftgames;

import co.com.citygames.model.game.Game;
import co.com.citygames.model.game.gateways.GameGateway;
import co.com.citygames.usecase.game.getallsonygames.GetAllSonyGamesUseCase;
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
class GetAllMicrosoftGamesUseCaseTest {

    @Mock
    GameGateway gateway;

    GetAllMicrosoftGamesUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new GetAllMicrosoftGamesUseCase(gateway);
    }

    @Test
    @DisplayName("Get all microsoft games successfully")
    void get() {
        var g1 = new Game("testId", "title", "testPrice","Xbox Series","testCover", 1, true );
        var g2 = new Game("testId2", "title2","testPrice2", "Xbox Series", "testCover2", 0,true);

        Mockito.when(gateway.getAllMicrosoftGames()).thenReturn(Flux.just(g1, g2));

        var result = useCase.get();

        StepVerifier.create(result)
                .expectNext(g1)
                .expectNext(g2)
                .verifyComplete();

        Mockito.verify(gateway).getAllMicrosoftGames();
    }
}