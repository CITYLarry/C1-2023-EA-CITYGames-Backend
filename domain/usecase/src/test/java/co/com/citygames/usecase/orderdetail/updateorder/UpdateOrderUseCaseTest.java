package co.com.citygames.usecase.orderdetail.updateorder;

import co.com.citygames.model.orderdetail.OrderDetail;
import co.com.citygames.model.orderdetail.gateways.OrderDetailGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Instant;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class UpdateOrderUseCaseTest {

    @Mock
    OrderDetailGateway gateway;

    UpdateOrderUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new UpdateOrderUseCase(gateway);
    }

    @Test
    @DisplayName("Update order successfully")
    void updateSuccess() {

        var orderId = "testId";
        var orderUpdated = new OrderDetail("testId", "newCustomerId", Instant.now(), List.of());

        Mockito.when(gateway.updateOrder(orderId, orderUpdated)).thenReturn(Mono.just(orderUpdated));

        var result = useCase.apply(orderId, orderUpdated);

        StepVerifier.create(result)
                .expectNext(orderUpdated)
                .verifyComplete();

        Mockito.verify(gateway).updateOrder(orderId, orderUpdated);
    }

    @Test
    @DisplayName("Update order with non-existent id")
    void updateGameNonExistentId() {

        var orderId = "testId";
        var orderUpdated = new OrderDetail("testId", "newCustomerId", Instant.now(), List.of());

        Mockito.when(gateway.updateOrder(orderId, orderUpdated)).thenReturn(Mono.error(new RuntimeException("Could not find order for id: " + orderId)));

        var result = useCase.apply(orderId, orderUpdated);

        StepVerifier.create(result)
                .expectErrorMatches(throwable -> throwable.getMessage().equals("Could not find order for id: " + orderId))
                .verify();

        Mockito.verify(gateway).updateOrder(orderId, orderUpdated);
    }
}