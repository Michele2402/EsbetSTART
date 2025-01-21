package unisa.esbetstart.eventmanagement.infrastructure.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import unisa.esbetstart.eventmanagement.domain.model.Game;
import unisa.esbetstart.eventmanagement.infrastructure.entity.GameEntity;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class InfrastructureGameMapper {

    public final InfrastructureCompetitionMapper infrastructureCompetitionMapper;
    private final InfrastructureRuleMapper infrastructureRuleMapper;

    /**
     * Maps a GameEntity to a Game model with competitions
     * @param gameEntity the GameEntity to map
     * @return the Game model with competitions
     */
    public Game toGameModelWithCompetitions (GameEntity gameEntity) {
        return Game.builder()
                .id(gameEntity.getId())
                .name(gameEntity.getName())
                .competitions(infrastructureCompetitionMapper.toCompetitionModelWithSimpleDetailsList(gameEntity.getCompetitions()))
                .build();
    }


    /**
     * Maps a GameEntity to a Game model with rules
     * @param game the Game to map
     * @return the Game model with rules
     */
    public GameEntity toGameEntityWithRules(Game game) {
        return GameEntity.builder()
                .id(game.getId())
                .name(game.getName())
                .rules(game.getRules().stream().map(infrastructureRuleMapper::toRuleEntity).collect(Collectors.toSet()))
                .build();
    }

    /**
     * Maps a GameEntity to a Game model without external classes
     * @param gameEntity the GameEntity to map
     * @return the Game model

     */
    public Game toGameModel(GameEntity gameEntity) {
        return Game.builder()
                .id(gameEntity.getId())
                .name(gameEntity.getName())
                .build();
    }

    public Game toGameModelWithCompetitionsAndEvents(GameEntity gameEntity) {
        return Game.builder()
                .id(gameEntity.getId())
                .name(gameEntity.getName())
                .competitions(gameEntity.getCompetitions().stream().map(infrastructureCompetitionMapper::toCompetitionModelWithSimpleEventsList).collect(Collectors.toSet()))
                .build();
    }
}
