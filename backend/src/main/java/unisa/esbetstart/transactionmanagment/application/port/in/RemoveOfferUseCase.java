package unisa.esbetstart.transactionmanagment.application.port.in;

public interface RemoveOfferUseCase {

    /**
     * Removes an offer
     * @param offerId the offer id
     */
    void removeOffer(String offerId);
}
