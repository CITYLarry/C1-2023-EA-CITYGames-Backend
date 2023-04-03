package co.com.citygames.mongo.data;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Document(collection = "games")
public class GameData {

    @Id
    private String gameId;
    private String title;
    private String yearRelease;
    private String edition;

    private Integer quantity;

    private Boolean available;

    private List<String> categories = new ArrayList<>();
}
