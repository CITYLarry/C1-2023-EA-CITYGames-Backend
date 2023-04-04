package co.com.citygames.usecase.orderdetail.getorderbyid;

import co.com.citygames.model.game.Game;
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
class GetOrderByIdUseCaseTest {

    @Mock
    OrderDetailGateway gateway;

    GetOrderByIdUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new GetOrderByIdUseCase(gateway);
    }

    @Test
    @DisplayName("Get order by id successfully")
    void apply() {

        var orderId = "testId";
        var order = new OrderDetail("testId", "testCustomerId", Instant.now(), List.of());

        Mockito.when(gateway.getOrderById(orderId)).thenReturn(Mono.just(order));

        var result = useCase.apply(orderId);

        StepVerifier.create(result)
                .expectNext(order)
                .verifyComplete();

        Mockito.verify(gateway).getOrderById(orderId);
    }

    @Test
    @DisplayName("Get order by id with non-existent id")
    void getByIdNonExistentId() {

        var orderId = "testId";

        var error = new RuntimeException("Could not find order for id: " + orderId);

        Mockito.when(gateway.getOrderById(orderId)).thenReturn(Mono.error(error));

        var result = useCase.apply(orderId);

        StepVerifier.create(result)
                .expectErrorMatches(throwable -> throwable.getMessage().equals(error.getMessage()))
                .verify();

        Mockito.verify(gateway).getOrderById(orderId);
    }
}