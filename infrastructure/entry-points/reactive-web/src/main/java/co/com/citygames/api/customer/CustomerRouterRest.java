package co.com.citygames.api.customer;

import co.com.citygames.model.customer.Customer;
import co.com.citygames.usecase.customer.deletecustomer.DeleteCustomerUseCase;
import co.com.citygames.usecase.customer.getallcustomers.GetAllCustomersUseCase;
import co.com.citygames.usecase.customer.getcustomerbyid.GetCustomerByIdUseCase;
import co.com.citygames.usecase.customer.savecustomer.SaveCustomerUseCase;
import co.com.citygames.usecase.customer.updatecustomer.UpdateCustomerUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class CustomerRouterRest {

    @Bean
    public RouterFunction<ServerResponse> getAllCustomers(GetAllCustomersUseCase getAllCustomersUseCase) {
        return route(
                GET("/api/v1/customers"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getAllCustomersUseCase.get(), Customer.class))
                        .onErrorResume(throwable -> ServerResponse.noContent().build())
        );
    }

    @Bean
    public RouterFunction<ServerResponse> getCustomerById(GetCustomerByIdUseCase getCustomerByIdUseCase) {
        return route(
                GET("/api/v1/customers/{customerId}"),
                request -> getCustomerByIdUseCase.apply(request.pathVariable("customerId"))
                        .flatMap(customer -> ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(customer))
                        .onErrorResume(throwable -> ServerResponse.badRequest()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(throwable.getMessage()))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> saveCustomer(SaveCustomerUseCase saveCustomerUseCase) {
        return route(
                POST("/api/v1/customers").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(Customer.class)
                        .flatMap(customer -> saveCustomerUseCase.apply(customer)
                                .flatMap(result -> ServerResponse.status(201)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(result))
                                .onErrorResume(throwable -> ServerResponse.badRequest()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(throwable.getMessage())))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> updateCustomer(UpdateCustomerUseCase updateCustomerUseCase) {
        return route(
                PUT("/api/v1/customers/{customerId}").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(Customer.class)
                        .flatMap(customer -> updateCustomerUseCase.apply(request.pathVariable("customerId"), customer)
                                .flatMap(result -> ServerResponse.ok()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(result))
                                .onErrorResume(throwable -> ServerResponse.badRequest()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(throwable.getMessage())))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> deleteCustomer(DeleteCustomerUseCase deleteCustomerUseCase) {
        return route(
                DELETE("/api/v1/customers/{customerId}"),
                request -> deleteCustomerUseCase.apply(request.pathVariable("customerId"))
                        .thenReturn(ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue("Deleted customer with id: " + request.pathVariable("customerId")))
                        .flatMap(responseMono -> responseMono)
                        .onErrorResume(throwable -> ServerResponse.badRequest()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(throwable.getMessage()))
        );
    }
}
