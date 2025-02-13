package unisa.esbetstart.slipmanagment.infrastructure.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import unisa.esbetstart.eventmanagement.infrastructure.entity.OddEntity;
import unisa.esbetstart.slipmanagment.domain.model.BetPlaced;
import unisa.esbetstart.slipmanagment.domain.model.OddStatic;
import unisa.esbetstart.slipmanagment.infrastructure.entity.BetPlacedEntity;
import unisa.esbetstart.slipmanagment.infrastructure.entity.OddStaticEntity;
import unisa.esbetstart.usermanagment.infrastructure.entity.GamblerEntity;

import java.time.LocalDateTime;
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
                .date(LocalDateTime.now())
                .gambler(GamblerEntity.builder()
                        .email(betPlaced.getGambler().getEmail())
                        .withdrawableBalance(betPlaced.getGambler().getWithdrawableBalance())
                        .build())
                .result(betPlaced.getResult())
                .odds(betPlaced.getOddStatics().stream().map(infrastructureOddStaticMapper::toOddStaticEntity).collect(Collectors.toSet()))
                .build();
    }

    /**
     * This method maps a BetPlaced to a BetPlacedEntity up to the gambler
     * @param betPlaced
     * @return BetPlacedEntity
     */
    public BetPlacedEntity toBetPlacedEntityToGambler(BetPlaced betPlaced) {
        return BetPlacedEntity.builder()
                .id(betPlaced.getId())
                .amount(betPlaced.getAmount())
                .date(betPlaced.getDate())
                .gambler(GamblerEntity.builder()
                        .email(betPlaced.getGambler().getEmail())
                        .withdrawableBalance(betPlaced.getGambler().getWithdrawableBalance())
                        .build())
                .result(betPlaced.getResult())
                .odds(betPlaced.getOddStatics().stream().map(oddStatic -> OddStaticEntity.builder()
                        .id(oddStatic.getId())
                        .betPlaced(BetPlacedEntity.builder().id(betPlaced.getId()).build())
                        .name(oddStatic.getName())
                        .event(oddStatic.getEvent())
                        .competition(oddStatic.getCompetition())
                        .game(oddStatic.getGame())
                        .date(oddStatic.getDate())
                        .result(oddStatic.getResult())
                        .value(oddStatic.getValue())
                        //odd may be null
                        .odd(oddStatic.getOdd() == null ? null : OddEntity.builder().id(oddStatic.getOdd().getId()).build())
                        .build()).collect(Collectors.toSet()))
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
                .oddStatics(betPlacedEntity.getOdds()
                        .stream()
                        .map(oddStaticEntity
                                -> OddStatic.builder().id(oddStaticEntity.getId()).build()).collect(Collectors.toSet()))
                .build();

    }

}
