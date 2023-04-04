package co.com.citygames.model.customer;
import co.com.citygames.model.orderdetail.OrderDetail;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Customer {

    private String customerId;
    private String email;
    private String password;

    private List<OrderDetail> orderList;
}
