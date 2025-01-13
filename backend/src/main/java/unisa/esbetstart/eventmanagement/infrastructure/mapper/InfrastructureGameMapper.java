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
}
