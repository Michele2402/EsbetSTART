package unisa.esbetstart.transactionmanagment.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import unisa.esbetstart.common.exceptions.ObjectIsNullException;
import unisa.esbetstart.common.utils.CheckTypeAttribute;
import unisa.esbetstart.common.utils.ParseAttribute;
import unisa.esbetstart.transactionmanagment.application.port.in.UpdateOfferUseCase;
import unisa.esbetstart.transactionmanagment.application.port.out.CreateOfferPortOut;
import unisa.esbetstart.transactionmanagment.application.port.out.GetOfferPortOut;
import unisa.esbetstart.transactionmanagment.domain.enums.OfferTypeEnum;
import unisa.esbetstart.transactionmanagment.domain.model.Offer;
import unisa.esbetstart.transactionmanagment.presentation.request.UpdateOfferRequest;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class UpdateOfferManagerService implements UpdateOfferUseCase {

    private final ParseAttribute parseAttribute;
    private final CheckTypeAttribute checkTypeAttribute;
    private final GetOfferPortOut getOfferPortOut;
    private final CreateOfferPortOut createOfferPortOut;

    @Override
    public void updateOffer(UpdateOfferRequest request) {
        log.info("Updating offer with id: " + request.getOfferId());

        // Check the offer id
        UUID offerId = parseAttribute.checkUUIDIsNullOrInvalid(request.getOfferId(), "Offer id");

        // Convert the expiration date string into a LocalDateTime object
        LocalDateTime date = parseAttribute.convertStringIntoDate(request.getExpirationDate(), "Expiration date");

        // Convert the enum string into an OfferType object
        OfferTypeEnum type = parseAttribute.convertStringIntoOfferType(request.getType(), "Offer type");

        // Check the offer data
        checkUpdateOfferRequest(request);

        // Get the existing offer
        Offer existingOffer = getOfferPortOut.getOfferById(offerId);

        // Check if the offer exists
        if(existingOffer == null) {
            log.error("Offer with id {} not found", offerId);
            throw new ObjectIsNullException("Offer with id " + offerId + " not found");
        }

        // Update the offer
        existingOffer.setName(request.getName());
        existingOffer.setDescription(request.getDescription());
        existingOffer.setExpirationDate(date);
        existingOffer.setGoal(request.getGoal());
        existingOffer.setType(type);
        existingOffer.setBonus(request.getPrice());

        // Update the offer in the database
        createOfferPortOut.updateOffer(existingOffer);

        log.info("Offer with id: {} updated", offerId);
    }

    private void checkUpdateOfferRequest(UpdateOfferRequest request) {
        if(request == null) {
            log.error("UpdateOfferRequest is null");
            throw new ObjectIsNullException("UpdateOfferRequest is null");
        }

        // Check the offer data
        checkTypeAttribute.checkFloatIsNullOrNegative(request.getPrice(), "Price");
        checkTypeAttribute.checkStringIsNullOrEmpty(request.getName(), "Name");
        checkTypeAttribute.checkStringIsNullOrEmpty(request.getDescription(), "Description");
        checkTypeAttribute.checkIntegerIsNullOrNegative(request.getGoal(), "Goal");

    }
}
