package unisa.esbetstart.eventmanagement.application.port.in;

import unisa.esbetstart.eventmanagement.presentation.request.AddCompetitionRequest;

public interface AddCompetitionUseCase {

    void addCompetition(AddCompetitionRequest request);
}
