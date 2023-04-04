package co.com.citygames.usecase.orderdetail.saveorder;

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
class SaveOrderUseCaseTest {

    @Mock
    OrderDetailGateway gateway;

    SaveOrderUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new SaveOrderUseCase(gateway);
    }

    @Test
    @DisplayName("Save order successfully")
    void saveSuccessfully() {

        var order = new OrderDetail("testId", "testCustomerId", Instant.now(), List.of());

        Mockito.when(gateway.saveOrder(order)).thenReturn(Mono.just(order));

        var result = useCase.apply(order);

        StepVerifier.create(result)
                .expectNext(order)
                .verifyComplete();

        Mockito.verify(gateway).saveOrder(order);
    }
}