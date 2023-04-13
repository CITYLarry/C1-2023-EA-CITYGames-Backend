package co.com.citygames.mongo.game.data;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collection = "games")
public class GameData {

    @Id
    private String gameId;
    private String title;
    private String price;
    private String edition;
    private String cover;

    private Integer quantity;

    private Boolean available;
}
