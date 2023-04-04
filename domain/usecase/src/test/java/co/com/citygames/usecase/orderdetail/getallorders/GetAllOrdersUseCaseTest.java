package co.com.citygames.usecase.orderdetail.getallorders;

import co.com.citygames.model.orderdetail.OrderDetail;
import co.com.citygames.model.orderdetail.gateways.OrderDetailGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Instant;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class GetAllOrdersUseCaseTest {

    @Mock
    OrderDetailGateway gateway;

    GetAllOrdersUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new GetAllOrdersUseCase(gateway);
    }

    @Test
    @DisplayName("Get all orders successfully")
    void get() {

        var o1 = new OrderDetail("testId", "testCustomerId", Instant.now(), List.of());
        var o2 = new OrderDetail("testId2", "testCustomerId2", Instant.now(), List.of());

        Mockito.when(gateway.getAllOrders()).thenReturn(Flux.just(o1, o2));

        var result = useCase.get();

        StepVerifier.create(result)
                .expectNext(o1)
                .expectNext(o2)
                .verifyComplete();

        Mockito.verify(gateway).getAllOrders();
    }

    @Test
    @DisplayName("Get all orders with empty response")
    void getEmptyResponse() {

        Mockito.when(gateway.getAllOrders()).thenReturn(Flux.empty());

        var result = useCase.get();

        StepVerifier.create(result)
                .verifyComplete();

        Mockito.verify(gateway).getAllOrders();
    }
}