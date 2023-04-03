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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
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
