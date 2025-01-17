package unisa.esbetstart.eventmanagement.application.port.out;

import unisa.esbetstart.eventmanagement.domain.model.Odd;

import java.util.UUID;

public interface GetOddPortOut {

    /**
     * Get the odd by id, only the event id is retrieved
     * @param oddId the odd id
     * @return the odd
     */
    Odd getOddByIdWithSimpleDetails(UUID oddId);

}
