package co.com.citygames.usecase.orderdetail.saveorder;

import co.com.citygames.model.orderdetail.OrderDetail;
import co.com.citygames.model.orderdetail.gateways.OrderDetailGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class SaveOrderUseCase implements Function<OrderDetail, Mono<OrderDetail>> {

    private final OrderDetailGateway orderDetailGateway;

    public Mono<OrderDetail> apply(OrderDetail orderDetail) {
        return orderDetailGateway.saveOrder(orderDetail);
    }
}
