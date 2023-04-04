package co.com.citygames.config;

import co.com.citygames.model.orderdetail.gateways.OrderDetailGateway;
import co.com.citygames.usecase.orderdetail.deleteorder.DeleteOrderUseCase;
import co.com.citygames.usecase.orderdetail.getallorders.GetAllOrdersUseCase;
import co.com.citygames.usecase.orderdetail.getorderbyid.GetOrderByIdUseCase;
import co.com.citygames.usecase.orderdetail.saveorder.SaveOrderUseCase;
import co.com.citygames.usecase.orderdetail.updateorder.UpdateOrderUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "co.com.citygames.usecase.orderdetail")
public class OrderUseCasesConfig {

    @Bean
    public GetAllOrdersUseCase getAllOrdersUseCase(OrderDetailGateway orderDetailGateway) {
        return new GetAllOrdersUseCase(orderDetailGateway);
    }

    @Bean
    public GetOrderByIdUseCase getOrderByIdUseCase(OrderDetailGateway orderDetailGateway) {
        return new GetOrderByIdUseCase(orderDetailGateway);
    }

    @Bean
    public SaveOrderUseCase saveOrderUseCase(OrderDetailGateway orderDetailGateway) {
        return new SaveOrderUseCase(orderDetailGateway);
    }

    @Bean
    public UpdateOrderUseCase updateOrderUseCase(OrderDetailGateway orderDetailGateway) {
        return new UpdateOrderUseCase(orderDetailGateway);
    }

    @Bean
    public DeleteOrderUseCase deleteOrderUseCase(OrderDetailGateway orderDetailGateway) {
        return new DeleteOrderUseCase(orderDetailGateway);
    }
}
