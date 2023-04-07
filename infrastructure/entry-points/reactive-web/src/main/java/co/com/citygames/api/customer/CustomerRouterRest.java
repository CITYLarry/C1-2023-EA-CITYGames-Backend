package co.com.citygames.api.customer;

import co.com.citygames.model.customer.Customer;
import co.com.citygames.usecase.customer.deletecustomer.DeleteCustomerUseCase;
import co.com.citygames.usecase.customer.getallcustomers.GetAllCustomersUseCase;
import co.com.citygames.usecase.customer.getcustomerbyemail.GetCustomerByEmailUseCase;
import co.com.citygames.usecase.customer.getcustomerbyid.GetCustomerByIdUseCase;
import co.com.citygames.usecase.customer.savecustomer.SaveCustomerUseCase;
import co.com.citygames.usecase.customer.updatecustomer.UpdateCustomerUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
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
    @RouterOperation(
            path = "/api/v1/customers",
            produces = MediaType.APPLICATION_JSON_VALUE,
            beanClass = GetAllCustomersUseCase.class,
            beanMethod = "get",
            method = RequestMethod.GET,
            operation = @Operation(
                    operationId = "getAllCustomers",
                    tags = "Customer use cases",
                    responses = {
                            @ApiResponse(
                                    responseCode = "200",
                                    description = "Customers returned successfully",
                                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Customer.class)))
                            ),
                            @ApiResponse(
                                    responseCode = "204",
                                    description = "Nothing to show",
                                    content = @Content()
                            )
                    }
            )
    )
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
    @RouterOperation(
            path = "/api/v1/customers/{customerId}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            beanClass = GetCustomerByIdUseCase.class,
            beanMethod = "apply",
            method = RequestMethod.GET,
            operation = @Operation(
                    operationId = "getCustomerById",
                    tags = "Customer use cases",
                    parameters = {
                            @Parameter(
                                    name = "customerId",
                                    description = "Customer ID",
                                    required = true,
                                    in = ParameterIn.PATH
                            )
                    },
                    responses = {
                            @ApiResponse(
                                    responseCode = "200",
                                    description = "Customer found successfully",
                                    content = @Content(schema = @Schema(implementation = Customer.class))
                            ),
                            @ApiResponse(
                                    responseCode = "400",
                                    description = "Could not find customer for id:",
                                    content = @Content()
                            )
                    }
            )
    )
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
    @RouterOperation(
            path = "/api/v1/customers/email/{email}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            beanClass = GetCustomerByEmailUseCase.class,
            beanMethod = "apply",
            method = RequestMethod.GET,
            operation = @Operation(
                    operationId = "getCustomerByEmail",
                    tags = "Customer use cases",
                    parameters = {
                            @Parameter(
                                    name = "email",
                                    description = "Customer email address",
                                    required = true,
                                    in = ParameterIn.PATH
                            )
                    },
                    responses = {
                            @ApiResponse(
                                    responseCode = "200",
                                    description = "Customer found successfully",
                                    content = @Content(schema = @Schema(implementation = Customer.class))
                            ),
                            @ApiResponse(
                                    responseCode = "400",
                                    description = "Could not find customer for email:",
                                    content = @Content()
                            )
                    }
            )
    )
    public RouterFunction<ServerResponse> getCustomerByEmail(GetCustomerByEmailUseCase getCustomerByEmailUseCase) {
        return route(
                GET("/api/v1/customers/email/{email}"),
                request -> getCustomerByEmailUseCase.apply(request.pathVariable("email"))
                        .flatMap(customer -> ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(customer))
                        .onErrorResume(throwable -> ServerResponse.badRequest()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(throwable.getMessage()))
        );
    }

    @Bean
    @RouterOperation(
            path = "/api/v1/customers",
            produces = MediaType.APPLICATION_JSON_VALUE,
            beanClass = SaveCustomerUseCase.class,
            beanMethod = "apply",
            method = RequestMethod.POST,
            operation = @Operation(
                    operationId = "saveCustomer",
                    tags = "Customer use cases",
                    requestBody = @RequestBody(
                            required = true,
                            description = "Customer object to be saved",
                            content = @Content(schema = @Schema(implementation = Customer.class))
                    ),
                    responses = {
                            @ApiResponse(
                                    responseCode = "201",
                                    description = "Customer saved successfully",
                                    content = @Content(schema = @Schema(implementation = Customer.class))
                            ),
                            @ApiResponse(
                                    responseCode = "400",
                                    description = "Invalid input data",
                                    content = @Content()
                            )
                    }
            )
    )
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
    @RouterOperation(
            path = "/api/v1/customers/{customerId}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            beanClass = UpdateCustomerUseCase.class,
            beanMethod = "apply",
            method = RequestMethod.PUT,
            operation = @Operation(
                    operationId = "updateCustomer",
                    tags = "Customer use cases",
                    description = "Updates the customer with the given ID",
                    parameters = {
                            @Parameter(
                                    name = "customerId",
                                    description = "Customer ID",
                                    required = true,
                                    in = ParameterIn.PATH
                            )
                    },
                    requestBody = @RequestBody(
                            description = "Customer object to be updated",
                            required = true,
                            content = @Content(schema = @Schema(implementation = Customer.class))
                    ),
                    responses = {
                            @ApiResponse(
                                    responseCode = "200",
                                    description = "Customer updated successfully",
                                    content = @Content(schema = @Schema(implementation = Customer.class))
                            ),
                            @ApiResponse(
                                    responseCode = "400",
                                    description = "Could not find customer for id:",
                                    content = @Content()
                            )
                    }
            )
    )
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
    @RouterOperation(
            path = "/api/v1/customers/{customerId}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            beanClass = DeleteCustomerUseCase.class,
            beanMethod = "apply",
            method = RequestMethod.DELETE,
            operation = @Operation(
                    operationId = "deleteCustomer",
                    tags = "Customer use cases",
                    parameters = {
                            @Parameter(
                                    name = "customerId",
                                    description = "Customer ID",
                                    required = true,
                                    in = ParameterIn.PATH
                            )
                    },
                    responses = {
                            @ApiResponse(
                                    responseCode = "200",
                                    description = "Customer deleted successfully",
                                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
                            ),
                            @ApiResponse(
                                    responseCode = "400",
                                    description = "Could not find customer for id:",
                                    content = @Content()
                            )
                    }
            )
    )
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
