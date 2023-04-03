package co.com.citygames.model.orderdetail.gateways;

import co.com.citygames.model.orderdetail.OrderDetail;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OrderDetailGateway {

    Flux<OrderDetail> getAllOrders();
    Mono<OrderDetail> getOrderById(String orderId);
    Mono<OrderDetail> saveOrder(OrderDetail orderDetail);
    Mono<OrderDetail> updateOrder(String orderId, OrderDetail orderDetail);
    Mono<Void> deleteOrder(String orderId);
}
