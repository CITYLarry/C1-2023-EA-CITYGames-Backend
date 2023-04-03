package co.com.citygames.mongo.orderdetail;

import co.com.citygames.model.orderdetail.OrderDetail;
import co.com.citygames.model.orderdetail.gateways.OrderDetailGateway;
import co.com.citygames.mongo.orderdetail.data.OrderDetailData;
import lombok.RequiredArgsConstructor;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class MongoOrderRepositoryAdapter implements OrderDetailGateway {

    private final MongoOrderDBRepository orderDetailDataRepository;
    private final ObjectMapper objectMapper;

    @Override
    public Flux<OrderDetail> getAllOrders() {
        return orderDetailDataRepository
                .findAll()
                .switchIfEmpty(Flux.empty())
                .map(orderDetailData -> objectMapper.map(orderDetailData, OrderDetail.class));
    }

    @Override
    public Mono<OrderDetail> getOrderById(String orderId) {
        return orderDetailDataRepository
                .findById(orderId)
                .switchIfEmpty(Mono.error(new RuntimeException("Could not find order for id: " + orderId)))
                .map(orderDetailData -> objectMapper.map(orderDetailData, OrderDetail.class));
    }

    @Override
    public Mono<OrderDetail> saveOrder(OrderDetail orderDetail) {
        return orderDetailDataRepository
                .save(objectMapper.map(orderDetail, OrderDetailData.class))
                .map(orderDetailData -> objectMapper.map(orderDetailData, OrderDetail.class));
    }

    @Override
    public Mono<OrderDetail> updateOrder(String orderId, OrderDetail orderDetail) {
        return orderDetailDataRepository
                .findById(orderId)
                .switchIfEmpty(Mono.error(new RuntimeException("Could not find order for id: " + orderId)))
                .flatMap(orderDetailData -> {
                    orderDetail.setOrderId(orderDetailData.getOrderId());
                    return orderDetailDataRepository.save(objectMapper.map(orderDetail, OrderDetailData.class));
                })
                .map(orderDetailData -> objectMapper.map(orderDetailData, OrderDetail.class));
    }

    @Override
    public Mono<Void> deleteOrder(String orderId) {
        return orderDetailDataRepository
                .findById(orderId)
                .switchIfEmpty(Mono.error(new RuntimeException("Could not find order for id: " + orderId)))
                .flatMap(orderDetailData -> orderDetailDataRepository.deleteById(orderDetailData.getOrderId()));
    }
}
