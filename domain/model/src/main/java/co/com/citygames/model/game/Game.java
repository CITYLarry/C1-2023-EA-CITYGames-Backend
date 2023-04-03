package co.com.citygames.model.game;
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
public class Game {

    private String gameId;
    private String title;
    private String yearRelease;
    private String edition;

    private Integer quantity;

    private Boolean available;

    private List<String> categories;
}
