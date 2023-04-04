package co.com.citygames.usecase.orderdetail.deleteorder;

import co.com.citygames.model.orderdetail.gateways.OrderDetailGateway;
import co.com.citygames.usecase.orderdetail.getallorders.GetAllOrdersUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class DeleteOrderUseCaseTest {

    @Mock
    OrderDetailGateway gateway;

    DeleteOrderUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new DeleteOrderUseCase(gateway);
    }

    @Test
    @DisplayName("Delete order successfully")
    void delete() {
        var orderId = "testId";

        Mockito.when(gateway.deleteOrder(orderId)).thenReturn(Mono.empty());

        var result = useCase.apply(orderId);

        StepVerifier.create(result)
                .verifyComplete();

        Mockito.verify(gateway).deleteOrder(orderId);
    }

    @Test
    @DisplayName("Delete order with non-existent id")
    void deleteNonExistentId() {
        var orderId = "testId";

        Mockito.when(gateway.deleteOrder(orderId)).thenReturn(Mono.error(new RuntimeException("Could not find order for id: " + orderId)));

        var result = useCase.apply(orderId);

        StepVerifier.create(result)
                .expectErrorMatches(throwable -> throwable instanceof RuntimeException && throwable.getMessage().equals("Could not find order for id: " + orderId))
                .verify();

        Mockito.verify(gateway).deleteOrder(orderId);
    }
}