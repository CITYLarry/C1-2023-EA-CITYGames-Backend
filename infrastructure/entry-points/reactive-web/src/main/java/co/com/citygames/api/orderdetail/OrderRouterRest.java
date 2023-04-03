package co.com.citygames.api.orderdetail;

import co.com.citygames.model.game.Game;
import co.com.citygames.model.orderdetail.OrderDetail;
import co.com.citygames.usecase.game.getallgames.GetAllGamesUseCase;
import co.com.citygames.usecase.game.savegame.SaveGameUseCase;
import co.com.citygames.usecase.orderdetail.getallorders.GetAllOrdersUseCase;
import co.com.citygames.usecase.orderdetail.saveorder.SaveOrderUseCase;
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
}
