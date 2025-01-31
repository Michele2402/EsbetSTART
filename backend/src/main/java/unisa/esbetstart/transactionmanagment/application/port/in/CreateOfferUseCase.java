package unisa.esbetstart.transactionmanagment.application.port.in;

import unisa.esbetstart.transactionmanagment.presentation.request.AddOfferRequest;

public interface CreateOfferUseCase {

    /**
     * Creates an offer
     * @param request the request
     */
    void createOffer(AddOfferRequest request);
}
