package unisa.esbetstart.slipmanagment.infrastructure.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import unisa.esbetstart.slipmanagment.domain.model.BetPlaced;
import unisa.esbetstart.slipmanagment.infrastructure.entity.BetPlacedEntity;
import unisa.esbetstart.usermanagment.infrastructure.entity.GamblerEntity;

import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class InfrastructureBetPlacedMapper {

    private final InfrastructureOddStaticMapper infrastructureOddStaticMapper;

    /**
     * This method maps a BetPlaced to a BetPlacedEntity
     * @param betPlaced
     * @return BetPlacedEntity
     */
    public BetPlacedEntity toBetPlacedEntity(BetPlaced betPlaced) {
        return BetPlacedEntity.builder()
                .id(betPlaced.getId())
                .amount(betPlaced.getAmount())
                .gambler(GamblerEntity.builder().email(betPlaced.getGambler().getEmail()).build())
                .result(betPlaced.getResult())
                .odds(betPlaced.getOddStatics().stream().map(infrastructureOddStaticMapper::toOddStaticEntity).collect(Collectors.toSet()))
                .build();
    }

    /**
     * This method maps a BetPlacedEntity to a BetPlaced
     * @param betPlacedEntity
     * @return BetPlaced
     */
    public BetPlaced toBetPlacedModel(BetPlacedEntity betPlacedEntity) {

        return BetPlaced.builder()
                .id(betPlacedEntity.getId())
                .amount(betPlacedEntity.getAmount())
                .result(betPlacedEntity.getResult())
                .build();

    }

}
