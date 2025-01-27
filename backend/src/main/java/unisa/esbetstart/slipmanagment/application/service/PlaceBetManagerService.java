package unisa.esbetstart.slipmanagment.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import unisa.esbetstart.common.exceptions.ObjectIsNullException;
import unisa.esbetstart.common.utils.ParseAttribute;
import unisa.esbetstart.slipmanagment.application.port.in.PlaceBetUseCase;
import unisa.esbetstart.slipmanagment.application.port.out.GetSlipPortOut;
import unisa.esbetstart.slipmanagment.application.port.out.PlaceBetPortOut;
import unisa.esbetstart.slipmanagment.application.port.out.UpdateSlipPortOut;
import unisa.esbetstart.slipmanagment.domain.model.BetPlaced;
import unisa.esbetstart.slipmanagment.domain.model.Slip;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class PlaceBetManagerService implements PlaceBetUseCase {


    private final ParseAttribute parseAttribute;
    private final GetSlipPortOut getSlipPortOut;
    private final PlaceBetPortOut placeBetPortOut;
    private final UpdateSlipPortOut updateSlipPortOut;

    @Override
    public void placeBet(String slipId) {

        log.info("Place bet for slip with id: {}", slipId);

        //check if the id is valid
        UUID slipUUID = parseAttribute.checkUUIDIsNullOrInvalid(slipId, "Slip id in place bet call");

        Slip slip = getSlipPortOut.getSlipCompleteById(slipUUID);

        //check if the slip is valid
        if (slip == null) {
            log.error("Slip with id: {} not found", slipId);
            throw new ObjectIsNullException("Slip with id: " + slipId + " not found");
        }

        BetPlaced betPlaced = slip.placeBet();

        betPlaced.getOddStatics().forEach(oddStatic -> oddStatic.setBetPlaced(BetPlaced.builder().id(betPlaced.getId()).build()));

        placeBetPortOut.placeBet(betPlaced);

        slip.resetslip();
        updateSlipPortOut.updateSlip(slip);

        log.info("Bet placed for slip with id: {}", slipId);

    }
}
