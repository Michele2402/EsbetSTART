package unisa.esbetstart.eventmanagement.infrastructure.mapper;

import jakarta.servlet.http.PushBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import unisa.esbetstart.eventmanagement.domain.model.Competition;
import unisa.esbetstart.eventmanagement.domain.model.Event;
import unisa.esbetstart.eventmanagement.domain.model.Odd;
import unisa.esbetstart.eventmanagement.infrastructure.entity.CompetitionEntity;
import unisa.esbetstart.eventmanagement.infrastructure.entity.EventEntity;

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
                .competition(CompetitionEntity.builder()
                        .id(event.getCompetition().getId()).build())
                .odds(event.getOdds().stream().map(infrastructureOddMapper::toOddEntity).collect(Collectors.toSet()))
                .build();
    }


    /**
     * Maps an event entity to an event model with simple details (only with odds and competition ID)
     * @param eventEntity the event entity
     * @return the event model
     */

    public Event toEventModelWithSimpleDetails (EventEntity eventEntity) {
        return Event.builder()
                .id(eventEntity.getId())
                .name(eventEntity.getName())
                .date(eventEntity.getDate())
                .competition(Competition.builder()
                        .id(eventEntity.getCompetition().getId()).build())
                .odds(eventEntity.getOdds().stream().map(
                        oddEntity -> Odd.builder()
                                .id(oddEntity.getId())
                                .build()
                ).collect(Collectors.toSet()))
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
                .competition(Competition.builder()
                        .id(eventEntity.getCompetition().getId()).build())
                .build();
    }

}
