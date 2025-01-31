package unisa.esbetstart.slipmanagment.presentation.mapper;

import org.springframework.stereotype.Component;
import unisa.esbetstart.slipmanagment.domain.model.Slip;
import unisa.esbetstart.slipmanagment.presentation.response.SlipOddResponse;
import unisa.esbetstart.slipmanagment.presentation.response.SlipResponse;

import java.util.stream.Collectors;

@Component
public class PresentationSlipMapper {

    /**
     * This method maps a Slip to a SlipResponse
     *
     * @param slip
     * @return SlipResponse
     */
    public SlipResponse toSlipResponse(Slip slip) {
        return SlipResponse.builder()
                .amount(slip.getAmount())
                .odds(slip.getOdds().stream().map(slipOdd -> SlipOddResponse.builder()
                                .oddId(slipOdd.getId().toString())
                                .oddName(slipOdd.getName())
                                .oddValue(slipOdd.getValue())
                                .eventName(slipOdd.getEvent().getName())
                        .build())
                        .collect(Collectors.toSet())
                )
                .build();
    }

}
