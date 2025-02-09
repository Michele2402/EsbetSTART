package unisa.esbetstart.eventmanagement.infrastructure.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import unisa.esbetstart.eventmanagement.domain.model.Game;
import unisa.esbetstart.eventmanagement.domain.model.Rule;
import unisa.esbetstart.eventmanagement.infrastructure.entity.GameEntity;
import unisa.esbetstart.eventmanagement.infrastructure.entity.RuleEntity;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.LinkedHashSet;

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
     * Maps a List of GameEntity to a Set of Game model with rules
     * @param gameEntities the List of GameEntity to map
     * @return the Set of Game model with rules
     */
    public Set<Game> toGameModelWithRulesSet (List<GameEntity> gameEntities) {
        return gameEntities.stream()
                .map(this::toGameModelWithRules)
                .sorted(Comparator.comparing(Game::getName))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    /**
     * Maps a GameEntity to a Game model with rules
     * @param gameEntity the GameEntity to map
     * @return the Game model with rules
     */
    public Game toGameModelWithRules(GameEntity gameEntity) {

        return Game.builder()
                .id(gameEntity.getId())
                .name(gameEntity.getName())
                .rules(gameEntity.getRules().stream()
                        .sorted(Comparator.comparing(RuleEntity::getPosition))
                        .map(infrastructureRuleMapper::toRuleModel)
                        .collect(Collectors.toCollection(LinkedHashSet::new)))
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

    /**
     * Maps a GameEntity to a Game model with competitions and events
     * @param gameEntity the GameEntity to map
     * @return the Game model with competitions and events
     */
    public Game toGameModelWithCompetitionsAndEvents(GameEntity gameEntity) {
        return Game.builder()
                .id(gameEntity.getId())
                .name(gameEntity.getName())
                .competitions(gameEntity.getCompetitions().stream().map(infrastructureCompetitionMapper::toCompetitionModelWithSimpleEventsList).collect(Collectors.toSet()))
                .build();
    }
}
