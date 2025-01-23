package unisa.esbetstart.slipmanagment.infrastructure.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
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

}
