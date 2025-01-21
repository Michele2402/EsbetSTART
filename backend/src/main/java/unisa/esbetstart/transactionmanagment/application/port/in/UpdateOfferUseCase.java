package unisa.esbetstart.transactionmanagment.application.port.in;

import unisa.esbetstart.transactionmanagment.presentation.request.UpdateOfferRequest;

public interface UpdateOfferUseCase {
    void updateOffer(UpdateOfferRequest request);
}
