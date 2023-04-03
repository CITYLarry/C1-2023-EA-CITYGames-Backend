package co.com.citygames.mongo.orderdetail.data;

import co.com.citygames.model.game.Game;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Document(collection = "orders")
public class OrderDetailData {

    @Id
    private String orderId;
    private String customerId;

    private Instant createdAt;

    private List<Game> gameList = new ArrayList<>();
}
