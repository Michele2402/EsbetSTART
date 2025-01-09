package unisa.esbetstart.eventmanagement.infrastructure.mapper;

import org.springframework.stereotype.Component;
import unisa.esbetstart.eventmanagement.domain.model.Competition;
import unisa.esbetstart.eventmanagement.infrastructure.entity.CompetitionEntity;
import unisa.esbetstart.eventmanagement.infrastructure.entity.GameEntity;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class  InfrastructureCompetitionMapper {

    public Set<Competition> toCompetitionWithSimpleDetailsList (Set<CompetitionEntity> competitionEntities) {

        return competitionEntities
                .stream()
                .map(this::toCompetitionModelWithSimpleDetails)
                .collect(Collectors.toSet());
    }

    public Competition toCompetitionModelWithSimpleDetails (CompetitionEntity competitionEntity) {
        return Competition.builder()
                .id(competitionEntity.getId())
                .name(competitionEntity.getName())
                .originCountry(competitionEntity.getOriginCountry())
                .build();
    }

    public CompetitionEntity toCompetitionEntity (Competition competition) {
        return CompetitionEntity.builder()
                .id(competition.getId())
                .name(competition.getName())
                .originCountry(competition.getOriginCountry())
                .game(GameEntity.builder()
                        .id(competition.getGame().getId())
                        .build())
                .build();
    }
}
