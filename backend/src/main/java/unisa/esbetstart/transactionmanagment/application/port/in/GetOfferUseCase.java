package unisa.esbetstart.transactionmanagment.application.port.in;

import unisa.esbetstart.transactionmanagment.domain.model.ActivatedOffer;
import unisa.esbetstart.transactionmanagment.domain.model.Offer;

import java.util.Set;

public interface GetOfferUseCase {

    /**
     * Gets all the offers in the database
     * @return the set of offers
     */
    Set<Offer> getALlOffers();

    /**
     * Gets all the activated offers of a gambler
     * @param gamblerEmail the gambler's email
     * @return the set of activated offers
     */
    Set<ActivatedOffer> getActivatedOffersByGamblerEmail(String gamblerEmail);
}
