package unisa.esbetstart.eventmanagement.application.port.in;

import unisa.esbetstart.eventmanagement.presentation.request.AddCompetitionRequest;

public interface AddCompetitionUseCase {

    /**
     * Adds a competition to the database.
     * @param request the AddCompetitionRequest to add
     */
    void addCompetition(AddCompetitionRequest request);
}
