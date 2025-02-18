package unisa.esbetstart.slipmanagment.infrastructure.mapper;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import unisa.esbetstart.eventmanagement.domain.model.Odd;
import unisa.esbetstart.slipmanagment.domain.model.BetPlaced;
import unisa.esbetstart.slipmanagment.domain.model.OddStatic;
import unisa.esbetstart.slipmanagment.infrastructure.entity.BetPlacedEntity;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class InfrastructureBetMapper {

    /**
     * Maps a BetPlacedEntity to a BetPlaced model with the OddStatics.
     * @param betPlacedEntity the BetPlacedEntity to map
     * @return the BetPlaced model with OddStatic
     * */
    public BetPlaced toBetModelWithOddStatic(BetPlacedEntity betPlacedEntity) {
        Set<OddStatic> oddStatics = betPlacedEntity.getOdds().stream()
                .map(oddStaticEntity -> OddStatic.builder()
                        .id(oddStaticEntity.getId())
                        .name(oddStaticEntity.getName())
                        .value(oddStaticEntity.getValue())
                        .result(oddStaticEntity.getResult())
                        .event(oddStaticEntity.getEvent())
                        .event(oddStaticEntity.getEvent())
                        .competition(oddStaticEntity.getCompetition())
                        .game(oddStaticEntity.getGame())
                        .build()
                ).collect(Collectors.toSet());

        return BetPlaced.builder()
                .amount(betPlacedEntity.getAmount())
                .result(betPlacedEntity.getResult())
                .date(betPlacedEntity.getDate())
                .oddStatics(oddStatics)
                .build();
    }
}
