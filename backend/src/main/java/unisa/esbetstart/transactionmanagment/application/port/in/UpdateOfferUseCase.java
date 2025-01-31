package unisa.esbetstart.transactionmanagment.application.port.in;

import unisa.esbetstart.transactionmanagment.presentation.request.UpdateOfferRequest;

public interface UpdateOfferUseCase {

    /**
     * Updates an offer
     * @param request the request
     */
    void updateOffer(UpdateOfferRequest request);

}
