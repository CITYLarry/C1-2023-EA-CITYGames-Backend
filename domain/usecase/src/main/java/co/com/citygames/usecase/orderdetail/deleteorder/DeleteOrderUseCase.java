package co.com.citygames.usecase.orderdetail.deleteorder;

import co.com.citygames.model.orderdetail.OrderDetail;
import co.com.citygames.model.orderdetail.gateways.OrderDetailGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class DeleteOrderUseCase implements Function<String, Mono<Void>> {

    private final OrderDetailGateway orderDetailGateway;

    public Mono<Void> apply(String orderId) {
        return orderDetailGateway.deleteOrder(orderId);
    }
}
