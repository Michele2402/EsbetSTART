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

    public OddEntity toOddEntity (Odd odd) {
        return OddEntity.builder()
                .id(odd.getId())
                .name(odd.getName())
                .value(odd.getValue())
                .event(EventEntity.builder()
                        .id(odd.getEvent().getId()).build())
                .build();
    }

    public Odd toOddModel (OddEntity oddEntity) {
        return Odd.builder()
                .id(oddEntity.getId())
                .name(oddEntity.getName())
                .value(oddEntity.getValue())
                .build();
    }
}
