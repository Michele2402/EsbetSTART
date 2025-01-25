package unisa.esbetstart.transactionmanagment.application.port.in;

import unisa.esbetstart.transactionmanagment.domain.model.Offer;

import java.util.Set;

public interface GetOfferUseCase {
    Set<Offer> getALlOffers();
}
