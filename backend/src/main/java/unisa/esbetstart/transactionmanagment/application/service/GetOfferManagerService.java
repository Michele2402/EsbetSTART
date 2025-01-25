package unisa.esbetstart.transactionmanagment.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import unisa.esbetstart.transactionmanagment.application.port.in.GetOfferUseCase;
import unisa.esbetstart.transactionmanagment.application.port.out.GetOfferPortOut;
import unisa.esbetstart.transactionmanagment.domain.model.Offer;

import java.util.Set;

@RequiredArgsConstructor
@Service
@Slf4j
public class GetOfferManagerService implements GetOfferUseCase {

    private final GetOfferPortOut getOfferPortOut;


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
}
