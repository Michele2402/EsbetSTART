package unisa.esbetstart.transactionmanagment.application.port.out;

import unisa.esbetstart.transactionmanagment.domain.model.ActivatedOffer;

public interface CreateActivatedOfferPortOut {
    void addAcceptedOffer(ActivatedOffer offer);
}
