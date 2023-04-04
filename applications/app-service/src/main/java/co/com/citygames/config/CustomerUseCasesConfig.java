package co.com.citygames.config;

import co.com.citygames.model.customer.gateways.CustomerGateway;
import co.com.citygames.usecase.customer.deletecustomer.DeleteCustomerUseCase;
import co.com.citygames.usecase.customer.getallcustomers.GetAllCustomersUseCase;
import co.com.citygames.usecase.customer.getcustomerbyid.GetCustomerByIdUseCase;
import co.com.citygames.usecase.customer.savecustomer.SaveCustomerUseCase;
import co.com.citygames.usecase.customer.updatecustomer.UpdateCustomerUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "co.com.citygames.usecase.customer")
public class CustomerUseCasesConfig {

    @Bean
    public GetAllCustomersUseCase getAllCustomersUseCase(CustomerGateway customerGateway) {
        return new GetAllCustomersUseCase(customerGateway);
    }

    @Bean
    public GetCustomerByIdUseCase getCustomerByIdUseCase(CustomerGateway customerGateway) {
        return new GetCustomerByIdUseCase(customerGateway);
    }

    @Bean
    public SaveCustomerUseCase saveCustomerUseCase(CustomerGateway customerGateway) {
        return new SaveCustomerUseCase(customerGateway);
    }

    @Bean
    public UpdateCustomerUseCase updateCustomerUseCase(CustomerGateway customerGateway) {
        return new UpdateCustomerUseCase(customerGateway);
    }

    @Bean
    public DeleteCustomerUseCase deleteCustomerUseCase(CustomerGateway customerGateway) {
        return new DeleteCustomerUseCase(customerGateway);
    }
}
