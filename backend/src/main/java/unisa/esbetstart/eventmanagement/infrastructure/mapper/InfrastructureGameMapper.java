package unisa.esbetstart.eventmanagement.infrastructure.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import unisa.esbetstart.eventmanagement.domain.model.Game;
import unisa.esbetstart.eventmanagement.infrastructure.entity.GameEntity;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class InfrastructureGameMapper {

    public final InfrastructureCompetitionMapper infrastructureCompetitionMapper;
    private final InfrastructureRuleMapper infrastructureRuleMapper;

    public Game toGameModelWithCompetitions (GameEntity gameEntity) {
        return Game.builder()
                .id(gameEntity.getId())
                .name(gameEntity.getName())
                .competitions(infrastructureCompetitionMapper.toCompetitionModelWithSimpleDetailsList(gameEntity.getCompetitions()))
                .build();
    }

    public GameEntity toGameEntityWithRules(Game game) {
        return GameEntity.builder()
                .id(game.getId())
                .name(game.getName())
                .rules(game.getRules().stream().map(infrastructureRuleMapper::toRuleEntity).collect(Collectors.toSet()))
                .build();
    }

    public Set<Game> toGameModelWithRulesSet (List<GameEntity> gameEntities) {
        return gameEntities.stream().map(this::toGameModelWithRules).collect(Collectors.toSet());
    }

    public Game toGameModelWithRules(GameEntity gameEntity) {
        return Game.builder()
                .id(gameEntity.getId())
                .name(gameEntity.getName())
                .rules(gameEntity.getRules().stream().map(infrastructureRuleMapper::toRuleModel).collect(Collectors.toSet()))
                .build();
    }
}
