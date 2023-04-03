package co.com.citygames.api;

import co.com.citygames.model.game.Game;
import co.com.citygames.usecase.getallgames.GetAllGamesUseCase;
import co.com.citygames.usecase.getgamebyid.GetGameByIdUseCase;
import co.com.citygames.usecase.savegame.SaveGameUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
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

    @Bean
    public RouterFunction<ServerResponse> saveGame(SaveGameUseCase saveGameUseCase) {
        return route(
                POST("/api/v1/games/{gameId}").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(Game.class)
                        .flatMap(game -> saveGameUseCase.apply(game)
                                .flatMap(result -> ServerResponse.status(201)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(result))
                                .onErrorResume(throwable -> ServerResponse.badRequest()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(throwable.getMessage())))
        );
    }
}
