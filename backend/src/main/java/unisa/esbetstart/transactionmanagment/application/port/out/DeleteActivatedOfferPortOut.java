package unisa.esbetstart.transactionmanagment.application.port.out;

import java.util.UUID;

public interface DeleteActivatedOfferPortOut {

    void deleteActivatedOfferById(UUID activatedOfferId);

}
