package unisa.esbetstart.eventmanagement.infrastructure.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import unisa.esbetstart.eventmanagement.domain.model.Competition;
import unisa.esbetstart.eventmanagement.domain.model.Event;
import unisa.esbetstart.eventmanagement.infrastructure.entity.CompetitionEntity;
import unisa.esbetstart.eventmanagement.infrastructure.entity.EventEntity;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class InfrastructureEventMapper {

    private final InfrastructureOddMapper infrastructureOddMapper;
    private final InfrastructureCompetitionMapper infrastructureCompetitionMapper;

    public EventEntity toEventEntity (Event event) {
        return EventEntity.builder()
                .id(event.getId())
                .name(event.getName())
                .date(event.getDate())
                .isEnded(event.isEnded())
                .competition(CompetitionEntity.builder()
                        .id(event.getCompetition().getId()).build())
                .odds(event.getOdds().stream().map(infrastructureOddMapper::toOddEntity).collect(Collectors.toSet()))
                .build();
    }


    /**
     * Maps an event entity to an event model details up to the bet placed
     * @param eventEntity the event entity
     * @return the event model
     */

    public Event toEventModelWithAlotOfDetails(EventEntity eventEntity) {

        return Event.builder()
                .id(eventEntity.getId())
                .name(eventEntity.getName())
                .date(eventEntity.getDate())
                .isEnded(eventEntity.isEnded())
                .odds(eventEntity.getOdds().stream().map(infrastructureOddMapper::toOddModelWithAlotOfDetails).collect(Collectors.toSet()))
                .competition(Competition.builder()
                        .id(eventEntity.getCompetition().getId()).build())
                .build();
    }

    /**
     * Maps an event model to an event entity without odds and with the competition ID
     * @param event the event model
     * @return the event entity
     */
    public EventEntity toEventEntityWithoutOdds (Event event) {

        return EventEntity.builder()
                .id(event.getId())
                .name(event.getName())
                .date(event.getDate())
                .isEnded(event.isEnded())
                .competition(CompetitionEntity.builder()
                        .id(event.getCompetition().getId()).build())
                .build();
    }

    /**
     * Maps an event entity to an event model without odds and with the competition ID
     * @param eventEntity the event entity
     * @return the event model
     */
    public Event toEventModelWithoutOdds (EventEntity eventEntity) {
        return Event.builder()
                .id(eventEntity.getId())
                .name(eventEntity.getName())
                .date(eventEntity.getDate())
                .isEnded(eventEntity.isEnded())
                .competition(Competition.builder()
                        .id(eventEntity.getCompetition().getId()).build())
                .build();
    }

    public List<Event> toModelWithOddsList (List<EventEntity> eventEntityList) {
        return eventEntityList.stream().map(this::toModelWithOdds).collect(Collectors.toList());
    }

    public Event toModelWithOdds (EventEntity eventEntity) {
        return Event.builder()
                .id(eventEntity.getId())
                .name(eventEntity.getName())
                .date(eventEntity.getDate())
                .isEnded(eventEntity.isEnded())
                .odds(eventEntity.getOdds().stream().map(infrastructureOddMapper::toOddModel).collect(Collectors.toSet()))
                .build();
    }

    public EventEntity toEventEntityToBetPlaced(Event event) {
        return EventEntity.builder()
                .id(event.getId())
                .name(event.getName())
                .date(event.getDate())
                .isEnded(event.isEnded())
                .competition(CompetitionEntity.builder()
                        .id(event.getCompetition().getId()).build())
                .odds(event.getOdds().stream().map(infrastructureOddMapper::toOddEntityWithAlotOfDetails).collect(Collectors.toSet()))
                .build();
    }

}
