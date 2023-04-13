package co.com.citygames.mongo.customer.data;

import co.com.citygames.model.orderdetail.OrderDetail;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Document(collection = "customers")
public class CustomerData {

    @Id
    private String customerId;
    private String email;

    private List<OrderDetail> orderList = new ArrayList<>();
}
