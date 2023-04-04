package co.com.citygames.api.orderdetail;

import co.com.citygames.model.game.Game;
import co.com.citygames.model.orderdetail.OrderDetail;
import co.com.citygames.usecase.game.deletegame.DeleteGameUseCase;
import co.com.citygames.usecase.game.getallgames.GetAllGamesUseCase;
import co.com.citygames.usecase.game.getgamebyid.GetGameByIdUseCase;
import co.com.citygames.usecase.game.savegame.SaveGameUseCase;
import co.com.citygames.usecase.game.updategame.UpdateGameUseCase;
import co.com.citygames.usecase.orderdetail.deleteorder.DeleteOrderUseCase;
import co.com.citygames.usecase.orderdetail.getallorders.GetAllOrdersUseCase;
import co.com.citygames.usecase.orderdetail.getorderbyid.GetOrderByIdUseCase;
import co.com.citygames.usecase.orderdetail.saveorder.SaveOrderUseCase;
import co.com.citygames.usecase.orderdetail.updateorder.UpdateOrderUseCase;
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
public class OrderRouterRest {

    @Bean
    @RouterOperation(
            path = "/api/v1/orders",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            beanClass = GetAllOrdersUseCase.class,
            beanMethod = "get",
            method = RequestMethod.GET,
            operation = @Operation(
                    operationId = "getAllOrders",
                    tags = "Order use cases",
                    responses = {
                            @ApiResponse(
                                    responseCode = "200",
                                    description = "Orders returned successfully",
                                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = OrderDetail.class)))
                            ),
                            @ApiResponse(
                                    responseCode = "204",
                                    description = "No orders found"
                            )
                    }
            )
    )
    public RouterFunction<ServerResponse> getAllOrders(GetAllOrdersUseCase getAllOrdersUseCase) {
        return route(
                GET("/api/v1/orders"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getAllOrdersUseCase.get(), OrderDetail.class))
                        .onErrorResume(throwable -> ServerResponse.noContent().build())
        );
    }

    @Bean
    @RouterOperation(
            path = "/api/v1/orders/{orderId}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            beanClass = GetOrderByIdUseCase.class,
            beanMethod = "apply",
            method = RequestMethod.GET,
            operation = @Operation(
                    operationId = "getOrderById",
                    tags = "Order use cases",
                    parameters = {
                            @Parameter(
                                    name = "orderId",
                                    description = "Order ID",
                                    required = true,
                                    in = ParameterIn.PATH
                            )
                    },
                    responses = {
                            @ApiResponse(
                                    responseCode = "200",
                                    description = "Order found successfully",
                                    content = @Content(schema = @Schema(implementation = OrderDetail.class))
                            ),
                            @ApiResponse(
                                    responseCode = "400",
                                    description = "Could not find order for id:",
                                    content = @Content()
                            )
                    }
            )
    )
    public RouterFunction<ServerResponse> getOrderById(GetOrderByIdUseCase getOrderByIdUseCase) {
        return route(
                GET("/api/v1/orders/{orderId}"),
                request -> getOrderByIdUseCase.apply(request.pathVariable("orderId"))
                        .flatMap(orderDetail -> ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(orderDetail))
                        .onErrorResume(throwable -> ServerResponse.badRequest()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(throwable.getMessage()))
        );
    }

    @Bean
    @RouterOperation(
            path = "/api/v1/orders",
            produces = MediaType.APPLICATION_JSON_VALUE,
            beanClass = SaveOrderUseCase.class,
            beanMethod = "apply",
            method = RequestMethod.POST,
            operation = @Operation(
                    operationId = "saveOrder",
                    tags = "Order use cases",
                    requestBody = @RequestBody(
                            required = true,
                            description = "Order object to be saved",
                            content = @Content(schema = @Schema(implementation = OrderDetail.class))
                    ),
                    responses = {
                            @ApiResponse(
                                    responseCode = "201",
                                    description = "Order saved successfully",
                                    content = @Content(schema = @Schema(implementation = OrderDetail.class))
                            ),
                            @ApiResponse(
                                    responseCode = "400",
                                    description = "Invalid input data",
                                    content = @Content()
                            )
                    }
            )
    )
    public RouterFunction<ServerResponse> saveOrder(SaveOrderUseCase saveOrderUseCase) {
        return route(
                POST("/api/v1/orders").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(OrderDetail.class)
                        .flatMap(order -> saveOrderUseCase.apply(order)
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
            path = "/api/v1/orders/{orderId}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            beanClass = UpdateOrderUseCase.class,
            beanMethod = "apply",
            method = RequestMethod.PUT,
            operation = @Operation(
                    operationId = "updateOrder",
                    tags = "Order use cases",
                    parameters = {
                            @Parameter(
                                    name = "orderId",
                                    description = "Order ID",
                                    required = true,
                                    in = ParameterIn.PATH
                            )
                    },
                    requestBody = @RequestBody(
                            required = true,
                            description = "Order object to be updated",
                            content = @Content(schema = @Schema(implementation = OrderDetail.class))
                    ),
                    responses = {
                            @ApiResponse(
                                    responseCode = "200",
                                    description = "Order updated successfully",
                                    content = @Content(schema = @Schema(implementation = OrderDetail.class))
                            ),
                            @ApiResponse(
                                    responseCode = "400",
                                    description = "Invalid input data",
                                    content = @Content()
                            )
                    }
            )
    )
    public RouterFunction<ServerResponse> updateOrder(UpdateOrderUseCase updateOrderUseCase) {
        return route(
                PUT("/api/v1/orders/{orderId}").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(OrderDetail.class)
                        .flatMap(orderDetail -> updateOrderUseCase.apply(request.pathVariable("orderId"), orderDetail)
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
            path = "/api/v1/orders/{orderId}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            beanClass = DeleteOrderUseCase.class,
            beanMethod = "apply",
            method = RequestMethod.DELETE,
            operation = @Operation(
                    operationId = "deleteOrder",
                    tags = "Order use cases",
                    parameters = {
                            @Parameter(
                                    name = "orderId",
                                    description = "Order ID",
                                    required = true,
                                    in = ParameterIn.PATH
                            )
                    },
                    responses = {
                            @ApiResponse(
                                    responseCode = "200",
                                    description = "Order deleted successfully",
                                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
                            ),
                            @ApiResponse(
                                    responseCode = "400",
                                    description = "Could not find order for id:",
                                    content = @Content()
                            )
                    }
            )
    )
    public RouterFunction<ServerResponse> deleteOrder(DeleteOrderUseCase deleteOrderUseCase) {
        return route(
                DELETE("/api/v1/orders/{orderId}"),
                request -> deleteOrderUseCase.apply(request.pathVariable("orderId"))
                        .thenReturn(ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue("Deleted order with id: " + request.pathVariable("orderId")))
                        .flatMap(responseMono -> responseMono)
                        .onErrorResume(throwable -> ServerResponse.badRequest()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(throwable.getMessage()))
        );
    }
}
