package unisa.esbetstart.transactionmanagment.application.port.out;

import unisa.esbetstart.transactionmanagment.domain.model.Offer;

public interface CreateOfferPortOut {
    void addOffer(Offer offer);
}
