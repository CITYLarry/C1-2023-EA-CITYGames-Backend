package co.com.citygames.api.game;

import co.com.citygames.model.game.Game;
import co.com.citygames.usecase.game.deletegame.DeleteGameUseCase;
import co.com.citygames.usecase.game.getallgames.GetAllGamesUseCase;
import co.com.citygames.usecase.game.getallmicrosoftgames.GetAllMicrosoftGamesUseCase;
import co.com.citygames.usecase.game.getallpcgames.GetAllPcGamesUseCase;
import co.com.citygames.usecase.game.getallsonygames.GetAllSonyGamesUseCase;
import co.com.citygames.usecase.game.getgamebyid.GetGameByIdUseCase;
import co.com.citygames.usecase.game.savegame.SaveGameUseCase;
import co.com.citygames.usecase.game.updategame.UpdateGameUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class GameRouterRest {

    @Bean
    @RouterOperation(
            path = "/api/v1/games",
            produces = MediaType.APPLICATION_JSON_VALUE,
            beanClass = GetAllGamesUseCase.class,
            beanMethod = "get",
            method = RequestMethod.GET,
            operation = @Operation(
                    operationId = "getAllGames",
                    tags = "Game use cases",
                    responses = {
                        @ApiResponse(
                                responseCode = "200",
                                description = "Games returned successfully",
                                content = @Content(array = @ArraySchema(schema = @Schema(implementation = Game.class)))
                        ),
                        @ApiResponse(
                                responseCode = "204",
                                description = "No games found",
                                content = @Content()
                        )
                    }
            )
    )
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
    @RouterOperation(
            path = "/api/v1/games/sony",
            produces = MediaType.APPLICATION_JSON_VALUE,
            beanClass = GetAllSonyGamesUseCase.class,
            beanMethod = "get",
            method = RequestMethod.GET,
            operation = @Operation(
                    operationId = "getAllSonyGames",
                    tags = "Game use cases",
                    responses = {
                            @ApiResponse(
                                    responseCode = "200",
                                    description = "Games found successfully",
                                    content = @Content(schema = @Schema(implementation = Game.class))
                            ),
                            @ApiResponse(
                                    responseCode = "400",
                                    description = "Could not find game for Play Station 5",
                                    content = @Content()
                            )
                    }
            )
    )
    public RouterFunction<ServerResponse> getAllSonyGames(GetAllSonyGamesUseCase getAllSonyGamesUseCase) {
        return route(
                GET("/api/v1/games/sony"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getAllSonyGamesUseCase.get(), Game.class))
                        .onErrorResume(throwable -> ServerResponse.noContent().build())
        );
    }

    @Bean
    @RouterOperation(
            path = "/api/v1/games/microsoft",
            produces = MediaType.APPLICATION_JSON_VALUE,
            beanClass = GetAllMicrosoftGamesUseCase.class,
            beanMethod = "get",
            method = RequestMethod.GET,
            operation = @Operation(
                    operationId = "getAllMicrosoftGames",
                    tags = "Game use cases",
                    responses = {
                            @ApiResponse(
                                    responseCode = "200",
                                    description = "Games found successfully",
                                    content = @Content(schema = @Schema(implementation = Game.class))
                            ),
                            @ApiResponse(
                                    responseCode = "400",
                                    description = "Could not find game for Xbox Series",
                                    content = @Content()
                            )
                    }
            )
    )
    public RouterFunction<ServerResponse> getAllMicrosoftGames(GetAllMicrosoftGamesUseCase getAllMicrosoftGamesUseCase) {
        return route(
                GET("/api/v1/games/microsoft"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getAllMicrosoftGamesUseCase.get(), Game.class))
                        .onErrorResume(throwable -> ServerResponse.noContent().build())
        );
    }

    @Bean
    @RouterOperation(
            path = "/api/v1/games/pc",
            produces = MediaType.APPLICATION_JSON_VALUE,
            beanClass = GetAllPcGamesUseCase.class,
            beanMethod = "get",
            method = RequestMethod.GET,
            operation = @Operation(
                    operationId = "getAllPcGames",
                    tags = "Game use cases",
                    responses = {
                            @ApiResponse(
                                    responseCode = "200",
                                    description = "Games found successfully",
                                    content = @Content(schema = @Schema(implementation = Game.class))
                            ),
                            @ApiResponse(
                                    responseCode = "400",
                                    description = "Could not find game for Pc",
                                    content = @Content()
                            )
                    }
            )
    )
    public RouterFunction<ServerResponse> getAllPcGames(GetAllPcGamesUseCase getAllPcGamesUseCase) {
        return route(
                GET("/api/v1/games/pc"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getAllPcGamesUseCase.get(), Game.class))
                        .onErrorResume(throwable -> ServerResponse.noContent().build())
        );
    }

    @Bean
    @RouterOperation(
            path = "/api/v1/games/{gameId}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            beanClass = GetGameByIdUseCase.class,
            beanMethod = "apply",
            method = RequestMethod.GET,
            operation = @Operation(
                    operationId = "getGameById",
                    tags = "Game use cases",
                    parameters = {
                            @Parameter(
                                    name = "gameId",
                                    description = "Game ID",
                                    required = true,
                                    in = ParameterIn.PATH
                            )
                    },
                    responses = {
                            @ApiResponse(
                                    responseCode = "200",
                                    description = "Game found successfully",
                                    content = @Content(schema = @Schema(implementation = Game.class))
                            ),
                            @ApiResponse(
                                    responseCode = "400",
                                    description = "Could not find game for id:",
                                    content = @Content()
                            )
                    }
            )
    )
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
    @RouterOperation(
            path = "/api/v1/games",
            produces = MediaType.APPLICATION_JSON_VALUE,
            beanClass = SaveGameUseCase.class,
            beanMethod = "apply",
            method = RequestMethod.POST,
            operation = @Operation(
                    operationId = "saveGame",
                    tags = "Game use cases",
                    requestBody = @RequestBody(
                            required = true,
                            description = "Game object to be saved",
                            content = @Content(schema = @Schema(implementation = Game.class))
                    ),
                    responses = {
                            @ApiResponse(
                                    responseCode = "201",
                                    description = "Game saved successfully",
                                    content = @Content(schema = @Schema(implementation = Game.class))
                            ),
                            @ApiResponse(
                                    responseCode = "400",
                                    description = "Invalid input data",
                                    content = @Content()
                            )
                    }
            )
    )
    public RouterFunction<ServerResponse> saveGame(SaveGameUseCase saveGameUseCase) {
        return route(
                POST("/api/v1/games").and(accept(MediaType.APPLICATION_JSON)),
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

    @Bean
    @RouterOperation(
            path = "/api/v1/games/{gameId}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            beanClass = UpdateGameUseCase.class,
            beanMethod = "apply",
            method = RequestMethod.PUT,
            operation = @Operation(
                    operationId = "updateGame",
                    tags = "Game use cases",
                    description = "Updates the game with the given ID",
                    parameters = {
                            @Parameter(
                                    name = "gameId",
                                    description = "Game ID",
                                    required = true,
                                    in = ParameterIn.PATH
                            )
                    },
                    requestBody = @RequestBody(
                            description = "Game object to be updated",
                            required = true,
                            content = @Content(schema = @Schema(implementation = Game.class))
                    ),
                    responses = {
                            @ApiResponse(
                                    responseCode = "200",
                                    description = "Game updated successfully",
                                    content = @Content(schema = @Schema(implementation = Game.class))
                            ),
                            @ApiResponse(
                                    responseCode = "400",
                                    description = "Could not find game for id:",
                                    content = @Content()
                            )
                    }
            )
    )
    public RouterFunction<ServerResponse> updateGame(UpdateGameUseCase updateGameUseCase) {
        return route(
                PUT("/api/v1/games/{gameId}").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(Game.class)
                        .flatMap(game -> updateGameUseCase.apply(request.pathVariable("gameId"), game)
                                .flatMap(result -> ServerResponse.ok()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(result))
                                .onErrorResume(throwable -> ServerResponse.badRequest()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(throwable.getMessage())))
        );
    }

    @Bean
    @RouterOperation(
            path = "/api/v1/games/{gameId}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            beanClass = DeleteGameUseCase.class,
            beanMethod = "apply",
            method = RequestMethod.DELETE,
            operation = @Operation(
                    operationId = "deleteGame",
                    tags = "Game use cases",
                    parameters = {
                            @Parameter(
                                    name = "gameId",
                                    description = "Game ID",
                                    required = true,
                                    in = ParameterIn.PATH
                            )
                    },
                    responses = {
                            @ApiResponse(
                                    responseCode = "200",
                                    description = "Game deleted successfully",
                                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
                            ),
                            @ApiResponse(
                                    responseCode = "400",
                                    description = "Could not find game for id:",
                                    content = @Content()
                            )
                    }
            )
    )
    public RouterFunction<ServerResponse> deleteGame(DeleteGameUseCase deleteGameUseCase) {
        return route(
                DELETE("/api/v1/games/{gameId}"),
                request -> deleteGameUseCase.apply(request.pathVariable("gameId"))
                        .thenReturn(ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue("Deleted game with id: " + request.pathVariable("gameId")))
                        .flatMap(responseMono -> responseMono)
                        .onErrorResume(throwable -> ServerResponse.badRequest()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(throwable.getMessage()))
        );
    }
}
