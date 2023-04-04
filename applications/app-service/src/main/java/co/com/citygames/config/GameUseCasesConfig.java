package co.com.citygames.config;

import co.com.citygames.model.game.gateways.GameGateway;
import co.com.citygames.usecase.game.deletegame.DeleteGameUseCase;
import co.com.citygames.usecase.game.getallgames.GetAllGamesUseCase;
import co.com.citygames.usecase.game.getallmicrosoftgames.GetAllMicrosoftGamesUseCase;
import co.com.citygames.usecase.game.getallpcgames.GetAllPcGamesUseCase;
import co.com.citygames.usecase.game.getallsonygames.GetAllSonyGamesUseCase;
import co.com.citygames.usecase.game.getgamebyid.GetGameByIdUseCase;
import co.com.citygames.usecase.game.savegame.SaveGameUseCase;
import co.com.citygames.usecase.game.updategame.UpdateGameUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "co.com.citygames.usecase.game")/*,
        includeFilters = {
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = "^.+UseCase$")
        },
        useDefaultFilters = false)*/
public class GameUseCasesConfig {

        @Bean
        public GetAllGamesUseCase getAllGamesUseCase(GameGateway gameGateway) {
                return new GetAllGamesUseCase(gameGateway);
        }

        @Bean
        public GetGameByIdUseCase getGameByIdUseCase(GameGateway gameGateway) {
                return new GetGameByIdUseCase(gameGateway);
        }

        @Bean
        public SaveGameUseCase saveGameUseCase(GameGateway gameGateway) {
                return new SaveGameUseCase(gameGateway);
        }

        @Bean
        public UpdateGameUseCase updateGameUseCase(GameGateway gameGateway) {
                return new UpdateGameUseCase(gameGateway);
        }

        @Bean
        public DeleteGameUseCase deleteGameUseCase(GameGateway gameGateway) {
                return new DeleteGameUseCase(gameGateway);
        }

        @Bean
        public GetAllSonyGamesUseCase getAllSonyGamesUseCase(GameGateway gameGateway) {
                return new GetAllSonyGamesUseCase(gameGateway);
        }

        @Bean
        public GetAllMicrosoftGamesUseCase getAllMicrosoftGamesUseCase(GameGateway gameGateway) {
                return new GetAllMicrosoftGamesUseCase(gameGateway);
        }

        @Bean
        public GetAllPcGamesUseCase getAllPcGamesUseCase(GameGateway gameGateway) {
                return new GetAllPcGamesUseCase(gameGateway);
        }
}
