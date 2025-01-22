package unisa.esbetstart.transactionmanagment.application.port.in;

import unisa.esbetstart.transactionmanagment.presentation.request.AcceptOfferRequest;

public interface AcceptOfferUseCase {
    void acceptOffer(AcceptOfferRequest request);
}
