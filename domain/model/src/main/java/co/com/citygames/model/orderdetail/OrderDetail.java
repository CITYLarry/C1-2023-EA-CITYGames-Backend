package co.com.citygames.model.orderdetail;
import co.com.citygames.model.game.Game;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class OrderDetail {

    private String orderId;
    private String customerId;

    private Instant createdAt;

    private List<Game> gameList;
}
