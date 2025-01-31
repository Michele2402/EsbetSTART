package unisa.esbetstart.transactionmanagment.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import unisa.esbetstart.common.exceptions.ObjectIsNullException;
import unisa.esbetstart.common.utils.ParseAttribute;
import unisa.esbetstart.transactionmanagment.application.port.in.CreateOfferUseCase;
import unisa.esbetstart.transactionmanagment.application.port.out.CreateOfferPortOut;
import unisa.esbetstart.transactionmanagment.application.port.out.GetOfferPortOut;
import unisa.esbetstart.transactionmanagment.domain.enums.OfferTypeEnum;
import unisa.esbetstart.transactionmanagment.domain.model.Offer;
import unisa.esbetstart.transactionmanagment.presentation.request.AddOfferRequest;
import unisa.esbetstart.common.utils.CheckTypeAttribute;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class CreateOfferManagerService implements CreateOfferUseCase {

    private final CheckTypeAttribute checkTypeAttribute;
    private final ParseAttribute parseAttribute;
    private final GetOfferPortOut getOfferPortOut;
    private final CreateOfferPortOut createOfferPortOut;

    @Override
    public void createOffer(AddOfferRequest request) {
        log.info("Adding offer {} to database", request.getName());

        // Convert the expiration date string into a LocalDateTime object
        LocalDateTime date = parseAttribute.convertStringIntoDate(request.getExpirationDate(), "Expiration date");

        // Convert the enum string into an OfferType object
        OfferTypeEnum type = parseAttribute.convertStringIntoOfferType(request.getType(), "Offer type");

        // Check the offer data
        checkAddOfferRequest(request);

        // Build the Offer object
        Offer offer = Offer.builder()
                .id(UUID.randomUUID())
                .name(request.getName())
                .description(request.getDescription())
                .expirationDate(date)
                .goal(request.getGoal())
                .type(type)
                .price(request.getPrice())
                .build();

        // Check if an offer with the same name already exists
        if (getOfferPortOut.getOfferByName(request.getName()) != null) {
            log.error("Offer with name {} already exists", request.getName());
            throw new ObjectIsNullException("Offer with name " + request.getName() + " already exists");
        }

        // Add the offer to the database
        createOfferPortOut.addOffer(offer);

        log.info("Offer {} added to database", request.getName());
    }

    private void checkAddOfferRequest(AddOfferRequest request) {
        if (request == null) {
            log.error("AddOfferRequest is null");
            throw new ObjectIsNullException("AddOfferRequest is null");
        }

        // Check the attributes of the request
        checkTypeAttribute.checkStringIsNullOrEmpty(request.getName(), "Name");
        checkTypeAttribute.checkStringIsNullOrEmpty(request.getDescription(), "Description");
        checkTypeAttribute.checkIntegerIsNullOrNegative(request.getGoal(), "Goal");
        checkTypeAttribute.checkFloatIsNullOrNegative(request.getPrice(), "Price");

    }
}
