package co.com.citygames.config;

import co.com.citygames.model.game.gateways.GameGateway;
import co.com.citygames.usecase.deletegame.DeleteGameUseCase;
import co.com.citygames.usecase.getallgames.GetAllGamesUseCase;
import co.com.citygames.usecase.getallmicrosoftgames.GetAllMicrosoftGamesUseCase;
import co.com.citygames.usecase.getallpcgames.GetAllPcGamesUseCase;
import co.com.citygames.usecase.getallsonygames.GetAllSonyGamesUseCase;
import co.com.citygames.usecase.getgamebyid.GetGameByIdUseCase;
import co.com.citygames.usecase.savegame.SaveGameUseCase;
import co.com.citygames.usecase.updategame.UpdateGameUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(basePackages = "co.com.citygames.usecase")/*,
        includeFilters = {
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = "^.+UseCase$")
        },
        useDefaultFilters = false)*/
public class UseCasesConfig {

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
