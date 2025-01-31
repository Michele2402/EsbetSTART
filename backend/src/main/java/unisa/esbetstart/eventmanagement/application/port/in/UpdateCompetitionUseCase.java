package unisa.esbetstart.eventmanagement.application.port.in;

import unisa.esbetstart.eventmanagement.presentation.request.UpdateCompetitionRequest;

public interface UpdateCompetitionUseCase {

    /**
     * Updates a competition in the database.
     * @param request the UpdateCompetitionRequest to update
     */
    void updateCompetition(UpdateCompetitionRequest request);

}
