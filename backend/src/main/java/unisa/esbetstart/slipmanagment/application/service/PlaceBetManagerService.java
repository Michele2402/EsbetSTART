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
import unisa.esbetstart.transactionmanagment.application.port.out.DeleteActivatedOfferPortOut;
import unisa.esbetstart.transactionmanagment.domain.model.ActivatedOffer;
import unisa.esbetstart.usermanagment.application.port.out.GetGamblerPortOut;
import unisa.esbetstart.usermanagment.application.port.out.UpdateUserPortOut;
import unisa.esbetstart.usermanagment.domain.model.Gambler;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class PlaceBetManagerService implements PlaceBetUseCase {


    private final ParseAttribute parseAttribute;
    private final GetSlipPortOut getSlipPortOut;
    private final PlaceBetPortOut placeBetPortOut;
    private final UpdateSlipPortOut updateSlipPortOut;
    private final GetGamblerPortOut getGamblerPortOut;
    private final DeleteActivatedOfferPortOut deleteActivatedOfferPortOut;
    private final UpdateUserPortOut updateGamblerPortOut;

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

        //get the gambler
        Gambler gambler = getGamblerPortOut.getGamblerByEmailWithOffers(slip.getGambler().getEmail());

        //get the list of the activated offers
        List<UUID> oldActivatedOffers = gambler.getActivatedOffers().stream()
                .map(ActivatedOffer::getId)
                .toList();

        //place the bet on the gambler object
        gambler.placeBet(betPlaced.getAmount());

        //delete all the activated offers that are completed
        for (UUID activatedOfferId : oldActivatedOffers) {
            if(!gambler.getActivatedOffers().stream().map(ActivatedOffer::getId).toList().contains(activatedOfferId)) {
                deleteActivatedOfferPortOut.deleteActivatedOfferById(activatedOfferId);
            }
        }

        //place the bet on the database
        placeBetPortOut.placeBet(betPlaced);

        //update the gambler
        updateGamblerPortOut.updateGambler(gambler);

        slip.resetSlip();
        updateSlipPortOut.updateSlip(slip);

        log.info("Bet placed for slip with id: {}", slipId);

    }
}
