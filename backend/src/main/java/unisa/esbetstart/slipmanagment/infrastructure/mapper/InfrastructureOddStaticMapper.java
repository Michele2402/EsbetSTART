package unisa.esbetstart.slipmanagment.infrastructure.mapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import unisa.esbetstart.eventmanagement.infrastructure.entity.OddEntity;
import unisa.esbetstart.slipmanagment.domain.model.OddStatic;
import unisa.esbetstart.slipmanagment.infrastructure.entity.BetPlacedEntity;
import unisa.esbetstart.slipmanagment.infrastructure.entity.OddStaticEntity;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class InfrastructureOddStaticMapper {

    private final InfrastructureBetPlacedMapper infrastructureBetPlacedMapper;

    /**
     * This method maps an OddStatic to an OddStaticEntity
     * @param oddStatic
     * @return OddStatic
     */
    public OddStaticEntity toOddStaticEntity(OddStatic oddStatic) {

        return OddStaticEntity.builder()
                .id(oddStatic.getId())
                .odd(OddEntity.builder().id(oddStatic.getOdd().getId()).build())
                .betPlaced(BetPlacedEntity.builder().id(oddStatic.getBetPlaced().getId()).build())
                .value(oddStatic.getValue())
                .name(oddStatic.getName())
                .result(oddStatic.getResult())
                .competition(oddStatic.getCompetition())
                .game(oddStatic.getGame())
                .date(oddStatic.getDate())
                .build();
    }

    public OddStatic toOddStaticModelWithAlotOfDetails(OddStaticEntity oddStaticEntity) {

        return OddStatic.builder()
                .id(oddStaticEntity.getId())
                .betPlaced(infrastructureBetPlacedMapper.toBetPlacedModel(oddStaticEntity.getBetPlaced()))
                .result(oddStaticEntity.getResult())
                .build();

    }

    public OddStaticEntity toOddStaticEntityWithAlotOfDetails(OddStatic oddStatic) {

        return OddStaticEntity.builder()
                .id(oddStatic.getId())
                .betPlaced(BetPlacedEntity.builder().id(oddStatic.getBetPlaced().getId()).build())
                .value(oddStatic.getValue())
                .name(oddStatic.getName())
                .result(oddStatic.getResult())
                .build();
    }

}
