package co.com.citygames.model.game;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Game {

    private String gameId;
    private String title;
    private String price;
    private String edition;
    private String cover;

    private Integer quantity;

    private Boolean available;
}
