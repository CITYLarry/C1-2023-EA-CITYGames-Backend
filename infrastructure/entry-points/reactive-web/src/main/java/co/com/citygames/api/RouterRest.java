package co.com.citygames.api;

import co.com.citygames.model.game.Game;
import co.com.citygames.usecase.getallgames.GetAllGamesUseCase;
import co.com.citygames.usecase.getgamebyid.GetGameByIdUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterRest {
    @Bean
    public RouterFunction<ServerResponse> getAllGames(GetAllGamesUseCase getAllGamesUseCase) {
        return route(
                GET("/api/v1/games"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getAllGamesUseCase.get(), Game.class))
                        .onErrorResume(throwable -> ServerResponse.noContent().build())
        );
    }

    @Bean
    public RouterFunction<ServerResponse> getGameById(GetGameByIdUseCase getGameByIdUseCase) {
        return route(
                GET("/api/v1/games/{gameId}"),
                request -> getGameByIdUseCase.apply(request.pathVariable("gameId"))
                        .flatMap(game -> ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(game))
                        .onErrorResume(throwable -> ServerResponse.badRequest()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(throwable.getMessage()))
        );
    }
}
