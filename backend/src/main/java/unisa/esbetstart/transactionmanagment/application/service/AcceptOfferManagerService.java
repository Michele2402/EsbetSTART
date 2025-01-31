package unisa.esbetstart.transactionmanagment.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import unisa.esbetstart.common.exceptions.ObjectIsNullException;
import unisa.esbetstart.common.exceptions.ObjectNotFoundException;
import unisa.esbetstart.common.utils.CheckTypeAttribute;
import unisa.esbetstart.common.utils.ParseAttribute;
import unisa.esbetstart.transactionmanagment.application.port.in.AcceptOfferUseCase;
import unisa.esbetstart.transactionmanagment.application.port.out.CreateActivatedOfferPortOut;
import unisa.esbetstart.transactionmanagment.application.port.out.GetOfferPortOut;
import unisa.esbetstart.transactionmanagment.domain.model.ActivatedOffer;
import unisa.esbetstart.transactionmanagment.domain.model.Offer;
import unisa.esbetstart.transactionmanagment.presentation.request.AcceptOfferRequest;
import unisa.esbetstart.usermanagment.application.port.out.GetGamblerPortOut;
import unisa.esbetstart.usermanagment.domain.model.Gambler;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class AcceptOfferManagerService implements AcceptOfferUseCase {

    private final ParseAttribute parseAttribute;
    private final GetOfferPortOut getOfferPortOut;
    private final GetGamblerPortOut getGamblerPortOut;
    private final CheckTypeAttribute CheckTypeAttribute;
    private final CreateActivatedOfferPortOut createActivatedOfferPortOut;

    @Override
    public void acceptOffer(AcceptOfferRequest request) {
        log.info("Accepting offer: {}", request.getOfferId());

        // Check if the offer ID is valid
        UUID offerId = parseAttribute.checkUUIDIsNullOrInvalid(request.getOfferId(), "Offer ID");

        // Check if the gambler email is valid
        CheckTypeAttribute.checkStringIsNullOrEmpty(request.getGamblerEmail(), "Gambler Email");

        // Get the offer and the gambler
        Offer offer = getOfferPortOut.getOfferById(offerId);
        Gambler gambler = getGamblerPortOut.getGamblerByEmailWithActivatedOffers(request.getGamblerEmail());

        if(offer == null) {
            log.error("Offer not found");
            throw new ObjectNotFoundException("Offer not found");
        }

        if(gambler == null) {
            log.error("Gambler not found");
            throw new ObjectNotFoundException("Gambler not found");
        }

        // Check if the offer is still active
        if(offer.getExpirationDate().isBefore(LocalDateTime.now())) {
            log.error("Offer is not active");
            throw new ObjectIsNullException("Offer is not active");
        }

        // Accept the offer
        ActivatedOffer activatedOffer = gambler.acceptOffer(offer);

        // Save the activated offer
        createActivatedOfferPortOut.addAcceptedOffer(activatedOffer);

        log.info("Offer accepted");
    }

}
