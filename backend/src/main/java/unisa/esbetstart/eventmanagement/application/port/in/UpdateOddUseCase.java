package unisa.esbetstart.eventmanagement.application.port.in;

import unisa.esbetstart.eventmanagement.presentation.request.UpdateOddRequest;

public interface UpdateOddUseCase {

    /**
     * Updates an odd in the database.
     * @param request the UpdateOddRequest to update
     */
    void updateOdd(UpdateOddRequest request);

}
