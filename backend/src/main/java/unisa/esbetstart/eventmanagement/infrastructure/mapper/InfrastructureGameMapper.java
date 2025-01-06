package unisa.esbetstart.eventmanagement.infrastructure.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import unisa.esbetstart.eventmanagement.domain.model.Game;
import unisa.esbetstart.eventmanagement.infrastructure.entity.GameEntity;

@Component
@RequiredArgsConstructor
public class InfrastructureGameMapper {

    public final InfrastructureCompetitionMapper infrastructureCompetitionMapper;

    public Game toGameModelWithCompetitions (GameEntity gameEntity) {
        return Game.builder()
                .id(gameEntity.getId())
                .name(gameEntity.getName())
                .competitions(infrastructureCompetitionMapper.toCompetitionWithSimpleDetailsList(gameEntity.getCompetitions()))
                .build();
    }
}
