package co.com.citygames.model.orderdetail.gateways;

import co.com.citygames.model.game.Game;
import co.com.citygames.model.orderdetail.OrderDetail;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OrderDetailGateway {

    Flux<Game> getAllOrders();
    Mono<Game> getOrderById(String orderId);
    Mono<Game> saveOrder(OrderDetail orderDetail);
    Mono<Game> updateOrder(String orderId, OrderDetail orderDetail);
    Mono<Void> deleteOrder(String orderId);
}
