package unisa.esbetstart.slipmanagment.application.port.out;

import unisa.esbetstart.slipmanagment.domain.model.OddStatic;

import java.util.UUID;

public interface GetOddStaticPortOut {

    /**
     * Get the odd static up until the gambler
     * @return the odd static
     */
    OddStatic getOddStaticToGamblerById(UUID oddStaticId);

}
