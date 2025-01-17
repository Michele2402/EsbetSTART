package unisa.esbetstart.eventmanagement.infrastructure.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import unisa.esbetstart.eventmanagement.domain.model.Event;
import unisa.esbetstart.eventmanagement.domain.model.Odd;
import unisa.esbetstart.eventmanagement.infrastructure.entity.EventEntity;
import unisa.esbetstart.eventmanagement.infrastructure.entity.OddEntity;

@Component
@RequiredArgsConstructor
public class InfrastructureOddMapper {

    /**
     * Maps an Odd model to an OddEntity.
     * @param odd the Odd model to map
     * @return the OddEntity
     */
    public OddEntity toOddEntity (Odd odd) {
        return OddEntity.builder()
                .id(odd.getId())
                .name(odd.getName())
                .value(odd.getValue())
                .position(odd.getPosition())
                .event(EventEntity.builder()
                        .id(odd.getEvent().getId()).build())
                .build();
    }


    public Odd toOddModel (OddEntity oddEntity) {
        return Odd.builder()
                .id(oddEntity.getId())
                .name(oddEntity.getName())
                .value(oddEntity.getValue())
                .position(oddEntity.getPosition())
                .build();
    }

    /**
     * Maps an OddEntity to an Odd model with only the event id.
     * @param oddEntity the OddEntity to map
     * @return the Odd model
     */
    public Odd toOddModelWithSimpleDetails (OddEntity oddEntity) {
        return Odd.builder()
                .id(oddEntity.getId())
                .name(oddEntity.getName())
                .value(oddEntity.getValue())
                .position(oddEntity.getPosition())
                .event(Event.builder()
                        .id(oddEntity.getEvent().getId()).build())
                .build();
    }
}
