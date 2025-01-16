package unisa.esbetstart.transactionmanagment.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import unisa.esbetstart.common.utils.ParseAttribute;
import unisa.esbetstart.transactionmanagment.application.port.in.RemoveOfferUseCase;
import unisa.esbetstart.transactionmanagment.application.port.out.GetOfferPortOut;
import unisa.esbetstart.transactionmanagment.application.port.out.RemoveOfferPortOut;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class RemoveOfferManagerService implements RemoveOfferUseCase {

    private final RemoveOfferPortOut removeOfferPortOut;
    private final ParseAttribute parseAttribute;
    private final GetOfferPortOut getOfferPortOut;

    /**
     * Removes an offer from the database.
     * @param offerId the id of the offer
     */
    @Override
    public void removeOffer(String offerId) {
        log.info("Removing offer with id: " + offerId);

        // Check if the offer id is valid and convert it into a UUID object
        UUID id = parseAttribute.checkUUIDIsNullOrInvalid(offerId, "Offer id");

        if(getOfferPortOut.getOfferById(id) == null) {
            log.error("Offer with id {} not found", id);
            return;
        }

        // Remove the offer from the database
        removeOfferPortOut.removeOffer(id);

        log.info("Offer removed");
    }

}
