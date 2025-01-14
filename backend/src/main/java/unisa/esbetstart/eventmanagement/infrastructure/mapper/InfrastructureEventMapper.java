package unisa.esbetstart.eventmanagement.infrastructure.mapper;

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

}
