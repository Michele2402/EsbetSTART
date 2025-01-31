package unisa.esbetstart.slipmanagment.infrastructure.mapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import unisa.esbetstart.eventmanagement.domain.model.Odd;
import unisa.esbetstart.eventmanagement.infrastructure.entity.OddEntity;
import unisa.esbetstart.eventmanagement.infrastructure.mapper.InfrastructureGameMapper;
import unisa.esbetstart.slipmanagment.domain.model.BetPlaced;
import unisa.esbetstart.slipmanagment.domain.model.OddStatic;
import unisa.esbetstart.slipmanagment.infrastructure.entity.BetPlacedEntity;
import unisa.esbetstart.slipmanagment.infrastructure.entity.OddStaticEntity;
import unisa.esbetstart.usermanagment.domain.model.Gambler;

import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class InfrastructureOddStaticMapper {

    /**
     * This method maps an OddStatic to an OddStaticEntity
     *
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

    /**
     * This method maps an OddStaticEntity to an OddStatic
     *
     * @param oddStaticEntity
     * @return OddStatic
     */
    public OddStatic toOddStaticModelUpToGambler(OddStaticEntity oddStaticEntity) {

        return OddStatic.builder()
                .id(oddStaticEntity.getId())
                .betPlaced(BetPlaced.builder()
                        .id(oddStaticEntity.getBetPlaced().getId())
                        .amount(oddStaticEntity.getBetPlaced().getAmount())
                        .result(oddStaticEntity.getBetPlaced().getResult())
                        .oddStatics(oddStaticEntity
                                .getBetPlaced()
                                .getOdds()
                                .stream()
                                .map(oddStatic -> OddStatic.builder()
                                        .id(oddStatic.getId())
                                        .value(oddStatic.getValue())
                                        .date(oddStatic.getDate())
                                        .name(oddStatic.getName())
                                        .result(oddStatic.getResult())
                                        .competition(oddStatic.getCompetition())
                                        .game(oddStatic.getGame())
                                        .build())
                                .collect(Collectors.toSet()))
                                .gambler(Gambler.builder()
                                        .email(oddStaticEntity.getBetPlaced().getGambler().getEmail())
                                        .balance(oddStaticEntity.getBetPlaced().getGambler().getBalance())
                                        .build())
                                .build())
                        .value(oddStaticEntity.getValue())
                        .build();
    }

    /**
     * This method maps an OddStaticEntity to an OddStaticModel
     *
     * @param oddStaticEntity
     * @return OddStatic
     */
    public OddStatic toOddStaticModelWithAlotOfDetails(OddStaticEntity oddStaticEntity) {

        return OddStatic.builder()
                .id(oddStaticEntity.getId())
                .odd(Odd.builder().id(oddStaticEntity.getOdd().getId()).build())
                .betPlaced(BetPlaced.builder()
                        .id(oddStaticEntity.getBetPlaced().getId())
                        .amount(oddStaticEntity.getBetPlaced().getAmount())
                        .result(oddStaticEntity.getBetPlaced().getResult())
                        .build())
                .value(oddStaticEntity.getValue())
                .name(oddStaticEntity.getName())
                .result(oddStaticEntity.getResult())
                .competition(oddStaticEntity.getCompetition())
                .game(oddStaticEntity.getGame())
                .date(oddStaticEntity.getDate())
                .build();

    }

    public OddStaticEntity toOddStaticEntityWithAlotOfDetails(OddStatic oddStatic) {

        return OddStaticEntity.builder()
                .id(oddStatic.getId())
                .betPlaced(BetPlacedEntity.builder().id(oddStatic.getBetPlaced().getId()).build())
                .odd(OddEntity.builder().id(oddStatic.getOdd().getId()).build())
                .value(oddStatic.getValue())
                .name(oddStatic.getName())
                .competition(oddStatic.getCompetition())
                .game(oddStatic.getGame())
                .date(oddStatic.getDate())
                .result(oddStatic.getResult())
                .build();
    }

}
