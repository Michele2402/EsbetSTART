package unisa.esbetstart.transactionmanagment.application.port.out;

import unisa.esbetstart.transactionmanagment.domain.model.ActivatedOffer;
import unisa.esbetstart.transactionmanagment.domain.model.Offer;

import java.util.Set;
import java.util.UUID;

public interface GetOfferPortOut {
    Offer getOfferById(UUID offerId);
    Offer getOfferByName(String offerName);
    Set<Offer> getAllOffers();
    Set<ActivatedOffer> getActivatedOffersByGamblerEmail(String gamblerEmail);
}
