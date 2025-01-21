package unisa.esbetstart.transactionmanagment.application.port.in;

import unisa.esbetstart.transactionmanagment.presentation.request.AddOfferRequest;

public interface CreateOfferUseCase {

    void createOffer(AddOfferRequest request);
}
