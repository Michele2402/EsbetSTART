package unisa.esbetstart.eventmanagement.infrastructure.mapper;

import org.springframework.stereotype.Component;
import unisa.esbetstart.eventmanagement.domain.model.Competition;
import unisa.esbetstart.eventmanagement.domain.model.Event;
import unisa.esbetstart.eventmanagement.domain.model.Game;
import unisa.esbetstart.eventmanagement.domain.model.Rule;
import unisa.esbetstart.eventmanagement.infrastructure.entity.CompetitionEntity;
import unisa.esbetstart.eventmanagement.infrastructure.entity.GameEntity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class  InfrastructureCompetitionMapper {

    /**
     * Maps a Set of CompetitionEntity to a Set of Competition model with simple details (no external objects).
     * @param competitionEntities the Set of CompetitionEntity to map
     * @return the Set of Competition model
     */
    public Set<Competition> toCompetitionModelWithSimpleDetailsList(Set<CompetitionEntity> competitionEntities) {

        if(competitionEntities == null)
            return new HashSet<>();

        return competitionEntities
                .stream()
                .map(this::toCompetitionModelWithSimpleDetails)
                .collect(Collectors.toSet());
    }

    /**
     * Maps a CompetitionEntity to a Competition model with simple details (no external objects).
     * @param competitionEntity the CompetitionEntity to map
     * @return the Competition model
     */
    public Competition toCompetitionModelWithSimpleDetails (CompetitionEntity competitionEntity) {
        return Competition.builder()
                .id(competitionEntity.getId())
                .name(competitionEntity.getName())
                .originCountry(competitionEntity.getOriginCountry())
                .build();
    }

    public List<Competition> toCompetitionModelWithSimpleDetailsList(List<CompetitionEntity> competitionEntities) {

        return competitionEntities
                .stream()
                .map(this::toCompetitionModelWithSimpleDetails)
                .collect(Collectors.toList());
    }

    /**
     * Maps a CompetitionEntity to a Competition model with a simple Game model (only the id).
     * @param competitionEntity the CompetitionEntity to map
     * @return the Competition model
     */
    public Competition toCompetitionModelWithSimpleGame(CompetitionEntity competitionEntity) {
        return Competition.builder()
                .id(competitionEntity.getId())
                .name(competitionEntity.getName())
                .originCountry(competitionEntity.getOriginCountry())
                .game(Game.builder()
                        .id(competitionEntity.getGame().getId())
                        .build())
                .build();
    }

    /**
     * Maps a CompetitionEntity to a complete Competition model with rules and events.
     * @param competitionEntity the CompetitionEntity to map
     * @return the Competition model
     */
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

    /**
     * Maps a CompetitionEntity to a complete Competition model with events, odds not included.
     * @param competitionEntity the CompetitionEntity to map
     * @return the Competition model
     */
    public Competition toCompetitionModelWithSimpleEventsList(CompetitionEntity competitionEntity) {
        Set<Event> events = competitionEntity.getEvents()
                .stream()
                .map(eventEntity -> Event.builder()
                        .id(eventEntity.getId())
                        .name(eventEntity.getName())
                        .date(eventEntity.getDate())
                        .isEnded(eventEntity.isEnded())
                        .build())
                .collect(Collectors.toSet());

        return Competition.builder()
                .id(competitionEntity.getId())
                .name(competitionEntity.getName())
                .originCountry(competitionEntity.getOriginCountry())
                .events(events)
                .build();
    }

    /**
     * Maps a Competition model to a CompetitionEntity with the game id.
     * @param competition the Competition model to map
     * @return the CompetitionEntity
     */
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
