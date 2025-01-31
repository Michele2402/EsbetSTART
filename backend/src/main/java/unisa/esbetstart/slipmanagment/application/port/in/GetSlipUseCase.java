package unisa.esbetstart.slipmanagment.application.port.in;

import unisa.esbetstart.slipmanagment.domain.model.Slip;

import java.util.UUID;

public interface GetSlipUseCase {

    /**
     * Gets the slip of a gambler by its email.
     * @param gamblerEmail the email of the gambler
     * @return the slip
     */
    Slip getSlip(String gamblerEmail);

}
