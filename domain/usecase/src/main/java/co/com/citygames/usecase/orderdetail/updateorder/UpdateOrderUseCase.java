package co.com.citygames.usecase.orderdetail.updateorder;

import co.com.citygames.model.orderdetail.OrderDetail;
import co.com.citygames.model.orderdetail.gateways.OrderDetailGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.BiFunction;

@RequiredArgsConstructor
public class UpdateOrderUseCase implements BiFunction<String, OrderDetail, Mono<OrderDetail>> {

    private final OrderDetailGateway orderDetailGateway;

    public Mono<OrderDetail> apply(String orderId, OrderDetail orderDetail) {
        return orderDetailGateway.updateOrder(orderId, orderDetail);
    }
}
