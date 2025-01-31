package unisa.esbetstart.transactionmanagment.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import unisa.esbetstart.common.exceptions.ObjectNotFoundException;
import unisa.esbetstart.common.utils.CheckTypeAttribute;
import unisa.esbetstart.common.utils.ParseAttribute;
import unisa.esbetstart.transactionmanagment.application.port.in.GetOfferUseCase;
import unisa.esbetstart.transactionmanagment.application.port.out.GetOfferPortOut;
import unisa.esbetstart.transactionmanagment.domain.model.ActivatedOffer;
import unisa.esbetstart.transactionmanagment.domain.model.Offer;
import unisa.esbetstart.usermanagment.application.port.out.GetUserPortOut;
import unisa.esbetstart.usermanagment.domain.model.User;

import java.util.Set;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Slf4j
public class GetOfferManagerService implements GetOfferUseCase {

    private final GetOfferPortOut getOfferPortOut;
    private final GetUserPortOut getUserPortOut;

    private final ParseAttribute parseAttribute;

    /**
     * Get all offers from the database.
     *
     * @return a set of all offers
     */
    @Override
    public Set<Offer> getALlOffers() {

        log.info("Getting all offers");

        Set<Offer> offers = getOfferPortOut.getAllOffers();

        log.info("All offers retrieved");

        return offers;
    }

    @Override
    public Set<ActivatedOffer> getActivatedOffersByGamblerEmail(String gamblerEmail) {

        log.info("Getting activated offers by gambler id: {}", gamblerEmail);


        User user = getUserPortOut.getUserByEmail(gamblerEmail);

        if(user == null) {
            log.error("User with email {} not found", gamblerEmail);
            throw new ObjectNotFoundException("User with email " + gamblerEmail + " not found");
        }

        Set<ActivatedOffer> activatedOffers = getOfferPortOut.getActivatedOffersByGamblerEmail(gamblerEmail);

        log.info("Activated offers retrieved");

        return activatedOffers;
    }
}
