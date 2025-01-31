package unisa.esbetstart.slipmanagment.infrastructure.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import unisa.esbetstart.eventmanagement.domain.model.Competition;
import unisa.esbetstart.eventmanagement.domain.model.Event;
import unisa.esbetstart.eventmanagement.domain.model.Game;
import unisa.esbetstart.eventmanagement.domain.model.Odd;
import unisa.esbetstart.eventmanagement.infrastructure.entity.OddEntity;
import unisa.esbetstart.eventmanagement.infrastructure.mapper.InfrastructureOddMapper;
import unisa.esbetstart.slipmanagment.domain.model.Slip;
import unisa.esbetstart.slipmanagment.infrastructure.entity.SlipEntity;
import unisa.esbetstart.usermanagment.domain.model.Gambler;
import unisa.esbetstart.usermanagment.infrastructure.entity.GamblerEntity;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class InfrastructureSlipMapper {

    private final InfrastructureOddMapper infrastructureOddMapper;

    /**
     * Map a SlipEntity to a Slip, only has the gambler email
     *
     * @param slipEntity SlipEntity
     * @return Slip
     */
    public Slip toSlipWithGambler(SlipEntity slipEntity) {
        return Slip.builder()
                .amount(slipEntity.getAmount())
                .gambler(Gambler
                        .builder()
                        .email(slipEntity.getGambler().getEmail())
                        .build())
                .id(slipEntity.getId())
                .build();
    }

    /**
     * Map a Slip to a SlipEntity, only has the gambler email and the odds ids
     *
     * @param slip Slip
     * @return SlipEntity
     */
    public SlipEntity toSlipEntity(Slip slip) {
        return SlipEntity.builder()
                .amount(slip.getAmount())
                .gambler(GamblerEntity.builder().email(slip.getGambler().getEmail()).build())
                .odds(slip.getOdds()
                        .stream()
                        .map(odd -> OddEntity.builder().id(odd.getId())
                                .build())
                        .collect(Collectors.toSet()))
                .id(slip.getId())
                .build();
    }

    /**
     * Map a SlipEntity to a Slip, with links till game
     *
     * @param slipEntity SlipEntity
     * @return Slip
     */
    public Slip toSlipModelComplete(SlipEntity slipEntity) {

        return Slip.builder()
                .amount(slipEntity.getAmount())
                .gambler(Gambler
                        .builder()
                        .email(slipEntity.getGambler().getEmail())
                        .build())
                .id(slipEntity.getId())
                .odds(slipEntity.getOdds()
                        .stream()
                        .map(oddEntity ->
                                Odd.builder().value(oddEntity.getValue())
                                        .name(oddEntity.getName())
                                        .position(oddEntity.getPosition())
                                        .id(oddEntity.getId())
                                        .event(Event.builder()
                                                .id(oddEntity.getEvent().getId())
                                                .name(oddEntity.getEvent().getName())
                                                .date(oddEntity.getEvent().getDate())
                                                .competition(Competition.builder()
                                                        .id(oddEntity.getEvent().getCompetition().getId())
                                                        .name(oddEntity.getEvent().getCompetition().getName())
                                                        .game(Game.builder()
                                                                .id(oddEntity.getEvent().getCompetition().getGame().getId())
                                                                .name(oddEntity.getEvent().getCompetition().getGame().getName())
                                                                .build())
                                                        .build())
                                                .build())
                                        .build())
                        .collect(Collectors.toSet()))
                .build();
    }

    /**
     * Map a SlipEntity to a Slip, with links till odds
     *
     * @param slipEntity SlipEntity
     * @return Slip
     */
    public Slip toSlipModelWithOdds(SlipEntity slipEntity) {
        return Slip.builder()
                .amount(slipEntity.getAmount())
                .gambler(Gambler
                        .builder()
                        .email(slipEntity.getGambler().getEmail())
                        .build())
                .id(slipEntity.getId())
                .odds(slipEntity.getOdds()
                        .stream()
                        .map(infrastructureOddMapper::toOddModelWithSimpleDetails)
                        .collect(Collectors.toSet()))
                .build();
    }

}
