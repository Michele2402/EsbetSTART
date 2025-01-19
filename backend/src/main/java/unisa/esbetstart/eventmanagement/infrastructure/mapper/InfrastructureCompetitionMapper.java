package unisa.esbetstart.eventmanagement.infrastructure.mapper;

import org.springframework.stereotype.Component;
import unisa.esbetstart.eventmanagement.domain.model.Competition;
import unisa.esbetstart.eventmanagement.domain.model.Event;
import unisa.esbetstart.eventmanagement.domain.model.Game;
import unisa.esbetstart.eventmanagement.domain.model.Rule;
import unisa.esbetstart.eventmanagement.infrastructure.entity.CompetitionEntity;
import unisa.esbetstart.eventmanagement.infrastructure.entity.GameEntity;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class  InfrastructureCompetitionMapper {

    public Set<Competition> toCompetitionModelWithSimpleDetailsList(Set<CompetitionEntity> competitionEntities) {

        if(competitionEntities == null)
            return new HashSet<>();

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

    public Competition toCompetitionWithRules (CompetitionEntity competitionEntity) {

        Set<Rule> rules = competitionEntity.getGame().getRules()
                .stream()
                .map(ruleEntity -> Rule.builder()
                        .id(ruleEntity.getId())
                        .name(ruleEntity.getName())
                        .position(ruleEntity.getPosition())
                        .build())
                .collect(Collectors.toSet());

        Set<Event> events = new HashSet<>();

        if(competitionEntity.getEvents() != null) {
            events = competitionEntity.getEvents()
                    .stream()
                    .map(eventEntity -> Event.builder()
                            .id(eventEntity.getId())
                            .name(eventEntity.getName())
                            .build())
                    .collect(Collectors.toSet());
        }

        return Competition.builder()
                .id(competitionEntity.getId())
                .name(competitionEntity.getName())
                .originCountry(competitionEntity.getOriginCountry())
                .events(events)
                .game(Game.builder()
                        .id(competitionEntity.getGame().getId())
                        .rules(rules)
                        .build())
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
