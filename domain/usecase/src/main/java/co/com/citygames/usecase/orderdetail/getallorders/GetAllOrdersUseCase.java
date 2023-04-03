package co.com.citygames.usecase.orderdetail.getallorders;

import co.com.citygames.model.orderdetail.OrderDetail;
import co.com.citygames.model.orderdetail.gateways.OrderDetailGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

import java.util.function.Supplier;

@RequiredArgsConstructor
public class GetAllOrdersUseCase implements Supplier<Flux<OrderDetail>> {

    private final OrderDetailGateway orderDetailGateway;

    public Flux<OrderDetail> get() {
        return orderDetailGateway.getAllOrders();
    }
}
