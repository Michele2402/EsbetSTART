package unisa.esbetstart.transactionmanagment.application.port.in;

import unisa.esbetstart.transactionmanagment.presentation.request.AcceptOfferRequest;

public interface AcceptOfferUseCase {

    /**
     * Accepts an offer
     * @param request the request
     */
    void acceptOffer(AcceptOfferRequest request);
}
