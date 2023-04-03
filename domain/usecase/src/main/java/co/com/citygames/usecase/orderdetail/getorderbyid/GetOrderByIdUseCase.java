package co.com.citygames.usecase.orderdetail.getorderbyid;

import co.com.citygames.model.orderdetail.OrderDetail;
import co.com.citygames.model.orderdetail.gateways.OrderDetailGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor

public class GetOrderByIdUseCase implements Function<String, Mono<OrderDetail>> {

    private final OrderDetailGateway orderDetailGateway;

    public Mono<OrderDetail> apply(String oderId) {
        return orderDetailGateway.getOrderById(oderId);
    }
}
